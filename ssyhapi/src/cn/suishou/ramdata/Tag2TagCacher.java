
package cn.suishou.ramdata;

import java.util.ArrayList;
import java.util.List;

import cn.suishou.bean.Tag;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

public class Tag2TagCacher {
	private static String cacheKey = X.CachePrefix.TAG_TO_TAG;
	private static Tag2TagCacher instance = null;
	
	public static Tag2TagCacher getInstance(){
		if(instance == null) instance = new Tag2TagCacher();
		return instance;
	}
	
	public List<Tag> getTagsByParentId(String ptagid){
		List<Tag> ret = new ArrayList<Tag>();
		
		String tagids = new JedisHashManager(cacheKey).get(ptagid);
		if(tagids != null && !"".equals(tagids)){
			Tag allTag = TagCacher.getInstance().getTag(ptagid);
			allTag.setName("全部");
			ret.add(allTag);
			String[] tagidss = tagids.split(";");
			for(String tagid : tagidss){
				Tag bean = TagCacher.getInstance().getTag(tagid);
				if(bean != null){
					ret.add(bean);
				}
			}
			
			return ret;
		}else{
			return null;
		}
	}



}