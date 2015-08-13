package cn.suishou.redis.superDiscount;

import cn.suishou.bean.superDiscount.SuperDiscountBean;
import cn.suishou.redis.executor.JedisHashManager;

import com.google.gson.Gson;


public class SuperDiscountCacher {

	private static String cacheKay="ssyh_super_discount";
	private static SuperDiscountCacher instance=null;
	public static SuperDiscountCacher getInstance(){
		if(instance==null){
			instance=new SuperDiscountCacher();
		}
		return instance;
	}
	private SuperDiscountCacher(){}
	
	public void add(SuperDiscountBean sb){
		new JedisHashManager(cacheKay).add( sb.getDate()+"_"+sb.getItemId(),new Gson().toJson(sb,SuperDiscountBean.class));
	}
	public void del(String itemId,String date){
		new JedisHashManager(cacheKay).delete(date+"_"+itemId);
	}
	public String get(String date,String itemId){
		String str=new JedisHashManager(cacheKay).get(date+"_"+itemId);
		return str;
	}
}
