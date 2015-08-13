package cn.suishou.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.FlashSellItem;
import cn.suishou.bean.Order;
import cn.suishou.bean.SelfItem;
import cn.suishou.common.Value;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.OrderDAO;
import cn.suishou.manager.FlashSellItemManager;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.SkuUtil;
import cn.suishou.utils.StringUtil;

@WebServlet("/api/reviewOrder")
public class ReviewOrder extends HttpServlet {
	private static Logger logger = Logger.getLogger(SubmitOrder.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		List<HashMap<String,Object>> orderList = new ArrayList<HashMap<String,Object>>();
		int pageSize = 10;
		int totalPage = 0 ;
		int count = 0;
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);	
			int page = ParamUtil.getParameterInt(request, "page");
			List<Order> list = OrderDAO.getInstance().getOrderListByUid(uid, page, pageSize);
			count = OrderDAO.getInstance().getOrderNO(uid);
			totalPage = count / pageSize ;
			if((count % pageSize)>0){
				totalPage ++;
			}
		
			for(Order order : list){
				String deliveryTime = order.getDelivery_time();
				String itemId = order.getItemId();
				String sku = order.getSku();
				
				int num = order.getNum();
				double price = order.getPrice();
				
				HashMap<String, Object> map = order.toMap();
				
				if(deliveryTime!=null && !"".equals(deliveryTime)){
					if(StringUtil.datetime2long(deliveryTime)-System.currentTimeMillis()>Value.returnOrderEnableDays*24*3600){
						map.put("returnOrderEnable", 0);
					}
				}
				
				SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);				
				FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(itemId);				
			
				if(selfItem == null){					
					map.put("validateStatus", Value.item_validte_status_unsale);
				}else if(!SkuUtil.checkSelfItemSkuStock(selfItem, sku, num)){					
					map.put("validateStatus", Value.item_validte_status_stock_not_enought);
				}else if(price != selfItem.getNow_price()){					
					map.put("validateStatus", Value.item_validte_status_price_changed);
				}else if(flashSellItem != null){					
					if(!FlashSellItemManager.isInFlashSellTime(flashSellItem)){						
						map.put("validateStatus", Value.item_validte_status_no_on_time);
					}else{						
						map.put("validateStatus", Value.item_validte_status_normal);
					}
				}else{				
					map.put("validateStatus", Value.item_validte_status_normal);
				}		
				orderList.add(map);
			}
			retMap.put("orderList", orderList);
			retMap.put("page", page);
			retMap.put("totalPage", totalPage);
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
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

	
	
}
