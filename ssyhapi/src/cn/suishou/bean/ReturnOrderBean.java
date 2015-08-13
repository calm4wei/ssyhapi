package cn.suishou.bean;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * 退款/退货记录Bean
 * @author  haol
 * @date	2014-12-31
 */
public class ReturnOrderBean 
{
	/** 退款/退货记录ID，自增 */
	private long id;
	/** uid */
	private String uid;
	/** 退货订单ID */
	private String orderId;
	/** 是否收到货，0：未收到货  1：已收到货 */
	private int isReceived;
	/** 退款/退货原因flag */
	private int reason;
	/** 退款金额 */
	private double refundAmount;
	/** 其他说明 */
	private String remark;
	/** 照片凭证1 */
	private String img1;
	/** 照片凭证2 */
	private String img2;
	/** 照片凭证3 */
	private String img3;
	/** 照片凭证4 */
	private String img4;
	
	public ReturnOrderBean() {}

	/** 缺少ID的构造函数 */
	public ReturnOrderBean(String uid, 
						String orderId, 
						int isReceived, 
						int reason, 
						double refundAmount, 
						String remark, 
						String img1,
						String img2,
						String img3,
						String img4) 
	{
		this.uid = uid;
		this.orderId = orderId;
		this.isReceived = isReceived;
		this.reason = reason;
		this.refundAmount = refundAmount;
		this.remark = remark;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
	}
	
	/** 全参构造函数 */
	public ReturnOrderBean(
						long id,
						String uid, 
						String orderId, 
						int isReceived, 
						int reason, 
						double refundAmount, 
						String remark, 
						String img1,
						String img2,
						String img3,
						String img4) 
	{
		this.id = id;
		this.uid = uid;
		this.orderId = orderId;
		this.isReceived = isReceived;
		this.reason = reason;
		this.refundAmount = refundAmount;
		this.remark = remark;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
	}

	/**
	 * 返回HashMap保存的收货地址信息
	 * @return	HashMap保存的收货地址信息
	 */
	public Map<String, Object> toHashMap() 
	{
		Map<String, Object> addrMap = new HashMap<String, Object>();
		addrMap.put("id", id);
		addrMap.put("uid", uid);
		addrMap.put("orderId", orderId);
		addrMap.put("isReceived", isReceived);
		addrMap.put("reason", reason);
		addrMap.put("refundAmount", refundAmount);
		addrMap.put("remark", remark);
		addrMap.put("img1", img1);
		addrMap.put("img2", img2);
		addrMap.put("img3", img3);
		addrMap.put("img4", img4);
		
		return addrMap;
	}

	/**
	 * 返回收货地址信息JSON String
	 * @return	收货地址信息JSON String
	 */
	public String toJSONString() 
	{
		String jsonString = "";
		JSONObject jsonObject = JSONObject.fromObject(this);
		if (jsonObject != null) {
			jsonString = jsonObject.toString();
		}
		return jsonString;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getIsReceived() {
		return isReceived;
	}
	public void setIsReceived(int isReceived) {
		this.isReceived = isReceived;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getImg4() {
		return img4;
	}
	public void setImg4(String img4) {
		this.img4 = img4;
	}
}
