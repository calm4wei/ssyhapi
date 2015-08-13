package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.suishou.bean.Order;
import cn.suishou.common.Value;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.StringUtil;

public class OrderDAO {
	protected static Logger logger = Logger.getLogger(OrderDAO.class);
	private static OrderDAO instance = null;

	private OrderDAO(){
	}
	
	public static OrderDAO getInstance(){
		if(instance == null) instance = new OrderDAO();
		return instance;
	}
	
	public boolean insertOrderList(List<Order> orderList){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			long now = System.currentTimeMillis();
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into `ssyh_main`.`order`(`title`,`parentOrderId`,`orderId`,`itemId`,`icon`,`providerId`,`sku`,`price`,`num`,`totalPrice`,`isFlashSell`,`uid`,`name`,`phone_number`,`address_province`,`address_city`,`address_detail`,`postcode`,`is_use_credit`,`credit_num`,`create_time`,`status`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			for(Order order : orderList){
				ps.setString(1, order.getTitle());
				ps.setString(2, order.getParentOrderId());
				ps.setString(3, order.getOrderId());
				ps.setString(4, order.getItemId());
				ps.setString(5, order.getIcon());
				ps.setString(6, order.getProviderId());				
				ps.setString(7, order.getSku());
				
				ps.setDouble(8, order.getPrice());
				ps.setInt(9, order.getNum());		
				ps.setDouble(10, order.getTotalPrice());
				ps.setInt(11, order.getIsFlashSell());
				ps.setString(12, order.getUid());
				ps.setString(13, order.getName());
				ps.setString(14, order.getPhone_number());			
				ps.setString(15, order.getAddress_province());
				ps.setString(16, order.getAddress_city());		
				ps.setString(17, order.getAddress_detail());
				ps.setString(18, order.getPostcode());
				ps.setInt(19, order.getIsUseCredit());
				ps.setInt(20, order.getCreditNum());
				ps.setTimestamp(21, StringUtil.long2timestamp(now));
				ps.setInt(22, Value.order_status_unpaid);
				
				ps.addBatch();
			}

			conn.setAutoCommit(true);
			int i[] = ps.executeBatch();
			if(i[0]>0){
				flag = true;
			}

		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean updateOrderPayStatus(String[] orderIds, String trade_no){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
		
			String sql = "update `ssyh_main`.`order` set `status`=?, `pay_platform`=?, `trade_no`=?, `pay_time`=? where 1=2 ";
			ps = conn.prepareStatement(sql);
			for(String orderId : orderIds){
				sql += " or orderId='"+orderId+"'";
			}

			ps.setInt(1, Value.order_status_paid);
			ps.setInt(2, Value.order_pay_platform_ali);
			ps.setString(3, trade_no);
			ps.setTimestamp(4, StringUtil.long2timestamp(System.currentTimeMillis()));
			int i = ps.executeUpdate();
			if(i>0){
				flag = true;
			}

		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean updateOrderPayStatus(String orderId, String trade_no, int payPlatform){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
		
			String sql = "update `ssyh_main`.`order` set `status`=?, `pay_platform`=?, `trade_no`=?, `pay_time`=? where `orderId`=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Value.order_status_paid);
			ps.setInt(2, payPlatform);
			ps.setString(3, trade_no);
			ps.setTimestamp(4, StringUtil.long2timestamp(System.currentTimeMillis()));
			ps.setString(5, orderId);
			int i = ps.executeUpdate();
			if(i>0){
				flag = true;
			}

		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	
	public void updateJfPayStatus(String[] orderIds){
		for(String orderId : orderIds){
			updateJfPayStatus(orderId);
		}
	}
	
	public boolean updateJfPayStatus(String orderId){
		try{			
			Order order = OrderDAO.getInstance().getOrder(orderId, null);
			if(order.getIsUseCredit()==1){
				int creaditNum = order.getCreditNum();
				String uid = order.getUid();
				String title = order.getTitle();
				int num = order.getNum();
				boolean flag = TradeMXDAO.getInstance().insertJFPay(uid, creaditNum, title, num);
				if(flag){
					OrderDAO.getInstance().updateOrderJfPayStatus(orderId, Value.order_credit_pay_status_success);
				}else{
					OrderDAO.getInstance().updateOrderJfPayStatus(orderId, Value.order_credit_pay_status_fail);
				}
			}
			return true;
		}catch(Exception e){
			logger.error("error stack", e);
			return false;
		}
	}
	
	public boolean updateOrderJfPayStatus(String orderId, int status){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
		
			String sql = "update `ssyh_main`.`order` set `credit_pay_status`=? where `orderId`=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, orderId);			
			int i = ps.executeUpdate();
			if(i>0){
				flag = true;
			}
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public Order getOrder(String orderId, String uid){	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Order order = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`order` where `orderId`=? " + (uid==null?"":" and `uid`='"+uid+"'");
			
			ps = conn.prepareStatement(sql);			
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			if(rs.next()){
				order = new Order();			
				order.setUid(rs.getString("uid"));
				order.setName(rs.getString("name"));
				order.setPhone_number(rs.getString("phone_number"));
				order.setAddress_province(rs.getString("address_province"));
				order.setAddress_city(rs.getString("address_city"));		
				order.setAddress_detail(rs.getString("address_detail"));
				order.setPostcode(rs.getString("postcode"));
				
				order.setOrderId(rs.getString("orderId"));
				order.setItemId(rs.getString("itemId"));
				order.setProviderId(rs.getString("providerId"));
				order.setIcon(rs.getString("icon"));
				order.setTitle(rs.getString("title"));			
				order.setSku(rs.getString("sku"));
				order.setNum(rs.getInt("num"));
				order.setPrice(rs.getDouble("price"));
				order.setTotalPrice(rs.getDouble("totalPrice"));
				order.setIsFlashSell(rs.getInt("isFlashSell"));
				
				order.setIsUseCredit(rs.getInt("is_use_credit"));
				order.setCreditNum(rs.getInt("credit_num"));
				order.setProviderId(rs.getString("providerId"));
				
				order.setStatus(rs.getInt("status"));
				order.setPayPlatform(rs.getInt("pay_platform"));
				order.setTradeNo(rs.getString("trade_no"));
				order.setCreateTime(StringUtil.timestamp2datetime(rs.getTimestamp("create_time")));
				order.setPayTime(StringUtil.timestamp2datetime(rs.getTimestamp("pay_time")));
				
				order.setNotify_logistics_time(rs.getInt("notify_logistics_time"));
				order.setLogistics_no(rs.getString("logistics_no"));
				order.setLogistics_info(rs.getString("logistics_info"));			
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return order;
	}
	
	
	public int getOrderNO(String uid){	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		int count = 0;
		try{
			conn = DBUtil.getConnection();
			String sql = "select count(*) from `ssyh_main`.`order` where `uid`=? and `is_client_shown`=1 ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);

			rs = ps.executeQuery();
			if(rs.next()){				
				count = rs.getInt(1);			
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return count;
	}
	
	public List<Order> getOrderListByUid(String uid, int page, int pageSize){	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> orderList = new ArrayList<Order>();
		
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`order` where `uid`=? and `is_client_shown`=1 order by create_time desc limit  ?,? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, (page - 1)*pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setUid(rs.getString("uid"));
				order.setName(rs.getString("name"));
				order.setPhone_number(rs.getString("phone_number"));
				order.setAddress_province(rs.getString("address_province"));
				order.setAddress_city(rs.getString("address_city"));		
				order.setAddress_detail(rs.getString("address_detail"));
				order.setPostcode(rs.getString("postcode"));
				
				order.setOrderId(rs.getString("orderId"));
				order.setItemId(rs.getString("itemId"));
				order.setProviderId(rs.getString("providerId"));
				order.setIcon(rs.getString("icon"));
				order.setTitle(rs.getString("title"));			
				order.setSku(rs.getString("sku"));
				order.setNum(rs.getInt("num"));
				order.setPrice(rs.getDouble("price"));
				order.setTotalPrice(rs.getDouble("totalPrice"));
				order.setIsFlashSell(rs.getInt("isFlashSell"));
				
				order.setIsUseCredit(rs.getInt("is_use_credit"));
				order.setCreditNum(rs.getInt("credit_num"));
				order.setProviderId(rs.getString("providerId"));
				
				order.setStatus(rs.getInt("status"));
				order.setPayPlatform(rs.getInt("pay_platform"));
				order.setTradeNo(rs.getString("trade_no"));
				order.setCreateTime(rs.getTimestamp("create_time").toString());
				order.setPayTime(rs.getTimestamp("pay_time")==null?null:rs.getTimestamp("pay_time").toString());
				
				order.setNotify_logistics_time(rs.getInt("notify_logistics_time"));
				order.setLogistics_no(rs.getString("logistics_no"));
				order.setLogistics_info(rs.getString("logistics_info"));
				order.setDelivery_time(rs.getTimestamp("delivery_time")==null?null:rs.getTimestamp("delivery_time").toString());
				orderList.add(order);
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return orderList;
	}
	
	public boolean deleteOrder(String uid, String orderId){	
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;	
		try{
			conn = DBUtil.getConnection();
			String sql = "delete from `ssyh_main`.`order` where `uid`=? and `orderId`=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, orderId);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean updateClientShownStatus(String uid, String orderId, int status){	
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;	
		try{
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`order` set `is_client_shown`=? where `uid`=? and `orderId`=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, uid);
			ps.setString(3, orderId);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	/**
	 * 用户申请退款/退货，修改order表的return_status为 1：申请退款<br>
	 * return_status状态值，0：初始状态 1：申请退款 2：退款处理中  3：退款完成
	 * @param uid			uid
	 * @param orderId		退货订单ID
	 * @return				return_status状态更新是否OK
	 */
	public boolean updateOrderReturnStatus(String uid, String orderId)
	{	
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;	
		try {
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`order` set `return_status`=? "
					   + "where `uid`=? and `orderId`=? and (`status`=? or `status`=? or `status`=?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Value.order_return_status_unreceived);
			ps.setString(2, uid);
			ps.setString(3, orderId);
			ps.setInt(4, Value.order_status_paid);		// 2：已支付
			ps.setInt(5, Value.order_status_deliver);	// 3：已发货
			ps.setInt(6, Value.order_status_received);	// 4：已收货
			int i = ps.executeUpdate();
			if(i > 0) {
				flag = true;
			}			
		} catch(Exception e) {		
			logger.error("updateOrderReturnStatus exception", e);
		} finally {
			DBUtil.close(ps, conn);
		}
		return flag;
	}
	
	public boolean updateOrderCompliantStatus(String uid, String orderId, int compliantReason, String compliantContact){	
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;	
		try{
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`order` set `return_status`=?, `compliantReason`=? , `compliantContact`=? where `uid`=? and `orderId`=? and `status`=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Value.order_return_status_received);
			ps.setInt(2, compliantReason);
			ps.setString(3, compliantContact);
			ps.setString(4, uid);
			ps.setString(5, orderId);
			ps.setInt(6, Value.order_status_received);		
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean updateOrderStatusByOrderId(String orderId, int status){	
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;	
		try{
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`order` set `status`=? where `orderId`=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, orderId);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean updateNotifyLogisticsTime(String uid, String orderId){	
		Connection conn = null;
		PreparedStatement ps = null;
		boolean flag = false;	
		try{
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`order` set `notify_logistics_time`=`notify_logistics_time`+1 where `uid`=? and `orderId`=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, orderId);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public String insertPaymentToOrder(String orderIds){
		Connection conn = null;
		PreparedStatement ps = null;
		try{	
			String paymentId = System.currentTimeMillis()+"";
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`payment_to_order`(`payment_id`,`order_ids`) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, paymentId);
			ps.setString(2, orderIds);
			conn.setAutoCommit(true);
			int i = ps.executeUpdate();
			if(i>0){
				return paymentId;
			}else{
				return null;
			}
		}catch(Exception e){		
			logger.error("error stack",e);
			return null;
		}finally{
			DBUtil.close(ps,conn);
		}	
	}
	
	public String[] getOrderIdsFromPaymentToOrder(String paymentId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String order_ids = "";
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`payment_to_order` where `payment_id`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, paymentId);		
			rs = ps.executeQuery();
			if(rs.next()){
				order_ids = rs.getString("order_ids")==null?"":rs.getString("order_ids");				
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return order_ids.split(",");
	}
	
	public static void main(String[] args){
		OrderDAO.getInstance().getOrderListByUid("66013309", 1, 10);
	}


}
