package cn.suishou.ramdata;

import java.util.List;
import java.util.Map;

import cn.suishou.bean.MAUStatsBean;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisHashManager;

/**
 * 用户月度活跃时长统计Cacher<br>
 * hash类型维护统计数据，key为ssyh_mau_stats_hash.yyyyMM，过期时间为2个月<br>
 * field为uid，value为用户月度停留时长，豪秒
 * @author  haol
 * @date	2014-01-06
 */
public class MAUStatsCacher 
{
	/** 用户月度活跃时长统计Cache Key */
	private static String cacheKey = X.CachePrefix.CACHE_MAU_STATS;
	/** 用户月度活跃时长统计Cache单例 */
	private static MAUStatsCacher instance;
	/** 获取用户月度活跃时长统计Cache单例 */
	public static MAUStatsCacher getInstance() 
	{
		if (instance == null) {
			instance = new MAUStatsCacher();
		}
		return instance;
	}
	
	/**
	 * 为指定 user 增加月度在线时长（毫秒数）
	 * @param bean 用户月度活跃时长统计Bean
	 * @param monthStr	统计月度，yyyyMM
	 * @return	       执行 HINCRBY 命令之后，哈希表 key 中域 field 的值
	 */
	public long increaseMAU(MAUStatsBean bean, String monthStr) 
	{
		JedisHashManager jedisManager = new JedisHashManager(cacheKey + monthStr);
		// 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
		long value = jedisManager.increaseBy(bean.getUid(), bean.getStayMillis());
		
		return value;
	}

	/**
	 * 为指定 user 增加月度在线时长（毫秒数）
	 * @param fieldList 月度活跃uid分页清单
	 * @param monthStr	统计月度，yyyyMM
	 * @return	       	fieldList对应的valueList，保存的是用户本月累计在线时长
	 */
	public List<String> hmgetValueList(List<String> fieldList, String monthStr) 
	{
		JedisHashManager jedisManager = new JedisHashManager(cacheKey + monthStr);
		List<String> valueList = jedisManager.hmgetValueList(fieldList);
		
		return valueList;
	}

	/**
	 * 获取指定月度所有用户在线时长map
	 * @param monthStr	统计月度，yyyyMM
	 * @return			所有用户在线时长map
	 */
	public Map<String, String> getAllMap(String monthStr) 
	{
		JedisHashManager jedisManager = new JedisHashManager(cacheKey + monthStr);
		Map<String, String> allMap = jedisManager.getAll();
		return allMap;
	}

	/**
	 * 为ssyh_mau_stats_hash.yyyyMM设置过期时长
	 * @param monthStr	统计月度，yyyyMM
	 * @param seconds	统计月度，yyyyMM，秒数
	 * @return			是否设置成功
	 */
	public boolean setExpire(String monthStr, int seconds) 
	{
		JedisHashManager jedisManager = new JedisHashManager(cacheKey + monthStr);
		long result = jedisManager.expire(seconds);
		return result > 0 ? true : false;
	}
	
}
