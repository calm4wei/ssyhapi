package cn.suishou.ramdata;

import java.util.Map;

import org.apache.log4j.Logger;

import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;


public class HomePageBannerCacher {
	private static final Logger logger = Logger.getLogger(HomePageBannerCacher.class);
	private static String cacheKey = X.CachePrefix.HOME_PAGE_BANNER;
	private static HomePageBannerCacher instance;

	public static HomePageBannerCacher getInstance() {
		return instance == null ? (instance = new HomePageBannerCacher()) : instance;
	}

	public Map<String, String> getAll() {	
		try{
			Map<String, String>	map = new JedisHashManager(cacheKey).getAll();			
			return map;			
		}catch(Exception e){
			logger.error("error stack",e);
			return null;
		}	
	}
	
}
