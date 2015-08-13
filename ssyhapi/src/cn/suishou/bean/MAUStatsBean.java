package cn.suishou.bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.suishou.utils.DateUtil;

/**
 * 用户月度活跃时长统计Bean
 * @author  haol
 * @date	2015-01-05
 */
public class MAUStatsBean 
{
	/** 年月字串，yyyyMM格式，与uid组成联合主键 */
	private String monthStr;
	/** 本系统uid，与monthStr组成联合主键 */
	private String uid;
	
	/** 用户月度停留时长，豪秒 */
	private long stayMillis;
	/** 用户月度停留时长，秒 */
	private int staySeconds;
	/** 用户月度停留时长，分钟 */
	private int stayMinutes;
	/** 用户月度停留时长，小时 */
	private double stayHours;
	
	/** 无参构造方法 */
	public MAUStatsBean() {}

	/** 根据milliseconds换化其他时长的构造方法 */
	public MAUStatsBean(String monthStr,
						String uid, 
						long stayMillis) 
	{
		this.monthStr = monthStr;
		this.uid = uid;
		
		this.stayMillis = stayMillis;
		// 将milliseconds转为秒、分钟、小时数，四舍五入，小时保留两位小数
		this.staySeconds = Math.round(stayMillis / 1000.0f);
		this.stayMinutes = Math.round(stayMillis / (DateUtil.ONE_MINUTE_MILLI_SECONDS * 1.0f));
		
		BigDecimal hourDecimal = new BigDecimal(stayMillis / (DateUtil.ONE_HOUR_MILLI_SECONDS * 1.00d));     
		this.stayHours = hourDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/** 全参构造方法 */
	public MAUStatsBean(String monthStr,
						String uid, 
						String taoNick, 
						int stayMillis, 
						int staySeconds, 
						int stayMinutes, 
						int stayHours) 
	{
		this.monthStr = monthStr;
		this.uid = uid;
		
		this.stayMillis = stayMillis;
		this.staySeconds = staySeconds;
		this.stayMinutes = stayMinutes;
		this.stayHours = stayHours;
	}
	
	/**
	 * 返回HashMap保存的收货地址信息
	 * @return	HashMap保存的收货地址信息
	 */
	public Map<String, Object> toHashMap() 
	{
		Map<String, Object> addrMap = new HashMap<String, Object>();
		addrMap.put("monthStr", monthStr);
		addrMap.put("uid", uid);
		addrMap.put("stayMillis", stayMillis);
		addrMap.put("staySeconds", staySeconds);
		addrMap.put("stayMinutes", stayMinutes);
		addrMap.put("stayHours", stayHours);
		
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

	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	public long getStayMillis() {
		return stayMillis;
	}

	public void setStayMillis(long stayMillis) {
		this.stayMillis = stayMillis;
	}

	/**
	 * 设置用户月度停留时长（豪秒）的时候，换算成秒、分钟、小时，设置到对应字段
	 * @param stayMillis	用户月度停留时长（豪秒）
	 */
	public void setMillisCascadeOthers(long stayMillis) 
	{
		this.stayMillis = stayMillis;
		// 将milliseconds转为秒、分钟、小时数，四舍五入，小时保留两位小数
		this.staySeconds = Math.round(stayMillis / 1000.0f);
		this.stayMinutes = Math.round(stayMillis / (DateUtil.ONE_MINUTE_MILLI_SECONDS * 1.0f));
		
		BigDecimal hourDecimal = new BigDecimal(stayMillis / (DateUtil.ONE_HOUR_MILLI_SECONDS * 1.00d));     
		this.stayHours = hourDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public int getStaySeconds() {
		return staySeconds;
	}

	public void setStaySeconds(int staySeconds) {
		this.staySeconds = staySeconds;
	}

	public int getStayMinutes() {
		return stayMinutes;
	}

	public void setStayMinutes(int stayMinutes) {
		this.stayMinutes = stayMinutes;
	}

	public double getStayHours() {
		return stayHours;
	}

	public void setStayHours(double stayHours) {
		this.stayHours = stayHours;
	}
}
