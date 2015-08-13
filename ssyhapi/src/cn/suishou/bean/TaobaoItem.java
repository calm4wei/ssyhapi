package cn.suishou.bean;

import java.util.HashMap;


public class TaobaoItem {
	public int id;
	public String itemId;
	public String title;
	public String recom;
	public String img;	
	public double priceLow;
	public double priceHeigh;
	public int status;
	public String adminName;
	public int market;
	public int isBaoYou;
	public int isPxj;
	public String insertTimestamp;	
	public String clickurl;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public String getRecom() {
		return recom;
	}
	public void setRecom(String recom) {
		this.recom = recom;
	}
	public double getPriceLow() {
		return priceLow;
	}
	public void setPriceLow(double priceLow) {
		this.priceLow = priceLow;
	}
	public double getPriceHeigh() {
		return priceHeigh;
	}
	public void setPriceHeigh(double priceHeigh) {
		this.priceHeigh = priceHeigh;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getMarket() {
		return market;
	}
	public void setMarket(int market) {
		this.market = market;
	}
	public int getIsBaoYou() {
		return isBaoYou;
	}
	public void setIsBaoYou(int isBaoYou) {
		this.isBaoYou = isBaoYou;
	}
	public int getIsPxj() {
		return isPxj;
	}
	public void setIsPxj(int isPxj) {
		this.isPxj = isPxj;
	}
	public String getInsertTimestamp() {
		return insertTimestamp;
	}
	public void setInsertTimestamp(String insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}	

	public String getClickurl() {
		return clickurl;
	}
	public void setClickurl(String clickurl) {
		this.clickurl = clickurl;
	}
	public HashMap<String, Object> toMap(){	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("title", recom);
		map.put("icon", img);
		map.put("orgPrice", priceLow);
		map.put("nowPrice", priceHeigh);
		map.put("market", market);
		map.put("isBaoYou", isBaoYou);
		map.put("isPxj", isPxj);	
		map.put("clickurl", clickurl);
		
		return map;
	}
	
	
}
