package cn.suishou.ramdata;

import org.apache.log4j.Logger;

import cn.suishou.bean.TaobaoItem;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

import com.google.gson.Gson;

public class TaobaoItemCacher {
	private static final Logger logger = Logger.getLogger(TaobaoItemCacher.class);
	private static String cacheKey = X.CachePrefix.ITEM_CACHE_TAOBAO;
	private static TaobaoItemCacher instance;

	private TaobaoItemCacher() {
	}

	public static TaobaoItemCacher getInstance() {
		return instance == null ? (instance = new TaobaoItemCacher()) : instance;
	}

	public TaobaoItem getItem(String itemId) {
		TaobaoItem bean = new TaobaoItem();
		Gson gson = new Gson();
		try{
			String json = new JedisHashManager(cacheKey).get(itemId);			
			
			if (json != null) {
				bean = gson.fromJson(json, TaobaoItem.class);
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}

		return bean;
	}
	
}
