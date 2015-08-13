package cn.suishou.redis.executor;

import java.util.Set;

import cn.suishou.redis.RedisExecutor;
import cn.suishou.redis.RedisRunner;
import redis.clients.jedis.Jedis;

public class JedisSetManager {

private String key = new String();
	
	public JedisSetManager(String key){
		this.key = key;
	}
	
	public long add(final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.sadd(key, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public long rem(final String member) {		
		RedisRunner<Long> runner = new RedisRunner<Long>(){
			public Long run(Jedis jedis) {
				long i = jedis.srem(key, member);
				return i;
			}} ;
		RedisExecutor<Long> re = new RedisExecutor<Long>() ;		
		return re.exe(runner) ;
	}
	
	public Set<String> getAll() {		
		RedisRunner<Set<String>> runner = new RedisRunner<Set<String>>(){
			public Set<String> run(Jedis jedis) {
				Set<String> i = jedis.smembers(key);
				return i;
			}} ;
		RedisExecutor<Set<String>> re = new RedisExecutor<Set<String>>() ;		
		return re.exe(runner) ;
	}
}
