package cn.suishou.redis.superDiscount;

import cn.suishou.redis.executor.JedisHashManager;


public class DataCacheCacher {
	private static String cacheKay="ssyh_superDiscount_dataCache";
	private static DataCacheCacher instance=null;
	public static DataCacheCacher getInstance(){
		if(instance==null){
			instance=new DataCacheCacher();
		}
		return instance;
	}
	private DataCacheCacher (){}
	
	public void add(String date,String data){
		new JedisHashManager(cacheKay).add(date, data);
	}
	public String get(String date){
		return new JedisHashManager(cacheKay).get(date);
	}
}
