package cn.suishou.ramdata;

import java.util.HashSet;

import com.google.gson.Gson;

import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisKeyManager;

public class FavoriteCacher {
	private static String cacheKey = X.CachePrefix.FAVORITE_CACHE;
	private static FavoriteCacher instance;

	public static FavoriteCacher getInstance() {
		return instance == null ? (instance = new FavoriteCacher()) : instance;
	}


	public void addFavorite(String uid,String itemId,String itemChannel) {
		Gson gson = new Gson();		
		HashSet<String> favSet = getFavorite(uid);		
		if(favSet == null){
			favSet = new HashSet<String>();
		}
		favSet.add(itemId+","+itemChannel);
		new JedisKeyManager(cacheKey+uid).set(gson.toJson(favSet));	
	}	
	

	public void deleteFavorite(String uid,String itemId,String itemChannel) {
		Gson gson = new Gson();
		HashSet<String> favSet = getFavorite(uid);
		favSet.remove(itemId+","+itemChannel);
		new JedisKeyManager(cacheKey+uid).set(gson.toJson(favSet));	
	}
	
	public void deleteAllFavorite(String uid) {
		new JedisKeyManager(cacheKey+uid).del();
	}

	@SuppressWarnings("unchecked")
	public HashSet<String> getFavorite(String uid) {	
		Gson gson = new Gson();
		String json = new JedisKeyManager(cacheKey+uid).get();					
		HashSet<String> favSet = gson.fromJson(json, HashSet.class);
		if(favSet == null){
			return new HashSet<String>();
		}else{
			return favSet;
		}
	}
	
}
