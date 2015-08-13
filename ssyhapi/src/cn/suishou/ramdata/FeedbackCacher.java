package cn.suishou.ramdata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import cn.suishou.bean.Feedback;
import cn.suishou.common.X;
import cn.suishou.redis.RedisExecutor;
import cn.suishou.redis.RedisRunner;
import cn.suishou.redis.executor.JedisSortedSetManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 反馈与回复Cacher
 * @author  haol
 * @date	2015-01-07
 */
public class FeedbackCacher 
{
	/** 反馈与回复Cache Key */
	private static String cacheKey = X.CachePrefix.CACHE_USER_FEEDBACK;
	/** 反馈与回复Cache单例 */
	private static FeedbackCacher instance;
	/** 获取反馈与回复Cache单例 */
	public static FeedbackCacher getInstance() 
	{
		if (instance == null) {
			instance = new FeedbackCacher();
		}
		return instance;
	}

	/**
	 * 将单条反馈与回复加入反馈与回复zset，以id为score。用户的反馈，与其收到的回复放一起，按id排列
	 * @param bean	反馈与回复Bean
	 * @return		success or not
	 */
	public boolean addFeedback(Feedback bean) 
	{
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + bean.getUid() + "." + bean.getType());
		String json = new Gson().toJson(bean, Feedback.class);
		long addNum = jedisManager.add(bean.getId(), json);
		return addNum > 0 ? true : false;
	}

	/**
	 * 批量将用户的反馈与回复list增加到zset
	 * @param list	用户的反馈与回复list
	 * @return		success or not
	 */
	public boolean addFeedbackList(final List<Feedback> list, final int type)
	{
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>() {
			public Boolean run(Jedis jedis) {
				Pipeline pipeline = jedis.pipelined();
				for (Feedback bean : list) {
					String json = new Gson().toJson(bean, Feedback.class);
					pipeline.zadd(cacheKey + bean.getUid() + "." + type, bean.getId(), json);
				}
				return true;
			}} ;
		RedisExecutor<Boolean> executor = new RedisExecutor<Boolean>();
		return executor.exe(runner);
	}
	
	/**
	 * 批量将用户的反馈与回复list增加到zset
	 * @param list	用户的反馈与回复list
	 * @return		管道操作结果
	 */
	public long addFeedbackList(Map<Double, String> scoreMembers, String uid, int type)
	{
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid + "." + type);
		long response = jedisManager.pipeAddScoreMembers(scoreMembers);
		return response;
	}

	/**
	 * 根据id、type，从用户反馈与回复中zset中 delete该条信息
	 * @param bean	反馈与回复Bean
	 * @return		success or not
	 */
	public boolean deleteFeedback(Feedback bean) 
	{
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + bean.getUid() + "." + bean.getType());
		long deleteNum = jedisManager.deleteByScore(bean.getId(), bean.getId());
		return deleteNum > 0 ? true : false;
	}

	/**
	 * 根据uid加载用户type类型的反馈与回复
	 * @param uid		app uid
	 * @param type		反馈类型，1：订单问题；2：功能改进
	 * @param cursorId	app第一次请求不传cursorId，从其zset的最后往前检索，cursorId作为上一次检索到的id
	 * @param pageSize	分页页大小
	 * @return			用户type类型的反馈与回复JsonArray
	 */
	public JsonArray getJsonArrayByUid(String uid, int type, long cursorId, int pageSize) 
	{
		JsonArray jsonArray = new JsonArray();
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid + "." + type);
		
		Map<Double, String> scoreMap = jedisManager.getScoreAndRange(cursorId, 0);
		if (MapUtils.isEmpty(scoreMap)) {
			return jsonArray;
		}
		Iterator<Entry<Double, String>> iterator = scoreMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Double, String> entry = iterator.next();
			// 取bean对应的json字串，转为json对象，加入数组
			String jsonStr = entry.getValue();
			if (StringUtils.isNotBlank(jsonStr)) {
				JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
				jsonArray.add(jsonObject);
			}
			if (jsonArray.size() == pageSize) {
				break;
			}
		}
		return jsonArray;
	}

	/**
	 * 根据id、uid加载用户type类型的反馈与回复
	 * @param uid		app uid
	 * @param id		反馈与回复ID
	 * @param type		反馈类型，1：订单问题；2：功能改进
	 * @return 			用户type类型的反馈与回复JsonObject
	 */
	public JsonObject getFeedbackJson(String uid, long id, int type) 
	{
		JsonObject jsonObject = new JsonObject();
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid + "." + type);
		Map<Double, String> scoreMap = jedisManager.getScoreAndRange(id, id);
		if (MapUtils.isNotEmpty(scoreMap)) {
			String addrJsonStr = scoreMap.get(new Double(id).doubleValue());
			if (StringUtils.isNotBlank(addrJsonStr)) {
				jsonObject = new JsonParser().parse(addrJsonStr).getAsJsonObject();
			}
		}
		return jsonObject;
	}

	/**
	 * 根据id、uid加载用户type类型的反馈与回复
	 * @param uid		app uid
	 * @param id		反馈与回复ID
	 * @param type		反馈类型，1：订单问题；2：功能改进
	 * @return 			用户type类型的反馈与回复bean
	 */
	public Feedback getFeedbackBean(String uid, long id, int type) 
	{
		Feedback bean = new Feedback();
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid + "." + type);
		Map<Double, String> scoreMap = jedisManager.getScoreAndRange(id, id);
		if (MapUtils.isNotEmpty(scoreMap)) {
			String jsonStr = scoreMap.get(new Double(id).doubleValue());
			if (StringUtils.isNotBlank(jsonStr)) {
				bean = new Gson().fromJson(jsonStr, Feedback.class);
			}
		}
		return bean;
	}
	
}
