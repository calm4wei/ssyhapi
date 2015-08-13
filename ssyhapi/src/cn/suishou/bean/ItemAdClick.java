package cn.suishou.bean;

public class ItemAdClick {

	private String id;
	private String uid;
	private String adId;
	private int jfNum;
	private String clickTime; //点击广告时间
	private String date; //点击广告日期
	private int status; //1点击   2加载完成
	private int fromArea; //0：签到    1：注册引导
	private String acUniqueId; //活动唯一标识
	private String acKey; //活动key

	
	public ItemAdClick(String uid, String adId, int jfNum, String clickTime, String date, int fromArea,	String acUniqueId, String acKey) {	
		this.uid = uid;	
		this.adId = adId;
		this.jfNum = jfNum;
		this.clickTime = clickTime;
		this.date = date;
		this.fromArea = fromArea;
		this.acUniqueId = acUniqueId;
		this.acKey = acKey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAcUniqueId() {
		return acUniqueId;
	}

	public void setAcUniqueId(String acUniqueId) {
		this.acUniqueId = acUniqueId;
	}

	public String getAcKey() {
		return acKey;
	}

	public void setAcKey(String acKey) {
		this.acKey = acKey;
	}

	public int getJfNum() {
		return jfNum;
	}

	public void setJfNum(int jfNum) {
		this.jfNum = jfNum;
	}

	public String getClickTime() {
		return clickTime;
	}

	public void setClickTime(String clickTime) {
		this.clickTime = clickTime;
	}

	public int getFromArea() {
		return fromArea;
	}

	public void setFromArea(int fromArea) {
		this.fromArea = fromArea;
	}
	
	
}
