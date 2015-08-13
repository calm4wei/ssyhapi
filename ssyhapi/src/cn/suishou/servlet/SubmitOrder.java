package cn.suishou.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.RSA;
import com.alipay.util.AlipayCore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tenpay.AccessTokenRequestHandler;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;
import com.tenpay.util.ConstantUtil;
import com.tenpay.util.WXUtil;

import cn.suishou.bean.FlashSellItem;
import cn.suishou.bean.Order;
import cn.suishou.bean.SelfItem;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Config;
import cn.suishou.common.Value;
import cn.suishou.dao.OrderDAO;
import cn.suishou.manager.FlashSellItemManager;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.utils.Encrypt;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.youhui.log4ssy.api.Log4SSY;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Store;
import cn.youhui.log4ssy.utils.Enums.Type;

@WebServlet("/api/submitOrder")
public class SubmitOrder extends HttpServlet {
	private static Logger logger = Logger.getLogger(SubmitOrder.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		HashMap<String, String> parentOrderIdMap = new HashMap<String, String>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		List<Order> orderList = new ArrayList<Order>();
		String orderIds = "";
		try{
			
			int orderChannel = ParamUtil.getParameterInt(request, "orderChannel"); //目前无购物车
			String data = ParamUtil.getParameter(request, "data", true);		
			String content = Encrypt.decode(data).split("#")[1];
			
			String uid = ParamUtil.getParameter(request, "uid", true);
			String name="",phone_number="",address_province="",address_city="",address_detail="",postcode="";
			String pay_mode_str = ParamUtil.getParameter(request, "pay_mode",true);
			if(orderChannel == Value.order_channel_itemlist){  //商品列表提交
				name = ParamUtil.getParameter(request, "name", true);
				phone_number = ParamUtil.getParameter(request, "phone_number", true);
				address_province = ParamUtil.getParameter(request, "address_province", true);
				address_city = ParamUtil.getParameter(request, "address_city", true);
				address_detail = ParamUtil.getParameter(request, "address_detail", true);
				postcode = ParamUtil.getParameter(request, "postcode");
			}
			
			JSONArray jsonArr = JSONArray.fromObject(content);
			for(Object obj : jsonArr){
				JSONObject jsonObj = (JSONObject)obj;
				String itemId = jsonObj.getString("itemId");
				String title = jsonObj.getString("title");				
				
				String sku = jsonObj.getString("sku")==null?"":jsonObj.getString("sku");
				int num = jsonObj.getInt("num");
				double price = jsonObj.getDouble("price");
				double totalPrice = jsonObj.getDouble("totalPrice");
				int isFlashSell = 0;
		
				SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);				
				FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(itemId);				
				
				if(flashSellItem != null){
					isFlashSell = 1;
					if(!FlashSellItemManager.isInFlashSellTime(flashSellItem)){
						retMap.put("status", new RespStatusBuilder(ActionStatus.NOT_IN_TIME));
						response.getWriter().print(gson.toJson(retMap));
						return;
					}
				}
				
				if(price != selfItem.getNow_price() || totalPrice != price * num){
					retMap.put("status", new RespStatusBuilder(ActionStatus.PRICE_ERROR));
					response.getWriter().print(gson.toJson(retMap));
					return;
				}
				
				Order order = new Order();
				
				order.setUid(uid);
				order.setName(name);
				order.setPhone_number(phone_number);
				order.setAddress_province(address_province);
				order.setAddress_city(address_city);
				order.setAddress_detail(address_detail);
				order.setPostcode(postcode);				
				
				String providerId = selfItem.uid;
				String parentOrderId="";
				String orderId="";
				if(parentOrderIdMap.get(providerId)!=null){//同一个商家的商品，订单号相同
					parentOrderId = parentOrderIdMap.get(providerId);
				}else{
					parentOrderId = getOrderId();
					parentOrderIdMap.put(providerId, parentOrderId);
				}				
			
				if(orderChannel == Value.order_channel_itemlist){//商品列表提交
					orderId=parentOrderId + getOrderSurfix();
				}else if(orderChannel == Value.order_channel_orderlist){
					orderId = jsonObj.getString("orderId");
				}
							
				orderIds += orderId + ",";
				
				order.setParentOrderId(parentOrderId);
				order.setOrderId(orderId);
				order.setItemId(itemId);
				order.setIcon(selfItem.getLogo_url());
				order.setTitle(title);			
				order.setSku(sku);
				order.setNum(num);
				order.setPrice(price);
				order.setTotalPrice(totalPrice);
				order.setIsFlashSell(isFlashSell);
				order.setIsUseCredit(selfItem.getIs_integral());
				order.setCreditNum(selfItem.getIntegral_num());
				order.setProviderId(selfItem.getUid());
				orderList.add(order);
				
				Log4SSY.Log(Config.suggestSystemURL, "SSYH", uid, Event.PURCHASE, Type.PRODUCT, Store.SSYH, itemId, "view", gson.toJson(selfItem.toMap()));
			}
			
			if(orderChannel == Value.order_channel_itemlist){  //商品列表提交
				logger.info("----提交订单");
				OrderDAO.getInstance().insertOrderList(orderList);				
			}
			
			orderIds = orderIds.substring(0, orderIds.length()-1);
			String paymentId = OrderDAO.getInstance().insertPaymentToOrder(orderIds);
			retMap.put("paymentId", paymentId);
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			
			// add by weifeng 2015-01-07
			//接收财付通通知的URL
			String notify_url = "";
			if (Value.PAY_MODE_WEIXIN == Integer.valueOf(pay_mode_str)){
				notify_url = "http://127.0.0.1:8180/tenpay_api_b2c/payNotifyUrl.jsp";
				PackageRequestHandler packageReqHandler = new PackageRequestHandler(request, response);//生成package的请求类 
				PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//获取prepayid的请求类
				ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);//返回客户端支付参数的请求类
				packageReqHandler.setKey(ConstantUtil.PARTNER_KEY);
				//获取token值 
				String token = AccessTokenRequestHandler.getAccessToken();
				if (!"".equals(token)) {
					//设置package订单参数
					packageReqHandler.setParameter("bank_type", "WX");//银行渠道
					packageReqHandler.setParameter("body", "测试"); //商品描述   
					packageReqHandler.setParameter("notify_url", notify_url); //接收财付通通知的URL  
					packageReqHandler.setParameter("partner", ConstantUtil.PARTNER); //商户号    
					packageReqHandler.setParameter("out_trade_no", paymentId); //商家订单号   
					packageReqHandler.setParameter("total_fee", "1"); //商品金额,以分为单位  
					packageReqHandler.setParameter("spbill_create_ip",request.getRemoteAddr()); //订单生成的机器IP，指用户浏览器端IP  
					packageReqHandler.setParameter("fee_type", "1"); //币种，1人民币   66
					packageReqHandler.setParameter("input_charset", "utf-8"); //字符编码
					
					//获取package包
					String packageValue = packageReqHandler.getRequestURL();
					
					String noncestr = WXUtil.getNonceStr();
					String timestamp = WXUtil.getTimeStamp();
					String traceid = "";
					////设置获取prepayid支付参数
					prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
					prepayReqHandler.setParameter("appkey", ConstantUtil.APP_KEY);
					prepayReqHandler.setParameter("noncestr", noncestr);
					prepayReqHandler.setParameter("package", packageValue);
					prepayReqHandler.setParameter("timestamp", timestamp);
					prepayReqHandler.setParameter("traceid", traceid);
					
					//生成获取预支付签名
					String sign = prepayReqHandler.createSHA1Sign();
					//增加非参与签名的额外参数
					prepayReqHandler.setParameter("app_signature", sign);
					prepayReqHandler.setParameter("sign_method",
							ConstantUtil.SIGN_METHOD);
					String gateUrl = ConstantUtil.GATEURL + token;
					prepayReqHandler.setGateUrl(gateUrl);
					
					//获取prepayId
					String prepayid = prepayReqHandler.sendPrepay();
					//吐回给客户端的参数
					if (null != prepayid && !"".equals(prepayid)) {
						retMap.put("appid", ConstantUtil.APP_ID);
						retMap.put("appkey", ConstantUtil.APP_KEY);
						retMap.put("noncestr", noncestr);
						retMap.put("package", "Sign=" + packageValue);
						retMap.put("partnerid", ConstantUtil.PARTNER);
						retMap.put("prepayid", prepayid);
						retMap.put("timestamp", timestamp);
						// 生成签名
						retMap.put("sign", clientHandler.createSHA1Sign());
						retMap.put("retcode", 0);
						retMap.put("retmsg", "OK");
					} else {
						retMap.put("retcode", -2);
						retMap.put("retmsg", "错误：获取prepayId失败");
					}
				} else {
					retMap.put("retcode", -1);
					retMap.put("retmsg", "错误：获取不到Token");
				}
				
			} else if (Value.PAY_MODE_ZHIFUBAO == Integer.parseInt(pay_mode_str)){
				HashMap<String, String> signMap = new HashMap<String, String>();
				notify_url = "";
				
				signMap.put("service", "mobile.securitypay.pay");
				signMap.put("partner", AlipayConfig.partner);
				signMap.put("_input_charset", AlipayConfig.input_charset);
				signMap.put("notify_url", notify_url);
				signMap.put("out_trade_no", paymentId);
				signMap.put("subject", "测试");
				signMap.put("body", "测试测试");
				signMap.put("payment_type", "1");
				signMap.put("seller_id", request.getRemoteAddr());
				signMap.put("total_fee", "0");
				
				// 生成签名
				String content2 = AlipayCore.createLinkString(AlipayCore.paraFilter(signMap));
				String sign = RSA.sign(content2, AlipayConfig.private_key, AlipayConfig.input_charset);
				
				retMap.put("partnerid", AlipayConfig.partner);
				retMap.put("input_charset", AlipayConfig.input_charset);
				retMap.put("sign_type", AlipayConfig.sign_type);
				retMap.put("sign", sign);
				retMap.put("notify_url", notify_url);
				retMap.put("retcode", 0);
				retMap.put("retmsg", "OK");
			}
			response.getWriter().print(gson.toJson(retMap));			
		}catch(Exception e){
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));
			response.getWriter().print(gson.toJson(retMap));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public String getOrderId(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(date);
	}	
	
	public String getOrderSurfix(){
		String now = System.currentTimeMillis()+"";
		return now.substring(now.length()-5, now.length());
	}

	public static void main(String[] args) {
		HashMap<String, Object> map ;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		map = new HashMap<String, Object>();
		map.put("itemId", "123");
		map.put("title", "大棉袄");
		map.put("sku", "红色,l");
		map.put("num", 2);
		map.put("price", 10);
		map.put("totalPrice", 20);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("itemId", "123");
		map.put("title", "大棉袄");
		map.put("sku", "红色,l");
		map.put("num", 2);
		map.put("price", 10);
		map.put("totalPrice", 20);		
		list.add(map);
		
		System.out.println("----------"+gson.toJson(list));

	}	
	
}
