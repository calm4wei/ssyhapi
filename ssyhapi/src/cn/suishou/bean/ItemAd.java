package cn.suishou.bean;

public class ItemAd {

	private String adId;
	private int itemChannel;
	private String clickUrl;
	private String img;
	private int type; //0：签到  1：注册引导
//	private boolean isNeedCallBack = false;	
	

	public int getItemChannel() {
		return itemChannel;
	}

	public void setItemChannel(int itemChannel) {
		this.itemChannel = itemChannel;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

//	public boolean isNeedCallBack() {
//		return isNeedCallBack;
//	}
//
//	public void setNeedCallBack(boolean isNeedCallBack) {
//		this.isNeedCallBack = isNeedCallBack;
//	}
	
	public void setClickUrlNeedCallback(){
		String nameAndPara = clickUrl.substring(clickUrl.lastIndexOf("/"), clickUrl.length());
		if(nameAndPara.indexOf("?")==-1){
			clickUrl += "?needCallback=1&adId="+adId;
		}else{
			clickUrl += "&needCallback=1&adId="+adId;
		}
	}

	
}
