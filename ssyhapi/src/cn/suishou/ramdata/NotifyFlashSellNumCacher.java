package cn.suishou.ramdata;

import org.apache.log4j.Logger;

import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

public class NotifyFlashSellNumCacher {
	private static final Logger logger = Logger.getLogger(NotifyFlashSellNumCacher.class);
	private static String cacheKey = X.CachePrefix.NOTIFY_FLASH_SELL_NUM;
	private static NotifyFlashSellNumCacher instance;

	public static NotifyFlashSellNumCacher getInstance() {
		return instance == null ? (instance = new NotifyFlashSellNumCacher()) : instance;
	}

	public int getNotifyFlashSellNum(String itemId) {
		try{
			String num = new JedisHashManager(cacheKey).get(itemId);
			if (num != null) {
				return Integer.valueOf(num);
			}else{
				return 100;
			}
		}catch(Exception e){
			logger.error("error stack",e);
			return 100;
		}
	}
	
	public void addNotifyFlashSellNum(String itemId){
		int num = getNotifyFlashSellNum(itemId) + 1;
		new JedisHashManager(cacheKey).add(itemId, num+"");
	}
	
	public void reduceNotifyFlashSellNum(String itemId){
		int num = getNotifyFlashSellNum(itemId) - 1;
		new JedisHashManager(cacheKey).add(itemId, num+"");
	}
	
	
	
}
