package cn.suishou.redis.superDiscount;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.suishou.redis.executor.JedisHashManager;
import cn.suishou.servlet.superDiscount.SuperDiscountServlet;


public class RelationCacher {

	private static String cacheKay="ssyh_relation";
	
	private static RelationCacher instance=null;
	public static RelationCacher getInstance(){
		if(instance==null){
			instance=new RelationCacher();
		}
		return instance;
	}
	private RelationCacher(){}
	
	public void add(String date,String itemIds){
		new JedisHashManager(cacheKay).add(date, itemIds);
	}
	
	public void del(String date){
		new JedisHashManager(cacheKay).delete(date);
	}
	
	public String get(String date){
		return new JedisHashManager(cacheKay).get(date);
	}
	public String getTrailer() throws ParseException {
		List<Long> list=new ArrayList<Long>();
		Map<String ,String> map= new JedisHashManager(cacheKay).getAll();
		
		Iterator<String> it=map.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			long l=SuperDiscountServlet.sdf.parse(key).getTime();
			if(l>System.currentTimeMillis()){
				list.add(l);
			}
		}
		Object[] obj = list.toArray();
		Arrays.sort(obj);
		String date=SuperDiscountServlet.sdf.format(obj[0]);
		return date+","+map.get(date);
	}
}
