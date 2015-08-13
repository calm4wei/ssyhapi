package cn.suishou.bean;

import java.util.HashMap;

import cn.suishou.manager.SignInManager;

public class SignIn {	
	private String uid;	
	private String date; // 签到日期
	private int runningDays = 0; // 连续签到时间
	private int jfNum = 0; // 获得积分个数
	private int status;  // 0:未签到  1 签到还未点击广告   2 已点击广告	
	private String createTime;	
	private ItemAd itemAd = null;

	public SignIn(){}
	
	public SignIn(String uid, String date, int runningDays, int jfNum) {
		super();
		this.uid = uid;
		this.date = date;
		this.runningDays = runningDays;
		this.jfNum = jfNum;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRunningDays() {
		return runningDays;
	}

	public void setRunningDays(int runningDays) {
		this.runningDays = runningDays;
	}


	public int getJfNum() {
		return jfNum;
	}

	public void setJfNum(int jfNum) {
		this.jfNum = jfNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}	

	
	public ItemAd getItemAd() {
		return itemAd;
	}

	public void setItemAd(ItemAd itemAd) {
		this.itemAd = itemAd;
	}

	public HashMap<String, Object> toMap(){	    
		String signDescription="";
		String tip = "点击浏览商品";
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(status == 4){
			signDescription = "每日签到九点开始，九点后再试";
			status = 0;
		}else if(status == 0){
			signDescription = "立即签到可领取集分宝jf个";     
		}else if(status==1){
			signDescription = "今日已签到获得集分宝"+jfNum+"个但未领取";
			tip = "点击浏览商品，即可领取";
		}else if(status==2){
			signDescription = "明日签到可领jfb个";
		}
		
		String tomorrowJf = SignInManager.getInstance().getNextJFNum(uid, runningDays+1);
		signDescription = signDescription.replaceAll("jf", tomorrowJf);
		
		map.put("status", status);
		map.put("days", runningDays);
		map.put("tip", tip);
		map.put("signDescription", signDescription);
		map.put("signAd", itemAd);
		return map;
	}
}
