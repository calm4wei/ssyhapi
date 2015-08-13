package cn.suishou.bean;

import java.util.HashMap;

public class VIPItem {
	public String itemId;	
	public String title;
	public String recom;
	public String img;
	public double priceHeigh;
	public double priceLow;
	public String click_url;
	public String start_time;
	public String end_time;
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public double getPriceHeigh() {
		return priceHeigh;
	}

	public void setPriceHeigh(double priceHeigh) {
		this.priceHeigh = priceHeigh;
	}

	public double getPriceLow() {
		return priceLow;
	}
	
	public void setPriceLow(double priceLow) {
		this.priceLow = priceLow;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public HashMap<String, Object> toMap(){	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("title", recom);
		map.put("icon", img);
		map.put("orgPrice", priceHeigh);
		map.put("nowPrice", priceLow);	
		map.put("clickurl", click_url);
		return map;
	}
}
