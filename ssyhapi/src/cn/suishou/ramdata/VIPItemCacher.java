package cn.suishou.ramdata;

import org.apache.log4j.Logger;

import cn.suishou.bean.VIPItem;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

import com.google.gson.Gson;

public class VIPItemCacher {
	private static final Logger logger = Logger.getLogger(VIPItemCacher.class);
	private static String cacheKey = X.CachePrefix.ITEM_CACHE_VIP;
	private static VIPItemCacher instance;

	public static VIPItemCacher getInstance() {
		return instance == null ? (instance = new VIPItemCacher()) : instance;
	}

	public VIPItem getItem(String itemId) {
		VIPItem bean = new VIPItem();
		Gson gson = new Gson();
		try{
			String json = new JedisHashManager(cacheKey).get(itemId);			
			
			if (json != null) {
				bean = gson.fromJson(json, VIPItem.class);
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}
		return bean;
	}
	
}
