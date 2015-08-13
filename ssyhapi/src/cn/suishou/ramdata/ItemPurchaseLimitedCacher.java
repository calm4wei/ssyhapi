package cn.suishou.ramdata;

import org.apache.log4j.Logger;

import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

public class ItemPurchaseLimitedCacher {
	private static final Logger logger = Logger.getLogger(ItemPurchaseLimitedCacher.class);
	
	private static String ITEM_PURCHASE_LIMITED = X.CachePrefix.ITEM_PURCHASE_LIMITED;
	private static String cacheKey_user_purchase_item = X.CachePrefix.USR_PURCHASE_ITEM;
	private static ItemPurchaseLimitedCacher instance;

	public static ItemPurchaseLimitedCacher getInstance() {
		return instance == null ? (instance = new ItemPurchaseLimitedCacher()) : instance;
	}

	public int getUserPurchaseNum(String uid, String itemId) {
		try{
			String num = new JedisHashManager(cacheKey_user_purchase_item+uid).get(itemId);
			if (num != null) {
				return Integer.valueOf(num);
			}else{
				return 0;
			}
		}catch(Exception e){
			logger.error("error stack",e);
			return 0;
		}
	}
	
	public void updateUserPurchaseNum(String uid, String itemId, int addNum){
		int purchaseNum = getUserPurchaseNum(uid, itemId) + addNum;		
		JedisHashManager jedis = new JedisHashManager(cacheKey_user_purchase_item+uid);
		jedis.add(itemId, purchaseNum+"");
		jedis.expire(3600*24*365);
	}
	
	public int getItemPurchaseLimitedNum(String itemId) {		
		try{
			String num = new JedisHashManager(ITEM_PURCHASE_LIMITED).get(itemId);
			if (num != null) {
				return Integer.valueOf(num);
			}else{
				return -1;
			}
		}catch(Exception e){
			logger.error("error stack",e);
			return -1;
		}
	}
	
}
