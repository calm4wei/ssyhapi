package cn.suishou.ramdata;

import java.util.HashSet;

import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisKeyManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NotifyFlashSellItemCacher {
	private static String cacheKey = X.CachePrefix.NOTIFY_FLASH_SELL_ITEM_CACHE;
	private static NotifyFlashSellItemCacher instance;

	public static NotifyFlashSellItemCacher getInstance() {
		return instance == null ? (instance = new NotifyFlashSellItemCacher()) : instance;
	}
	
	public void addNotifyUser(String uid,String itemId,String platform) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		HashSet<String> userSet = getNotifyUser(itemId);
		userSet.add(uid+","+platform);
		new JedisKeyManager(cacheKey+itemId).set(gson.toJson(userSet));
	}

	public void deleteNotifyUser(String uid,String itemId,String platform) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		HashSet<String> userSet = getNotifyUser(itemId);
		userSet.remove(uid+","+platform);
		new JedisKeyManager(cacheKey+itemId).set(gson.toJson(userSet));	
	}
	
	public void deleteNotifyUserAll(String itemId) {
		new JedisKeyManager(cacheKey+itemId).del();	
	}

	@SuppressWarnings("unchecked")
	public HashSet<String> getNotifyUser(String itemId) {	
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = new JedisKeyManager(cacheKey+itemId).get();					
		HashSet<String> userSet = gson.fromJson(json, HashSet.class);
		if(userSet == null){
			return new HashSet<String>();
		}else{
			return userSet;
		}		
	}
	
}
