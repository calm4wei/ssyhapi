package cn.suishou.ramdata;

import org.apache.log4j.Logger;

import cn.suishou.bean.SelfItem;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SelfItemCacher {
	private static final Logger logger = Logger.getLogger(SelfItemCacher.class);
	private static String cacheKey = X.CachePrefix.ITEM_CACHE_SELF;
	private static SelfItemCacher instance;

	private SelfItemCacher() {
	}

	public static SelfItemCacher getInstance() {
		return instance == null ? (instance = new SelfItemCacher()) : instance;
	}

	public SelfItem getItem(String itemId) {
		SelfItem bean = null;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		try{
			String json = new JedisHashManager(cacheKey).get(itemId);			
			
			if (json != null) {
				bean = gson.fromJson(json, SelfItem.class);
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}

		return bean;
	}
	
	public void setItem(SelfItem selfItem){
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		new JedisHashManager(cacheKey).add(selfItem.getId(), gson.toJson(selfItem));
	}
	
}
