package cn.suishou.bean;

import java.util.HashMap;

public class CartItem {
	public String id;
	public String uid;
	public String itemId;
	public String title;
	public String icon;
	public double price;	
	public int num;
	public String sku;	
	public String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public HashMap<String, Object> toMap(){	    
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", id);
		map.put("title", title);
		map.put("icon", icon);
		map.put("price", price);
		map.put("num", num);	
		map.put("sku", sku);
	
		return map;
	}

	
}
