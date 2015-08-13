package cn.suishou.bean;

import java.sql.Timestamp;

import cn.suishou.utils.DateUtil;

import com.google.gson.JsonObject;

/**
 * 意见反馈/客服回复bean
 * @author haol
 */
public class Feedback 
{
	/** 意见反馈或回复ID */
	private long id;
	/** 反馈或回复用户uid */
	private String uid;
	/** 反馈用户或回复客服昵称 */
	private String nick;
	/** 反馈或回复用户头像 */
	private String avatar;
	/** 反馈或回复发表时间 */
	private String timestamp;
	/** 是否被回复(只在用户反馈时有值) */
	private int isReplied;
	/** 回复所针对用户uid(只在回复时有值) */
	private String replyToUid;
	/** 反馈类型，1：订单问题；2：功能改进 */
	private int type;
	/** 反馈或回复内容 */
	private String content;
	/** 从用户反馈与回复zset的最后往前检索时，cursorId作为上一次检索到的id */
	private long cursorId;
	
	public Feedback() {}

	/** 不使用ID的构造函数 */
	public Feedback(String uid, String nick, String avatar, String timestamp, int isReplied, String replyToUid, int type, String content) {
		this.uid = uid;
		this.nick = nick;
		this.avatar = avatar;
		this.timestamp = timestamp;
		this.isReplied = isReplied;
		this.replyToUid = replyToUid;
		this.type = type;
		this.content = content;
	}

	/** 使用app提交参数的构造函数 */
	public Feedback(String uid, String timestamp, int type, String content, long cursorId) {
		this.uid = uid;
		this.timestamp = timestamp;
		this.type = type;
		this.content = content;
		this.cursorId = cursorId;
	}
	
	/** 全参构造函数 */
	public Feedback(long id, String uid, String nick, String avatar, String timestamp, int isReplied, String replyToUid, int type, String content) {
		this.id = id;
		this.uid = uid;
		this.nick = nick;
		this.avatar = avatar;
		this.timestamp = timestamp;
		this.isReplied = isReplied;
		this.replyToUid = replyToUid;
		this.type = type;
		this.content = content;
	}
	
	/**
	 * 转为bean对应的JsonObject
	 * @return	bean对应的JsonObject
	 */
	public JsonObject toJsonObject() 
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", id);
		jsonObject.addProperty("uid", uid);
		jsonObject.addProperty("nick", nick);
		jsonObject.addProperty("avatar", avatar);
		jsonObject.addProperty("timestamp", timestamp);
		jsonObject.addProperty("isReplied", isReplied);
		jsonObject.addProperty("replyToUid", replyToUid);
		jsonObject.addProperty("type", type);
		jsonObject.addProperty("content", content);
		
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
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	/** 反馈或回复发表时间timestamp，格式："yyyy-MM-dd HH:mm:ss" */
	public String getTimestamp() {
		return timestamp;
	}
	/** 获取 精确到分钟的反馈或回复时间，格式："yyyy-MM-dd HH:mm" */
	public String getTimestampMinute() {
		String timestampMinute = DateUtil.dateStr19To16(timestamp);
		return timestampMinute;
	}
	/** 反馈或回复发表时间timestamp，long类型 */
	public long getTimestampLong() {
		long stamp = DateUtil.strToStamp(timestamp);
		return stamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = DateUtil.sqlTimestampToStr(timestamp);
	}

	public int getIsReplied() {
		return isReplied;
	}
	public void setIsReplied(int isReplied) {
		this.isReplied = isReplied;
	}
	public String getReplyToUid() {
		return replyToUid;
	}
	public void setReplyToUid(String replyToUid) {
		this.replyToUid = replyToUid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCursorId() {
		return cursorId;
	}
	public void setCursorId(long cursorId) {
		this.cursorId = cursorId;
	}
}
