package cn.suishou.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.CartItem;
import cn.suishou.bean.FlashSellItem;
import cn.suishou.bean.SelfItem;
import cn.suishou.common.Value;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.CartDAO;
import cn.suishou.manager.FlashSellItemManager;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.SkuUtil;

@WebServlet("/api/cart")
public class Cart extends HttpServlet {
	private static Logger logger = Logger.getLogger(Cart.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			String type = ParamUtil.getParameter(request, "type", true);	//add 、 delete、get
			String uid =  ParamUtil.getParameter(request, "uid", true);	
			
			if(type.equals("add")){
				String itemId = ParamUtil.getParameter(request, "itemId", true);
				String title =  ParamUtil.getParameter(request, "title", true);	
				String icon =  ParamUtil.getParameter(request, "icon", true);	
				double price =  ParamUtil.getParameterDouble(request, "price");	
				int num =  ParamUtil.getParameterInt(request, "num");	
				String sku =  ParamUtil.getParameter(request, "sku");				
				
				CartItem cartItem = new CartItem();
				cartItem.setUid(uid);
				cartItem.setItemId(itemId);
				cartItem.setIcon(icon);
				cartItem.setTitle(title);
				cartItem.setPrice(price);
				cartItem.setNum(num);
				cartItem.setSku(sku);
				CartDAO.getInstance().insertCartItem(cartItem);
							
			}else if(type.equals("delete")){
				String[] itemIdSet =  ParamUtil.getParameter(request, "itemIds", true).split(",");						
				CartDAO.getInstance().deleteCartItem(uid, itemIdSet);
			}else if(type.equals("get")){
				ArrayList<HashMap<String, Object>> cartList = new ArrayList<HashMap<String, Object>>();
				int page = ParamUtil.getParameterInt(request, "page", 1);
				int pageSize=Value.page_size;
				int totalPage = 0 ;
				int count = CartDAO.getInstance().getCartItemCount(uid);
				totalPage = count / pageSize ;
				if((count % pageSize)>0){
					totalPage ++;
				}
				ArrayList<CartItem> cartItemList = CartDAO.getInstance().getCart(uid, page, pageSize);
				for(CartItem cartItem : cartItemList){
					HashMap<String, Object> map = cartItem.toMap();
					String itemId = cartItem.getItemId();
					int num = cartItem.getNum();
					String sku = cartItem.getSku();
					double price = cartItem.getPrice();
					
					try{
						SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);				
						FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(itemId);				
						
						if(flashSellItem != null){					
							if(!FlashSellItemManager.isInFlashSellTime(flashSellItem)){
								map.put("validateStatus", Value.item_validte_status_no_on_time);
							}
						}else if(selfItem == null){
							map.put("validateStatus", Value.item_validte_status_unsale);
						}else if(!SkuUtil.checkSelfItemSkuStock(selfItem, sku, num)){
							map.put("validateStatus", Value.item_validte_status_stock_not_enought);
						}else if(price != selfItem.getNow_price()){
							map.put("validateStatus", Value.item_validte_status_price_changed);
						}else{
							map.put("validateStatus", Value.item_validte_status_normal);
						}						
					}catch(Exception e){
						map.put("validateStatus", Value.item_validte_status_unsale);
					}
					
					cartList.add(map);						
				}			
		
				retMap.put("cartItemList", cartList);
				retMap.put("page", page);
				retMap.put("totalPage", totalPage);				
			}
			
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));			
			response.getWriter().print(gson.toJson(retMap));			
		}catch(Exception e){
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));
			response.getWriter().print(gson.toJson(retMap));
		}
	}
	
	public String getDay(long current){	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(current);		
	}
	
	public boolean ifOnTime(long current){   
		SimpleDateFormat sdf=new SimpleDateFormat("HH");
		String h=sdf.format(current);
		if(Integer.parseInt(h)>Value.super_discount_start_time){
			return true;
		}
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	

	
}
