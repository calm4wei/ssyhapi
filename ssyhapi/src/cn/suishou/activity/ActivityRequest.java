package cn.suishou.activity;

public class ActivityRequest {	
	private String uid;
	private String activityKey; //活动key
	private String activityUniqueId; //活动唯一标识
	private int jfNum;
	private int runningDays = 0; //连续参加天数
	
	public ActivityRequest(String uid, String activityKey, String activityUniqueId, int jfNum, int runningDays) {
		super();
		this.uid = uid;
		this.activityKey = activityKey;
		this.activityUniqueId = activityUniqueId;
		this.jfNum = jfNum;
		this.runningDays = runningDays;
	}
	
	public ActivityRequest(String uid, String activityKey, String activityUniqueId, int jfNum) {
		super();
		this.uid = uid;
		this.activityKey = activityKey;
		this.activityUniqueId = activityUniqueId;
		this.jfNum = jfNum;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getActivityKey() {
		return activityKey;
	}

	public void setActivityKey(String activityKey) {
		this.activityKey = activityKey;
	}

	public String getActivityUniqueId() {
		return activityUniqueId;
	}

	public void setActivityUniqueId(String activityUniqueId) {
		this.activityUniqueId = activityUniqueId;
	}

	public int getJfNum() {
		return jfNum;
	}

	public void setJfNum(int jfNum) {
		this.jfNum = jfNum;
	}

	public int getRunningDays() {
		return runningDays;
	}

	public void setRunningDays(int runningDays) {
		this.runningDays = runningDays;
	}
}
