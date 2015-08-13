package cn.suishou.redis.superDiscount;

import cn.suishou.redis.executor.JedisHashManager;

public class EditorCacher {

	private static String cacheKay="ssyh_editor";
	private static EditorCacher instance=null;
	public static EditorCacher getInstance(){
		if(instance==null){
			instance=new EditorCacher();
		}
		return instance;
	}
	private EditorCacher(){}
	
	
	public String get(String date){
		String str =new JedisHashManager(cacheKay).get(date);
		return str;
	}
}
