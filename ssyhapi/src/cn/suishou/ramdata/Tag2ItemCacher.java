package cn.suishou.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.suishou.bean.Tag2item;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisSortedSetManager;

import com.google.gson.Gson;

public class Tag2ItemCacher {

	private static String cacheKey = X.CachePrefix.TAG_TO_ITEM;
	private static Tag2ItemCacher instance = null;

	private Tag2ItemCacher() {
	}

	public static Tag2ItemCacher getInstance() {
		return instance == null ? (instance = new Tag2ItemCacher()) : instance;
	}
	
	public long getItemsSizeByTagid(String tagid) {
		long size = new JedisSortedSetManager(cacheKey + tagid).size();
		return size;
	}
	
	public List<Tag2item> getItemsByTagid(String tagid, int page, int pageSize){
		Gson gson = new Gson();
		List<Tag2item> ret = new ArrayList<Tag2item>();                                                                                                     
		Set<String> tag2itemSet = new JedisSortedSetManager(cacheKey + tagid).zrange((page - 1)*pageSize, (page * pageSize - 1)); 
		
		if(tag2itemSet != null && tag2itemSet.size() > 0){
			for(String tag2itemStr : tag2itemSet){
				Tag2item tag2item = gson.fromJson(tag2itemStr, Tag2item.class);
				ret.add(tag2item);
			}			
		}
		return ret;
	}
	
	public int getTagItemCount(String tagId){
		long total = new JedisSortedSetManager(cacheKey + tagId).zcount(Double.parseDouble("-1000000"), Double.MAX_VALUE);
		return (int) total;
	}
	
	public int getTotalPageNum(String tagId,int pageSize) {
		int total = getTagItemCount(tagId);
		int ys = (int) (total%pageSize);
		int ret = (int) ((total - ys)/pageSize);
		if(ys > 0)
			ret++;
		return ret;
	}

	public static void main(String[] args){
		String tagId = "1";
		List<Tag2item> tag2items = Tag2ItemCacher.getInstance().getItemsByTagid(tagId,1,20);
		System.out.println(tag2items.size());	
	}
}
