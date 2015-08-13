package cn.suishou.utils;

import com.google.gson.JsonObject;

import cn.suishou.common.Enums.ActionStatus;


public class RespStatusBuilder {
	private int code;
	private String desc;
	private String showDesc;
	
	public RespStatusBuilder(ActionStatus status) {
		this.code = status.getCode();
		this.desc = status.getDesc();
		this.showDesc = status.getShowDesc();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getShowDesc() {
		return showDesc;
	}

	public void setShowDesc(String showDesc) {
		this.showDesc = showDesc;
	}
	
	public  String toJson(){
		JsonObject jo=new JsonObject();
		jo.addProperty("code", getCode());
		jo.addProperty("desc", getDesc());
		jo.addProperty("showDesc", getShowDesc());
		return jo.toString();
	}
	
	/**
	 * 返回Server处理状态，JsonObject格式
	 * @return	Server处理状态，JsonObject格式
	 */
	public JsonObject toJsonObject()
	{
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("code", getCode());
		jsonObject.addProperty("desc", getDesc());
		jsonObject.addProperty("showDesc", getShowDesc());
		return jsonObject;
	}

	
}
