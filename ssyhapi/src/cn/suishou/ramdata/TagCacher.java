package cn.suishou.ramdata;

import cn.suishou.bean.Tag;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

import com.google.gson.Gson;

public class TagCacher {
	private static String cacheKey = X.CachePrefix.TAG_ALL_TAGS;

	private static TagCacher instance;
	
	private TagCacher() {
	}
	
	public static TagCacher getInstance() {
		return instance == null ? (instance = new TagCacher()) : instance;
	}

	
	public Tag getTag(final String tagId) {
		String source = new JedisHashManager(cacheKey).get(tagId);
		Tag tag = null;
		if (source != null) {
			tag = new Gson().fromJson(source, Tag.class);
		}
		return tag;
	}
	
}
