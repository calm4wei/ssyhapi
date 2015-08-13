package cn.suishou.ramdata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import cn.suishou.bean.UserAddrBean;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisSortedSetManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 用户收货地址维护Cacher
 * @author  haol
 * @date	2014-12-29
 */
public class UserAddressCacher 
{
	/** 用户收货地址Cache Key */
	private static String cacheKey = X.CachePrefix.CACHE_USER_ADDRESS;
	/** 用户收货地址Cache单例 */
	private static UserAddressCacher instance;
	/** 获取用户收货地址Cache单例 */
	public static UserAddressCacher getUserAddressCacher() 
	{
		if (instance == null) {
			instance = new UserAddressCacher();
		}
		return instance;
	}
	
	/**
	 * 将单条收货地址信息加入用户地址zset
	 * @param userAddrBean	用户收货地址Bean
	 * @return	success or not
	 */
	public boolean addUserAddr(UserAddrBean userAddrBean) 
	{
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + userAddrBean.getUid());
//		String json = gson.toJson(userAddrBean.toHashMap());
		String json = new Gson().toJson(userAddrBean, UserAddrBean.class);
		long addNum = jedisManager.add(userAddrBean.getId(), json);
		return addNum > 0 ? true : false;
	}

	/**
	 * 根据addressId，从用户zset中delete收货地址信息
	 * @param uid		当前用户
	 * @param addressId	收货地址信息ID
	 */
	public boolean deleteUserAddr(String uid, long addressId) 
	{
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid);
		long deleteNum = jedisManager.deleteByScore(addressId, addressId);
		return deleteNum > 0 ? true : false;
	}

	/**
	 * 根据uid，删除用户收货地址信息zset
	 * @param uid		当前用户
	 */
	public boolean deleteAllUserAddr(String uid) 
	{
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid);
		long deleteNum = jedisManager.deleteKey();
		return deleteNum > 0 ? true : false;
	}

	/**
	 * 根据addressId，从用户zset中update收货地址信息
	 * @param uid		当前用户
	 * @param addressId	收货地址信息ID
	 */
	public boolean updateUserAddr(UserAddrBean userAddrBean) 
	{
		// 1.先删除欲更新的收货地址
		deleteUserAddr(userAddrBean.getUid(), userAddrBean.getId());
		// 2.再新增更新后的收货地址
		boolean updateResult = addUserAddr(userAddrBean);
		
		return updateResult;
	}
	
	/**
	 * 根据uid获取当前用户全部收货地址信息set
	 * @param uid	当前用户
	 * @return		当前用户全部收货地址信息set
	 */
	public Set<String> getAllUserAddr(String uid) 
	{
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid);
		Set<String> setJson = jedisManager.getAll();
		return setJson;
	}

	/**
	 * 根据uid获取当前用户全部收货地址bean set
	 * @param uid	当前用户
	 * @return		当前用户全部收货地址bean set
	 */
	public Set<UserAddrBean> getAllUserAddrBean(String uid) 
	{
		Set<UserAddrBean> beanSet = new HashSet<UserAddrBean>();
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid);
		Set<String> setJson = jedisManager.getAll();
		for (String jsonStr : setJson) {
			UserAddrBean bean = new Gson().fromJson(jsonStr, UserAddrBean.class);
			beanSet.add(bean);
		}
		return beanSet;
	}

	/**
	 * 根据uid获取当前用户全部收货地址JsonArray，以默认收货地址开头
	 * @param uid	当前用户
	 * @return		当前用户全部收货地址JsonArray，以默认收货地址开头
	 */
	public JsonArray getAllUserAddrJsonArray(String uid) 
	{
		JsonArray defaultFirstArray = new JsonArray();	// 以默认收货地址开头的JsonArray
		JsonArray notDefaultArray = new JsonArray();	// 不以默认收货地址开头的JsonArray
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid);
		Set<String> jsonStrSet = jedisManager.getAll();

		if (jsonStrSet != null && jsonStrSet.size() > 0) {
			JsonParser jsonParser = new JsonParser();
			for (String str : jsonStrSet) {
				JsonObject jsonObject = jsonParser.parse(str).getAsJsonObject();
				// 如果包含"isDefault"节点，且该节点是数字
				if (jsonObject.has("isDefault") && jsonObject.getAsJsonPrimitive("isDefault").isNumber()) {
					int isDefault = jsonObject.getAsJsonPrimitive("isDefault").getAsInt();
					if (UserAddrBean.IS_DEFAULT_YES == isDefault) {	// 如果为用户默认收货地址，则将其置顶
						defaultFirstArray.add(jsonObject);
					} else {										// 否则往后放
						notDefaultArray.add(jsonObject);
					}
				} else {
					notDefaultArray.add(jsonObject);
				}
			}
		}
		defaultFirstArray.addAll(notDefaultArray);	// 将其他非默认地址追加到后面
		return defaultFirstArray;
	}
	
	/**
	 * 根据UID、addressId获取当前用户收货地址信息
	 * @param uid		当前用户
	 * @param addressId	收货地址信息ID
	 */
	public String getUserAddr(String uid, long addressId) 
	{
		String addrJsonStr = "";
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid);
		Map<Double, String> scoreMap = jedisManager.getScoreAndRange(addressId, addressId);
		if (MapUtils.isNotEmpty(scoreMap)) {
			addrJsonStr = scoreMap.get(new Double(addressId).doubleValue());
		}
		return addrJsonStr;
	}

	/**
	 * 根据UID、addressId获取当前用户收货地址信息
	 * @param uid		当前用户
	 * @param addressId	收货地址信息ID
	 */
	public JsonObject getUserAddrJsonObject(String uid, long addressId) 
	{
		JsonObject jsonObject = new JsonObject();
		JedisSortedSetManager jedisManager = new JedisSortedSetManager(cacheKey + uid);
		Map<Double, String> scoreMap = jedisManager.getScoreAndRange(addressId, addressId);
		if (MapUtils.isNotEmpty(scoreMap)) {
			String addrJsonStr = scoreMap.get(new Double(addressId).doubleValue());
			if (StringUtils.isNotBlank(addrJsonStr)) {
				jsonObject = new JsonParser().parse(addrJsonStr).getAsJsonObject();
			}
		}
		return jsonObject;
	}
	
}
