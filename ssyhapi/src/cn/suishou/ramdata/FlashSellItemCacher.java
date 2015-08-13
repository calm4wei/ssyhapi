package cn.suishou.ramdata;

import org.apache.log4j.Logger;

import cn.suishou.bean.FlashSellItem;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

import com.google.gson.Gson;

public class FlashSellItemCacher {
	private static final Logger logger = Logger.getLogger(FlashSellItemCacher.class);
	private static String cacheKey = X.CachePrefix.FLASH_SELL_ITEM_CACHE;
	private static FlashSellItemCacher instance;

	public static FlashSellItemCacher getInstance() {
		return instance == null ? (instance = new FlashSellItemCacher()) : instance;
	}

	public FlashSellItem getFlashSellItem(String itemId) {
		FlashSellItem bean = null;
		Gson gson = new Gson();
		try{
			String json = new JedisHashManager(cacheKey).get(itemId);	
			
			if (json != null) {
				bean = gson.fromJson(json, FlashSellItem.class);
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}

		return bean;
	}
	
}
