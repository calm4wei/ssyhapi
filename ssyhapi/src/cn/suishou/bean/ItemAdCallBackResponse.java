package cn.suishou.bean;

import java.util.HashMap;

public class ItemAdCallBackResponse {	
	boolean isSignAd = false;
	boolean success = false;
	String description;	
	int jfNum = 0;
	
	public boolean isSignAd() {
		return isSignAd;
	}

	public void setSignAd(boolean isSignAd) {
		this.isSignAd = isSignAd;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public int getJfNum() {
		return jfNum;
	}

	public void setJfNum(int jfNum) {
		this.jfNum = jfNum;
	}	
	
	public String getDescription() {
		if(jfNum>0){
			description = "成功领取" + jfNum + "个集分宝";
		}else{
			description = "你来的太迟拉，集分宝已经被领光咯";
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public HashMap<String, Object> toMap(){	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", success);
		map.put("signDescription", getDescription());
		return map;
	}
	
}
