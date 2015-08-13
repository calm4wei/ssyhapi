package cn.suishou.bean.superDiscount;

import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cn.suishou.common.ParamConfig;
import cn.suishou.ramdata.FavoriteCacher;

public class SuperDiscountBean {
	
	private int id;
	private String itemId;
	private String title;
	private String img;
	private double priceH;
	private double priceL;
	private int num;
	private int status;
	private double taoJb;
	private int isOnly4Mobile;
	private int isPxj;
	private String date;
	private int menuType;
	private int  fromChannel;
	private String clickUrl;
	private String couponUrl;
	
	public String getClickUrl() {
		return clickUrl;
	}
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	public int getFromChannel() {
		return fromChannel;
	}
	public void setFromChannel(int fromChannel) {
		this.fromChannel = fromChannel;
	}
	public double getPriceH() {
		return priceH;
	}
	public void setPriceH(double priceH) {
		this.priceH = priceH;
	}
	public double getPriceL() {
		return priceL;
	}
	public void setPriceL(double priceL) {
		this.priceL = priceL;
	}
	public int getMenuType() {
		return menuType;
	}
	public void setMenuType(int menuType) {
		this.menuType = menuType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsPxj() {
		return isPxj;
	}
	public void setIsPxj(int isPxj) {
		this.isPxj = isPxj;
	}
	public double getTaoJb() {
		return taoJb;
	}
	public void setTaoJb(double taoJb) {
		this.taoJb = taoJb;
	}
	
	public JsonObject toJson(String uid){
		JsonObject jo=new JsonObject();
		jo.addProperty("itemId", itemId);
		jo.addProperty("title", title);
		jo.addProperty("icon", img);
		jo.addProperty("orgPrice", priceH);
		jo.addProperty("nowPrice", priceL);
		jo.addProperty("clickurl", clickUrl);
		jo.addProperty("num", num);
//		map.put("taoJb", taoJb);
//		map.put("isOnly4Mobile", isOnly4Mobile);
//		map.put("isPxj", isPxj);
		jo.addProperty("clickurl", clickUrl);
//		map.put("itemChannel", fromChannel);
		jo.addProperty("couponUrl", couponUrl);
		JsonArray ja=new JsonArray();
		if(isOnly4Mobile==1){
			JsonObject jo2=new JsonObject();
			jo2.addProperty("tagName", ParamConfig.isOnly4Mobile_name);
			jo2.addProperty("tagColor", ParamConfig.isOnly4Mobile_color);
			ja.add(jo2);
		}
		if(fromChannel==1){
			JsonObject jo2=new JsonObject();
			jo2.addProperty("tagName", ParamConfig.isOnly4Suishou_name);
			jo2.addProperty("tagColor", ParamConfig.isOnly4Suishou_color);
			ja.add(jo2);
		}
		if(isPxj==1&&ja.size()<2){
			JsonObject jo2=new JsonObject();
			jo2.addProperty("tagName", ParamConfig.isPxj_name);
			jo2.addProperty("tagColor", ParamConfig.isPxj_color);
			ja.add(jo2);
		}
		if(taoJb>0&&ja.size()<2){
			JsonObject jo2=new JsonObject();
			jo2.addProperty("tagName", ParamConfig.isTaoJb_name);
			jo2.addProperty("tagColor", ParamConfig.isTaoJb_color);
			ja.add(jo2);
		}
		jo.add("tags", ja);
		Set<String> set=FavoriteCacher.getInstance().getFavorite(uid);
		int isFavorite=0;
		if(set!=null&&set.contains(itemId+","+fromChannel)){
			isFavorite=1;
		}
		jo.addProperty("isFavorite", isFavorite);
		return jo;
	}
}
