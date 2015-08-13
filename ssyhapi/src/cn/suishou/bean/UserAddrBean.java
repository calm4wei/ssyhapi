package cn.suishou.bean;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

/**
 * 用户收货地址Bean
 * @author  haol
 * @date	2014-12-29
 */
public class UserAddrBean 
{
	/** 是否为用户默认收货地址，1：是 */
	public static int IS_DEFAULT_YES = 1;
	/** 是否为用户默认收货地址，0：否 */
	public static int IS_DEFAULT_NO  = 0;
	
	/** 用户收货地址信息ID，自增 */
	private long id;
	/** uid */
	private String uid;
	/** 收货人姓名 */
	private String name;
	/** 收货人电话 */
	private String phoneNumber;
	/** 省份 */
	private String addressProvince;
	/** 城市 */
	private String addressCity;
	/** 具体地址 */
	private String addressDetail;
	/** 邮编 */
	private String postcode;
	/** 是否为用户默认收货地址，0：不是，1：是 */
	private int isDefault;
	
	public UserAddrBean() {}
	public UserAddrBean(String uid, 
						String name, 
						String phoneNumber, 
						String addressProvince, 
						String addressCity, 
						String addressDetail, 
						String postcode,
						int isDefault) 
	{
		this.uid = uid;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressDetail = addressDetail;
		this.postcode = postcode;
		this.isDefault = isDefault;
	}
	
	/** 全参构造函数 */
	public UserAddrBean(
						long id,
						String uid, 
						String name, 
						String phoneNumber, 
						String addressProvince, 
						String addressCity, 
						String addressDetail, 
						String postcode,
						int isDefault)
	{
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressDetail = addressDetail;
		this.postcode = postcode;
		this.isDefault = isDefault;
	}

	/**
	 * 返回HashMap保存的收货地址信息
	 * @return	HashMap保存的收货地址信息
	 */
	public Map<String, Object> toHashMap() 
	{
		Map<String, Object> addrMap = new HashMap<String, Object>();
		addrMap.put("id", id);
		addrMap.put("uid", uid);
		addrMap.put("name", name);
		addrMap.put("phoneNumber", phoneNumber);
		addrMap.put("addressProvince", addressProvince);
		addrMap.put("addressCity", addressCity);
		addrMap.put("addressDetail", addressDetail);
		addrMap.put("postcode", postcode);
		addrMap.put("isDefault", isDefault);
		
		return addrMap;
	}

	/**
	 * 返回收货地址信息JSON String
	 * @return	收货地址信息JSON String
	 */
	public String toJSONString() 
	{
		String jsonString = "";
		JSONObject jsonObject = JSONObject.fromObject(this);
		if (jsonObject != null) {
			jsonString = jsonObject.toString();
		}
		return jsonString;
	}

	/**
	 * 返回JSON保存的收货地址信息
	 * @return	JSON保存的收货地址信息
	 */
	public JsonObject toJsonObject() 
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", id);
		jsonObject.addProperty("uid", uid);
		jsonObject.addProperty("name", name);
		jsonObject.addProperty("phoneNumber", phoneNumber);
		jsonObject.addProperty("addressProvince", addressProvince);
		jsonObject.addProperty("addressCity", addressCity);
		jsonObject.addProperty("addressDetail", addressDetail);
		jsonObject.addProperty("postcode", postcode);
		jsonObject.addProperty("isDefault", isDefault);
		
		return jsonObject;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddressProvince() {
		return addressProvince;
	}
	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
}
