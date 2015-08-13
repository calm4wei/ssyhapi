package cn.suishou.bean;

import java.util.HashMap;

public class Order {
	String parentOrderId;
	String orderId;
	String title;
	String icon;
	String itemId;
	String sku;
	int num;
	double price;
	double totalPrice;
	int isFlashSell;

	String uid;
	String name;
	String phone_number;
	String address_province;
	String address_city;
	String address_detail;
	String postcode;
	
	int isUseCredit;
	int creditNum;
	String providerId;
	int status;
	int return_status;
	int is_client_shown;
	int payPlatform;
	String tradeNo;
	String createTime;
	String payTime;
	int notify_logistics_time;
	String logistics_no;
	String logistics_info;
	String delivery_time;

	
	public String getParentOrderId() {
		return parentOrderId;
	}
	public void setParentOrderId(String parentOrderId) {
		this.parentOrderId = parentOrderId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getIsFlashSell() {
		return isFlashSell;
	}
	public void setIsFlashSell(int isFlashSell) {
		this.isFlashSell = isFlashSell;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress_province() {
		return address_province;
	}
	public void setAddress_province(String address_province) {
		this.address_province = address_province;
	}
	public String getAddress_city() {
		return address_city;
	}
	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public int getIsUseCredit() {
		return isUseCredit;
	}
	public void setIsUseCredit(int isUseCredit) {
		this.isUseCredit = isUseCredit;
	}
	public int getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(int creditNum) {
		this.creditNum = creditNum;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public int getReturn_status() {
		return return_status;
	}
	public void setReturn_status(int return_status) {
		this.return_status = return_status;
	}
	public int getPayPlatform() {
		return payPlatform;
	}
	public void setPayPlatform(int payPlatform) {
		this.payPlatform = payPlatform;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public int getIs_client_shown() {  
		return is_client_shown;
	}
	public void setIs_client_shown(int is_client_shown) {
		this.is_client_shown = is_client_shown;
	}
	public int getNotify_logistics_time() {
		return notify_logistics_time;
	}
	public void setNotify_logistics_time(int notify_logistics_time) {
		this.notify_logistics_time = notify_logistics_time;
	}
	public String getLogistics_no() {
		return logistics_no;
	}
	public void setLogistics_no(String logistics_no) {
		this.logistics_no = logistics_no;
	}
	public String getLogistics_info() {
		return logistics_info;
	}
	public void setLogistics_info(String logistics_info) {
		this.logistics_info = logistics_info;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	public HashMap<String, Object> toMap(){	    //for订单列表
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("phone_number", phone_number);
		map.put("address_province", address_province);
		map.put("address_city", address_city);
		map.put("address_detail", address_detail);
		map.put("postcode", postcode);
		
		map.put("orderId", orderId);
		map.put("itemId", itemId);
		map.put("title", title);
		map.put("icon", icon);
		map.put("sku", sku);
		map.put("num", num);	
		map.put("price", price);
		map.put("totalPrice", totalPrice);		
		map.put("status", status);
		map.put("logistics_no", logistics_no);
		map.put("isUseCredit", isUseCredit);
		map.put("creditNum", creditNum);
		map.put("returnOrderEnable", 1);
		map.put("returnStatus", return_status);
		return map;
	}
	
}
