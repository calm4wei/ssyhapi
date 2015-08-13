package cn.suishou.bean;

import cn.suishou.common.AppPageConf;
import cn.suishou.common.Value;


public class Action {

	String actionType = "";
	String actionValue = "";
	
	public Action(){		
	}
	
	public Action(String actionType, String actionValue){
		this.actionType = actionType;
		this.actionValue = actionValue;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionValue() {
		return actionValue;
	}
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	
	public String getActionUrl(){
		String appUrl = "suishou://app.suishou.cn/";
		String actionUrl = "";
		if(actionType.equals(Value.tagStyleWeb) ){
			actionUrl = actionValue;
		}else if(actionType.equals(Value.tagStyleAppPage) ){
			actionUrl = appUrl+AppPageConf.actionValueToUrlMap.get(actionValue);
		}		
		return actionUrl;
	}
	

	
	
}
