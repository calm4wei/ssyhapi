package cn.suishou.bean;

public class ItemView {
	int itemChannel;
	String itemId;
	String uid;
	String imei;
	String fromChannel;
	String fromValue;
	
	public ItemView(int itemChannel, String itemId, String uid, String imei, String fromChannel, String fromValue){
		this.itemChannel = itemChannel;
		this.itemId = itemId;
		this.uid = uid;
		this.imei = imei;
		this.fromChannel = fromChannel;
		this.fromValue = fromValue;	
	}

	public int getItemChannel() {
		return itemChannel;
	}

	public void setItemChannel(int itemChannel) {
		this.itemChannel = itemChannel;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getFromChannel() {
		return fromChannel;
	}

	public void setFromChannel(String fromChannel) {
		this.fromChannel = fromChannel;
	}

	public String getFromValue() {
		return fromValue;
	}

	public void setFromValue(String fromValue) {
		this.fromValue = fromValue;
	}
	
	

}
