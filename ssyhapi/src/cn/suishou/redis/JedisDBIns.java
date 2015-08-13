package cn.suishou.redis;

import org.apache.log4j.Logger;

import cn.suishou.common.Config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDBIns {

	private static JedisDBIns ins = new JedisDBIns();
	private static Logger logger = Logger.getLogger(JedisDBIns.class) ;

	protected static JedisPool pool = null;
	protected static JedisPoolConfig config = null;

	public static JedisDBIns getInstance() {
		return ins == null ? (ins = new JedisDBIns()) : ins;
	}

	public Jedis getJedis() {

		try {
			if (pool == null) {
				init();
			}
			Jedis j = pool.getResource();
			return j;
		} catch (Exception e) {
			logger.error("error stack",e);
			return null;
		}
	}

	/**
	 * 本方法会判断传入的Jedis对象是否为空，或者是否连接。外界仅需调用
	 * 
	 * @param jedis
	 *            需要释放的Jedis对象
	 * */
	public void release(Jedis jedis) {
		if (pool != null && jedis != null) {
			pool.returnResource(jedis);
		}
		return;
	}

	public void releaseBrokenJedis(Jedis jedis) {
		if (pool!= null && jedis != null) {
			pool.returnBrokenResource(jedis);
		}
	}

	public void destory(){
		if(pool != null){
			pool.destroy();
		}
		pool = null ;
		return;
	}

	private void init() {
		System.out.println("redisconnection start");
		
		config = new JedisPoolConfig();
		config.setMaxActive(512);
		config.setMaxIdle(512);
		config.setMaxWait(1000);
		config.setTestOnBorrow(true);
		config.setTestWhileIdle(true);
		config.setTestOnReturn(true);
		config.minEvictableIdleTimeMillis = 60000;
		config.timeBetweenEvictionRunsMillis = 30000;
		config.numTestsPerEvictionRun = -1;

		pool = new JedisPool(config, Config.GLB_REDIS_HOST, Config.GLB_REDIS_PORT, 5000);
		System.out.println("redisconnection end");
	}

//	public static void main(String[] args) {
//		Jedis j = JedisDBIns.getInstance().getJedis();
//		j.set("692656526563", "test");
//	}
}
