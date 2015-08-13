package cn.suishou.redis.executor;

import java.util.Set;

import cn.suishou.redis.RedisExecutor;
import cn.suishou.redis.RedisRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class JedisKeyManager {
	private String key = null;
	
	public JedisKeyManager(String key){
		this.key = key;
	}
	
	public String set(final String value) {		
		RedisRunner<String> runner = new RedisRunner<String>(){
			public String run(Jedis jedis) throws JedisConnectionException {
				return jedis.set(key, value);
			}} ;
			RedisExecutor<String> re = new RedisExecutor<String>();		
			return re.exe(runner) ;
	}
	
	public String get() {		
		RedisRunner<String> runner = new RedisRunner<String>(){
			public String run(Jedis jedis) throws JedisConnectionException {
				return jedis.get(key);
			}} ;
			RedisExecutor<String> re = new RedisExecutor<String>();		
			return re.exe(runner) ;
	}
	
	public long expire(final int seconds) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				return jedis.expire(key, seconds);
			}} ;
			RedisExecutor<Long> re = new RedisExecutor<Long>();		
			return re.exe(runner) ;
	}
	
	public long del() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				
				if(key == null || "".equals(key))
					return 0l;
				Set<String> set = jedis.keys(key + "*");
				if(set != null && set.size() > 0){
				for(String s : set){
					jedis.del(s);
				}
				return (long)set.size();
				}else return 0l;
			}} ;
			RedisExecutor<Long> re = new RedisExecutor<Long>();		
			return re.exe(runner) ;
	}
	
	public long delonly() {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) throws JedisConnectionException {
				
				if(key == null || "".equals(key))
					return 0l;
				Set<String> set = jedis.keys(key);
				if(set != null && set.size() > 0){
				for(String s : set){
					jedis.del(s);
				}
				return (long)set.size();
				}else return 0l;
			}} ;
			RedisExecutor<Long> re = new RedisExecutor<Long>();		
			return re.exe(runner) ;
	}
	
}
