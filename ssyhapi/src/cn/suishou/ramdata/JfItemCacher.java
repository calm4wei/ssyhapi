package cn.suishou.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.suishou.bean.JfItem;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisSortedSetManager;

import com.google.gson.Gson;

public class JfItemCacher {

	private static String cacheKey = X.CachePrefix.JF_ITEM;
	private static JfItemCacher instance = null;

	private JfItemCacher() {
	}

	public static JfItemCacher getInstance() {
		return instance == null ? (instance = new JfItemCacher()) : instance;
	}
	
	public List<JfItem> getJfItems(){
		Gson gson = new Gson();
		List<JfItem> ret = new ArrayList<JfItem>();                                                                                                     
		Set<String> JfItemSet = new JedisSortedSetManager(cacheKey).zrange(Integer.parseInt("-1000000"), Integer.MAX_VALUE); 
		
		if(JfItemSet != null && JfItemSet.size() > 0){
			for(String JfItemSetString : JfItemSet){
				JfItem jfItem = gson.fromJson(JfItemSetString, JfItem.class);
				ret.add(jfItem);
			}			
		}
		return ret;
	}

}
