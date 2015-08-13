package cn.suishou.redis.executor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Tuple;
import cn.suishou.redis.RedisExecutor;
import cn.suishou.redis.RedisRunner;

public class JedisSortedSetManager{	
	
	private String key = new String();
	
	public JedisSortedSetManager(String key){
		this.key = key;
	}
	
	public long add(final double score, final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zadd(key, score, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public long rem(final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zrem(key, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public long size() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zcount(key, -Double.MAX_VALUE, Double.MAX_VALUE);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner);
	}
	
	public long size1() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zcount(key, Double.parseDouble("-1000000"), Double.MAX_VALUE);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public long delete(final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zrem(key, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 删除 从strat个 到end个 
	 * @param start
	 * @param end
	 * @return
	 */
	public long deleteByRank(final int start, final int end) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zremrangeByRank(key, start, end);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间 (包括等于 min 或 max ) 的成员。
	 * @param start	score >= start
	 * @param end	score <= end
	 * @return		被移除成员的数量
	 */
	public long deleteByScore(final long start, final long end) 
	{
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zremrangeByScore(key, start, end);
				return i;
			}} ;
		RedisExecutor<Long> executor = new RedisExecutor<Long>() ;		
		return executor.exe(runner) ;
	}
	
	public boolean isExist(final String member) {		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){
			public Boolean run(Jedis jedis) {
			    Double d = jedis.zscore(key, member);
			    if(d != null) return true;
				return false;
			}} ;
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>() ;		
		return re.exe(runner) ;
	}
	
	public Double getScore(final String member){
		RedisRunner<Double> runner = new RedisRunner<Double>(){
			public Double run(Jedis jedis) {
			    Double d = jedis.zscore(key, member);
			    if(d == null) return 0.0;
				return d;
			}} ;
		RedisExecutor<Double> re = new RedisExecutor<Double>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据sorce从大到小
	 */
	public Set<String> getRange(final int start, final int end) {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.zrevrange(key, start, end);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据sorce从小到大
	 */
	public Set<String> zrange(final int start, final int end) {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.zrange(key, start, end);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	public Long zcount(final double start, final double end) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.zcount(key, start, end);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据sorce从小到大
	 */
	public Set<String> getAll() {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.zrange(key, Integer.MIN_VALUE, Integer.MAX_VALUE);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	/**
	 * 根据sorce从大到小
	 */
	public Set<String> getAllDesc() {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.zrevrange(key, Integer.MIN_VALUE, Integer.MAX_VALUE);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
	
	public Map<String, Double> getRangeWithScores(final int start, final int end) {		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>(){
			public Map<String, Double> run(Jedis jedis) {
				Set<Tuple> set = jedis.zrevrangeWithScores(key, start, end);
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				for(Tuple t : set){
					map.put(t.getElement(), t.getScore());
				}
				return map;
			}} ;
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 从大到小
	 * @param start
	 * @param end
	 * @return
	 */
	public Map<String, Double> getRangeByScores(final double start, final double end){		
		RedisRunner<Map<String, Double>> runner = new RedisRunner<Map<String, Double>>(){
			public Map<String, Double> run(Jedis jedis) {
				Set<Tuple> set = jedis.zrevrangeByScoreWithScores(key, start, end);
				Map<String, Double> map = new LinkedHashMap<String, Double>();
				if(set != null && set.size() > 0)
				for(Tuple t : set){
					map.put(t.getElement(), t.getScore());
				}
				return map;
			}} ;
		RedisExecutor<Map<String, Double>> re = new RedisExecutor<Map<String, Double>>();		
		return re.exe(runner) ;
	}

	/**
	 * 返回有序集 key 中， score 值介于 max 和 min 之间 (默认包括等于 max 或 min ) 的所有的成员。有序集成员按 score 值递减 (从大到小) 的次序排列。
	 * @param start	score >= start
	 * @param end	score <= end
	 * @return		map以score为key，以member为value
	 */
	public Map<Double, String> getScoreAndRange(final double start, final double end)
	{
		RedisRunner<Map<Double, String>> runner = new RedisRunner<Map<Double, String>>() {
			public Map<Double, String> run(Jedis jedis) {
				Set<Tuple> set = jedis.zrevrangeByScoreWithScores(key, start, end);
				Map<Double, String> map = new LinkedHashMap<Double, String>();
				if(set != null && set.size() > 0)
				for(Tuple t : set){
					map.put(t.getScore(), t.getElement());
				}
				return map;
			}};
		RedisExecutor<Map<Double, String>> executor = new RedisExecutor<Map<Double, String>>();		
		return executor.exe(runner) ;
	}

	/**
	 * 使用管道，批量将scoreMembers加入到key指定的zset
	 * @param scoreMembers 包含double型score与String型value的map
	 * @return			        批量操作结果
	 */
	public long pipeAddScoreMembers(final Map<Double, String> scoreMembers)
	{
		RedisRunner<Long> runner = new RedisRunner<Long>() {
			public Long run(Jedis jedis) {
				Pipeline pipeline = jedis.pipelined();
				Response<Long> response = pipeline.zadd(key, scoreMembers);
				return response.get();
			}};
		RedisExecutor<Long> executor = new RedisExecutor<Long>();
		return executor.exe(runner);
	}

	public long deleteKey() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.del(key);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
}