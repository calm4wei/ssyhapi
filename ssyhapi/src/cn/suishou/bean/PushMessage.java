package cn.suishou.bean;

import java.util.HashMap;

public class PushMessage {   
	private String pId = "";
	private String title ="";
	private int code =0;
	private String content ="";
	private String starttime;
	private String endtime;
	private String platform = "";
	private int range = 1;  //发送给所有(0)或按条件发送(1)
	private String formula ="";
	private String icon = "";
	private Action action = new Action();

	public PushMessage(){}
	
	public PushMessage(String pId, String title, int code, String content, String starttime,String endtime, String platform, int range,
			String formula, String icon, Action action) {
		super();
		this.pId = pId;
		this.title = title;
		this.code = code;
		this.content = content;
		this.starttime = starttime;
		this.endtime = endtime;		
		this.platform = platform;	
		this.range = range;
		this.formula = formula;	
		this.icon = icon;
		this.action = action;
	}
	
	public PushMessage(String title, int code, String content, String starttime, String endtime, String icon, Action action) {
		super();		
		this.title = title;
		this.code = code;
		this.content = content;
		this.starttime = starttime;
		this.endtime = endtime;	
		this.icon = icon;
		this.action = action;
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}

	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public String getActionUrl(){		
		return action.getActionUrl();
	}	

	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	
	public HashMap<String, Object> toMap(){
		HashMap<String, Object> map = new HashMap<String, Object>();		
		
		map.put("pid", pId);
		map.put("code", code);
		map.put("title", title);
		map.put("icon", icon);
		map.put("content", content);
		map.put("starttime", starttime);
		map.put("ss_action", action.getActionUrl());
		return map;	
	}
}
