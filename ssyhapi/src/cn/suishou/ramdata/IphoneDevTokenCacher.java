package cn.suishou.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cn.suishou.common.X;
import cn.suishou.redis.JedisDBIns;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @category iphone端devtoken的管理
 */

public class IphoneDevTokenCacher {

	private static IphoneDevTokenCacher instance = null;
	private static String IphoneDevTokenCacherkey = X.CachePrefix.IphoneDevTokenCacherkey; 
	private static String AllIphoneDevTokenCacherkey = X.CachePrefix.AllIphoneDevTokenCacherkey; 
	
	private IphoneDevTokenCacher(){
		
	}
	
	public static IphoneDevTokenCacher getInstance(){
		if(instance == null) instance = new IphoneDevTokenCacher();
		return instance;
	}
	
	
	public void add(String uid, String devToken) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			jedis.hset(IphoneDevTokenCacherkey, uid, devToken);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void addDevToken(String devToken) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			jedis.sadd(AllIphoneDevTokenCacherkey, devToken);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public String getDevToken(String uid) {
		String ret = null;
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			ret = jedis.hget(IphoneDevTokenCacherkey, uid);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
		return ret;
	}	
	
	public Set<String> getAllDevToken(){
		Set<String> alldev = null;
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			alldev= jedis.smembers(AllIphoneDevTokenCacherkey);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
		return alldev;
	}
	
	public long getAllCount(){
		long ret = 0;
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			ret= jedis.scard(AllIphoneDevTokenCacherkey);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
		return ret;
	}
	
	public List<String> getAllHasUidDevToken(){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		List<String> retlist = new ArrayList<String>();
		try {
			retlist = jedis.hmget(IphoneDevTokenCacherkey);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().release(jedis);
		}
		return retlist;
	}

	
	public static void main(String[] a){
	}
}
