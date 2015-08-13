package cn.suishou.ramdata;

import java.util.HashMap;
import java.util.HashSet;

import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisKeyManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NotifySuperDiscountItemCacher {
	private static String cacheKey = X.CachePrefix.NOTIFY_SUPER_DISCOUNT_ITEM_CACHE;
	private static String itemInfocacheKey = X.CachePrefix.NOTIFY_SUPER_DISCOUNT_ITEM_INFO_CACHE;
	private static NotifySuperDiscountItemCacher instance;

	public static NotifySuperDiscountItemCacher getInstance() {
		return instance == null ? (instance = new NotifySuperDiscountItemCacher()) : instance;
	}
	
	public void addNotifyUser(String uid,String date,String itemId,String platform) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		HashSet<String> userSet = getNotifyUser(date, itemId);
		if(userSet == null){
			userSet = new HashSet<String>();
		}
		userSet.add(uid+","+platform);
		new JedisKeyManager(cacheKey+date+itemId).set(gson.toJson(userSet));
	}

	public void deleteNotifyUser(String uid,String date,String itemId,String platform) {
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		HashSet<String> userSet = getNotifyUser(date, itemId);
		userSet.remove(uid+","+platform);
		new JedisKeyManager(cacheKey+date+itemId).set(gson.toJson(userSet));	
	}
	
	public void deleteNotifyUserAll(String date,String itemId) {
		new JedisKeyManager(cacheKey+date+itemId).del();	
	}

	@SuppressWarnings("unchecked")
	public HashSet<String> getNotifyUser(String date, String itemId) {	
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = new JedisKeyManager(cacheKey+date+itemId).get();					
		HashSet<String> userSet = gson.fromJson(json, HashSet.class);
		return userSet;
	}

	public void setItemInfo(String date,String itemId,String title, String clickurl, String icon, int itemChannel){
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("clickurl", clickurl);
		map.put("icon", icon);
		map.put("itemChannel", itemChannel);
		new JedisKeyManager(itemInfocacheKey+date+itemId).set(gson.toJson(map));
	}
	
	public void deleteItemInfo(String date,String itemId) {
		new JedisKeyManager(cacheKey+date+itemId).del();
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getItemInfo(String date, String itemId) {	
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String json = new JedisKeyManager(cacheKey+date+itemId).get();					
		HashMap<String, Object> map = gson.fromJson(json, HashMap.class);
		return map;
	}
}
