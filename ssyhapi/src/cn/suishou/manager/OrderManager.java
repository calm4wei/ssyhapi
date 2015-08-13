package cn.suishou.manager;

import org.apache.log4j.Logger;

import cn.suishou.bean.Order;
import cn.suishou.bean.SelfItem;
import cn.suishou.dao.OrderDAO;
import cn.suishou.dao.SelfItemDAO;
import cn.suishou.ramdata.ItemPurchaseLimitedCacher;
import cn.suishou.ramdata.ItemSoldCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.utils.SkuUtil;

public class OrderManager {
	protected static Logger logger = Logger.getLogger(OrderManager.class);
	private static Object lock = new Object();
	
	public static void updateStock(String[] orderIds){
		for(String orderId : orderIds){
			updateStock(orderId);
		}
	}
	
	public static boolean updateStock(String orderId){
		try{
			Order order = OrderDAO.getInstance().getOrder(orderId, null);
			String itemId = order.getItemId();
			String sku = order.getSku();
			int num = order.getNum();			
			synchronized (lock) {
				SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);
				selfItem = SkuUtil.updateSelfItemSku(selfItem, sku, num);
			
				SelfItemDAO.getInstance().updateStock(selfItem);
				SelfItemCacher.getInstance().setItem(selfItem);
				ItemSoldCacher.getInstance().setItemSoldNum(itemId, order.getNum());
				return true;
			}
		}catch(Exception e){
			logger.error("error stack", e);
			return false;
		}
	}
	
	public static void updateUserPurchaseNum(String[] orderIds){
		for(String orderId : orderIds){
			Order order = OrderDAO.getInstance().getOrder(orderId, null);
			String uid = order.getUid();
			String itemId = order.getItemId();
			int num = order.getNum();
			updateUserPurchaseNum(uid, itemId, num);
		}
	}
	
	public static void updateUserPurchaseNum(String uid, String itemId, int num){
		ItemPurchaseLimitedCacher.getInstance().updateUserPurchaseNum(uid, itemId, num);
	}
	

	
	
}
