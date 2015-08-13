package cn.suishou.bean;


public class SkipBean{

	private String urlkey = "";
	private String itemid = "";
	private String uid = "";
	private String keyid = "";
	private String channel = "";
	private String version = "";
	private String tag_id = "";
	private String platform = "";
	private String type = "";
	private String app = "";
	private long timestamp = 0;
	private String ip = "";
	private String from_uid = "";

	public SkipBean() {}
	public SkipBean(String urlkey,String itemid,String uid,String keyid,
			String channel,String version,String tag_id,String platform,
			String type,String app,long timestamp,String ip,String from_uid) 
	{
		this.app = app;
		this.channel = channel;
		this.from_uid = from_uid;
		this.ip = ip;
		this.itemid = itemid;
		this.keyid = keyid;
		this.platform = platform;
		this.tag_id = tag_id;
		this.timestamp = timestamp;
		this.type = type;
		this.uid = uid;
		this.urlkey = urlkey;
		this.version = version;
	}
	
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrlkey() {
		return urlkey;
	}
	public void setUrlkey(String urlkey) {
		this.urlkey = urlkey;
	}
	public String getKeyid() {
		return keyid;
	}
	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}
	public String getFrom_uid() {
		return from_uid;
	}
	public void setFrom_uid(String from_uid) {
		this.from_uid = from_uid;
	}

}
