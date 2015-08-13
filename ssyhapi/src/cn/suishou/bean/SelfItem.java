package cn.suishou.bean;

import java.util.HashMap;

import net.sf.json.JSONObject;

public class SelfItem {
	public String id;
	public String uid;
	public String item_type;
	public String name;
	public String description;
	public double org_price;
	public double now_price;
	public int stock=0;
	public String template; // color_size standard  others
	public JSONObject details;
	public String logo_url;
	public String[] img_url;
	public String shipping_origin;
	public int is_integral=0; //是否使用积分 0：是  1：否
	public int integral_num=0;
	public int soldNum=100;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {	
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getOrg_price() {
		return org_price;
	}
	public void setOrg_price(double org_price) {
		this.org_price = org_price;
	}
	public double getNow_price() {
		return now_price;
	}
	public void setNow_price(double now_price) {
		this.now_price = now_price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public JSONObject getDetails() {
		return details;
	}
	public void setDetails(JSONObject details) {
		this.details = details;
	}
	public String[] getImg_url() {
		return img_url;
	}
	public void setImg_url(String[] img_url) {
		this.img_url = img_url;
	}
	public String getLogo_url() {
		return logo_url;
	}
	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIs_integral() {
		return is_integral;
	}
	public void setIs_integral(int is_integral) {
		this.is_integral = is_integral;
	}
	public int getIntegral_num() {
		return integral_num;
	}
	public void setIntegral_num(int integral_num) {
		this.integral_num = integral_num;
	}
	public String getShipping_origin() {
		return shipping_origin;
	}
	public void setShipping_origin(String shipping_origin) {
		this.shipping_origin = shipping_origin;
	}
	
	public String getItem_type() {
		return item_type;
	}
	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}
	public int getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(int soldNum) {
		this.soldNum = soldNum;
	}
	public HashMap<String, Object> toMap(){	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", id);
		map.put("title", name);
		map.put("icon", logo_url);
		map.put("orgPrice", org_price);
		map.put("nowPrice", now_price);	
		map.put("isBaoYou", 1);
		map.put("num", stock);
		return map;
	}	
	
	public HashMap<String, Object> toDetailMap(){	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", id);
		map.put("providerId", uid);
		map.put("title", name);
		map.put("description", description);
		map.put("icon", logo_url);
		map.put("orgPrice", org_price);
		map.put("nowPrice", now_price);	
		map.put("isBaoYou", 1);
		map.put("num", stock);
		map.put("template", template);
		map.put("skus", details==null?"":details.toString());
		map.put("img_url", img_url);		
		map.put("useCredit", is_integral);
		map.put("creditNum", integral_num);
		map.put("soldNum", soldNum);
		String[] address = shipping_origin.split(",");
		if(address.length==2 && address[0].equals(address[1])){ //对类似“上海市，上海市”进行处理
			shipping_origin = address[0];
		}
		map.put("shipping_origin", shipping_origin);
		
		return map;
	}
	

	
	
}
