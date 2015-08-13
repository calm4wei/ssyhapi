package cn.suishou.bean;

import java.sql.Timestamp;
import java.util.HashMap;

import cn.suishou.utils.StringUtil;

public class User {
	private String uid ;
	private String taobaoNick ="";
	private String phoneNum = "";
	private String password;
	private String taobaoUid;
	private String imei;
	private String imsi;
	private String channel;
	private String platform;
	private Timestamp creatTime;
	private Timestamp lastTime;
	private int isFromOldVersion = 0;
	private String icon;
		
	public User(){
	}
	
	public User(String uid, String taobaoNick, String taobaoUid,  String phoneNum,
			String password, String imei, String imsi, String channel, String platform) {
		this.uid = uid;
		this.taobaoNick = taobaoNick;
		this.phoneNum = phoneNum;
		this.password = password;
		this.taobaoUid = taobaoUid;
		this.imei = imei;
		this.imsi = imsi;
		this.channel = channel;
		this.platform = platform;
	}

	public String getTaobaoUid() {
		return taobaoUid;
	}
	public void setTaobaoUid(String taobaoUid) {
		this.taobaoUid = taobaoUid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTaobaoNick() {
		return taobaoNick;
	}
	public void setTaobaoNick(String taobaoNick) {
		this.taobaoNick = taobaoNick;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public int getIsFromOldVersion() {
		return isFromOldVersion;
	}

	public void setIsFromOldVersion(int isFromOldVersion) {
		this.isFromOldVersion = isFromOldVersion;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getIcon() {
		if(!StringUtil.isEmpty(icon)){
			return icon;
		}else{
			if(!StringUtil.isEmpty(taobaoUid)){
				return "http://wwc.taobaocdn.com//avatar/getAvatar.do?userId=" + taobaoUid + "&width=160&height=160&type=sns";				
			}else{
				return "http://static.etouch.cn/suishou/ad_img/taobao_icon.png";
			}
		}
	}

	public HashMap<String, String> toMap(){	    
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uid", uid);
		map.put("phoneNum", phoneNum == null ? "" : phoneNum);
		map.put("icon", getIcon());
		return map;
	}
	


}
