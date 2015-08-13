package cn.suishou.bean;

import java.util.HashMap;

import cn.suishou.common.Value;

public class HomePageBanner {
	String tagId;
	int range;
	String key;
	String img;
	String actionUrl;
	
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getActionUrl(){
		if(Value.home_page_banner_key_chaojihui.equals(key)){
			return "suishou://app.suishou.cn/ChaoJiHui";
		}else if(Value.home_page_banner_key_baicaihui.equals(key)){
			return "suishou://app.suishou.cn/BaiCaiHui?tagId="+tagId;
		}else if(Value.home_page_banner_key_xianchanghui.equals(key)){
			return "suishou://app.suishou.cn/XianChangHui?tagId="+tagId;
		}else if(key.indexOf(Value.home_page_banner_key_catagory)>-1){ 
			return "suishou://app.suishou.cn/TagItems?tagId="+tagId;
		}else{
			return "suishou://app.suishou.cn/TagItems?tagId="+tagId;
		}
	}
	
	public HashMap<String, Object> toMap(){	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("key", key);
		map.put("img", img);
		map.put("actionUrl", getActionUrl());
		return map;
	}	



}
