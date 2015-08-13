package cn.suishou.ramdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.suishou.bean.HomePageAd;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisSortedSetManager;
import cn.suishou.utils.StringUtil;

import com.google.gson.Gson;


public class HomePageAdCacher {

	private static String cacheKey = X.CachePrefix.HOME_PAGE_AD;	

	static HomePageAdCacher cacher = new HomePageAdCacher();
	
	public static HomePageAdCacher getInstance(){
		return cacher;
	}
	
	public List<HomePageAd> getAdList(){
		Set<String> set = new JedisSortedSetManager(cacheKey).getAll();
		
		List<HomePageAd> list = new ArrayList<HomePageAd>();
		Iterator<String> it = set.iterator();
		Gson g = new Gson();
		while (it.hasNext()) {  
		  String str = it.next();
		  HomePageAd ad = g.fromJson(str, HomePageAd.class);
		  long now = System.currentTimeMillis();
		  long startTime = StringUtil.datetime2long(ad.getStartTimestamp());
		  long endTime = StringUtil.datetime2long(ad.getEndTimestamp());
		  if(startTime < now && endTime >now){
			  list.add(ad);
		  }
		}  
		return list;
	}
	
}
