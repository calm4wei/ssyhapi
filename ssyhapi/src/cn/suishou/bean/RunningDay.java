package cn.suishou.bean;

public class RunningDay {
	public int signInStatus; //1:今天已签到  2：今天没签到
	public int runningDays;
	public int adClickStatus; //1: 签到还未点击广告   2: 已点击广告
	public int adId;
	public int jfNum;
	
	public int getSignInStatus() {
		return signInStatus;
	}
	public void setSignInStatus(int signInStatus) {
		this.signInStatus = signInStatus;
	}
	public int getRunningDays() {
		return runningDays;
	}
	public void setRunningDays(int runningDays) {
		this.runningDays = runningDays;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public int getAdClickStatus() {
		return adClickStatus;
	}
	public void setAdClickStatus(int adClickStatus) {
		this.adClickStatus = adClickStatus;
	}
	public int getJfNum() {
		return jfNum;
	}
	public void setJfNum(int jfNum) {
		this.jfNum = jfNum;
	}


	
}
