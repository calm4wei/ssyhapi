package cn.suishou.redis.executor;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import cn.suishou.redis.RedisExecutor;
import cn.suishou.redis.RedisRunner;

public class JedisHashManager {	
	
 private String key = new String();
	
	public JedisHashManager(String key){
		this.key = key;
	} 
	/**
	 * @return 添加成功返回true,更新返回false
	 */
	public long add(final String field, final String value) {
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.hset(key, field, value);	
				return i;
			}} ;
			
			RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
			return re.exe(runner) ;
	}
	
	public String get(final String field) {
		
		RedisRunner<String> runner = new RedisRunner<String>(){
			public String run(Jedis jedis) throws JedisConnectionException {
				if(null == field) return null;
				 String value = jedis.hget(key, field); 
				 return value;
			}} ;
			
		RedisExecutor<String> re = new RedisExecutor<String>();		
		return re.exe(runner) ;
	}
	
	public Map<String, String> getAll(){
		
		RedisRunner<Map<String, String>> runner = new RedisRunner<Map<String, String>>(){
			public Map<String, String> run(Jedis jedis) throws JedisConnectionException {
				Map<String, String> ret = jedis.hgetAll(key);
				return ret;
			}} ;
			
			RedisExecutor<Map<String, String>> re = new RedisExecutor<Map<String, String>>();		
			return re.exe(runner) ;
	}
	
	public ArrayList<String> getListByFields(final ArrayList<String> fields)
	{
		RedisRunner<ArrayList<String>> runner = new RedisRunner<ArrayList<String>>()
		{
			public ArrayList<String> run(Jedis jedis) throws JedisConnectionException 
			{
				ArrayList<String> values = new ArrayList<String>();
				for (String field : fields)
				{
					if (null != field && !"".equals(field))
					{
						String value = jedis.hget(key, field); 
						values.add(value);
					}
				}
				return values;
		}} ;
			
		RedisExecutor<ArrayList<String>> re = new RedisExecutor<ArrayList<String>>();		
		return re.exe(runner) ;
	}
	
	public String setAll(final Map<String, String> hash) {
			
			RedisRunner<String> runner = new RedisRunner<String>(){
				public String run(Jedis jedis) throws JedisConnectionException {
					 String value = jedis.hmset(key, hash);
					 return value;
				}} ;
				
			RedisExecutor<String> re = new RedisExecutor<String>();		
			return re.exe(runner) ;
		}

	public List<String> getAllField(){
		
		RedisRunner<List<String>> runner = new RedisRunner<List<String>>(){
			public List<String> run(Jedis jedis) throws JedisConnectionException {
				List<String> ret = new ArrayList<String>();
				ret.addAll(jedis.hkeys(key)); 
				return ret;
			}} ;
			
			RedisExecutor<List<String>> re = new RedisExecutor<List<String>>();		
			return re.exe(runner) ;
	}
	
	public boolean isExist(final String field) {
		
		RedisRunner<Boolean> runner = new RedisRunner<Boolean>(){
			public Boolean run(Jedis jedis) throws JedisConnectionException {
				return  jedis.hexists(key, field);			
			}} ;
		
		RedisExecutor<Boolean> re = new RedisExecutor<Boolean>();		
		return re.exe(runner) ;
	}
	
	/**
	 * @return 删除成功返回true
	 */
	public long delete(final String field){
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				long i = jedis.hdel(key, field);
				return i;
			}} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
	public long size(){
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				long i = jedis.hlen(key);
				return i;
			}} ;
		
		RedisExecutor<Long> re = new RedisExecutor<Long>();		
		return re.exe(runner) ;
	}
	
	/**
	 * 清空
	 */
	public long clean() {
		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				Long value = jedis.del(key);
				return value;
			}} ;
			
			RedisExecutor<Long> re = new RedisExecutor<Long>();		
			return re.exe(runner) ;
	}

	public long expire(final int second) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.expire(key, second);	
				return i;
			}} ;			
			RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
			return re.exe(runner) ;
	}

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment，如果 key 不存在，新的哈希表被创建并执行 HINCRBY 命令
	 * @param field		如果域 field 不存在，那么在执行命令前，域的值被初始化为 0
	 * @param increment	本操作的值被限制在 64 位(bit)有符号数字表示之内
	 * @return			执行 HINCRBY 命令之后，哈希表 key 中域 field 的值
	 */
	public long increaseBy(final String field, final long increment) 
	{
		RedisRunner<Long> redisRunner = new RedisRunner<Long>() {
			public Long run(Jedis jedis) throws JedisConnectionException {
				Long result = jedis.hincrBy(key, field, increment);
				return result;
			}
		};
		RedisExecutor<Long> redisExecutor = new RedisExecutor<Long>();
		return redisExecutor.exe(redisRunner);
	}

	/**
	 * 返回哈希表 key 中，fieldList对应的valueList
	 * @param fieldList	目标域集合
	 * @return			一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
	 */
	public List<String> hmgetValueList(final List<String> fieldList) 
	{
		RedisRunner<List<String>> redisRunner = new RedisRunner<List<String>>() {
			public List<String> run(Jedis jedis) throws JedisConnectionException {
				// 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
				List<String> result = jedis.hmget(key, (String[])fieldList.toArray());
				return result;
			}
		};
		RedisExecutor<List<String>> redisExecutor = new RedisExecutor<List<String>>();
		return redisExecutor.exe(redisRunner);
	}
	
}