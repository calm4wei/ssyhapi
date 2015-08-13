package cn.suishou.ramdata;

import org.apache.log4j.Logger;

import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

public class ItemSoldCacher {
	private static final Logger logger = Logger.getLogger(ItemSoldCacher.class);
	private static String cacheKey = X.CachePrefix.ITEM_SOLD;
	private static ItemSoldCacher instance;

	public static ItemSoldCacher getInstance() {
		return instance == null ? (instance = new ItemSoldCacher()) : instance;
	}

	public int getItemSoldNum(String itemId) {
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
	
	public void setItemSoldNum(String itemId, int addNum){
		int soldNum = getItemSoldNum(itemId) + addNum;
		new JedisHashManager(cacheKey).add(itemId, soldNum+"");
	}
	
	
	
}
