package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.suishou.bean.ReturnOrderBean;
import cn.suishou.utils.DBUtil;

/**
 * 退款/退货记录DAO
 * @author  haol
 * @date	2014-12-31
 */
public class ReturnOrderDAO 
{
	public static Logger logger = Logger.getLogger(ReturnOrderDAO.class);
	/** 退款/退货记录DAO单例 */
	private static ReturnOrderDAO instance;
	/** 获取退款/退货记录DAO单例 */
	public static ReturnOrderDAO getReturnOrderDAO() 
	{
		if (instance == null) {
			instance = new ReturnOrderDAO();
		}
		return instance;
	}
	
	/**
	 * 新增退款/退货记录，返回新退款/退货记录ID
	 * @param bean	退款/退货记录Bean
	 * @return	新退款/退货记录ID
	 */
	public long addReturnOrder(ReturnOrderBean bean) 
	{
		long id = 0L;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO `ssyh_main`.`return_order`"
		+ "(`uid`,`order_id`,`is_received`,`reason`,`refund_amount`,`remark`,`img1`,`img2`,`img3`,`img4`) VALUES(?,?,?,?,?,?,?,?,?,?);";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getUid());
			ps.setString(2, bean.getOrderId());
			ps.setInt(3, bean.getIsReceived());
			ps.setInt(4, bean.getReason());
			ps.setDouble(5, bean.getRefundAmount());
			ps.setString(6, bean.getRemark());
			ps.setString(7, bean.getImg1());
			ps.setString(8, bean.getImg2());
			ps.setString(9, bean.getImg3());
			ps.setString(10, bean.getImg4());
			ps.executeUpdate();
			// 获取新增生成的ID，返回
	        rs = ps.getGeneratedKeys();
	        if (rs.next()) {
                id = rs.getLong(1);
            }
		} catch (SQLException e) {
			logger.error("addUserAddr exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return id;
	}

	/**
	 * 根据uid获取当前用户全部退款/退货记录list
	 * @param uid	当前用户
	 * @return		当前用户全部退款/退货记录list
	 */
	public List<ReturnOrderBean> getByUid(String uid) 
	{
		List<ReturnOrderBean> list = new ArrayList<ReturnOrderBean>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `ssyh_main`.`return_order` WHERE `uid` = ?;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(fetchResultSet(rs));
			}
		} catch (SQLException e) {
			logger.error("getAddrsByUid exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 根据id获取退款/退货记录bean，获取不到返回null（用来判断该笔订单是否已申请过退款/退货）
	 * @param uid		uid
	 * @param orderId	订单ID
	 * @return			退款/退货记录bean
	 */
	public ReturnOrderBean getByOrderId(String uid, String orderId) 
	{
		ReturnOrderBean bean = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `ssyh_main`.`return_order` WHERE `uid` = ? AND `order_id` = ?;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, orderId);
			rs = ps.executeQuery();
			if(rs.next()) {
				bean = new ReturnOrderBean();
				bean = fetchResultSet(rs);
			}
		} catch (SQLException e) {
			logger.error("getByOrderId exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return bean;
	}

	/**
	 * 根据id获取退款/退货记录bean
	 * @param id	退款/退货记录ID
	 * @return		退款/退货记录bean
	 */
	public ReturnOrderBean getById(long id) 
	{
		ReturnOrderBean bean = new ReturnOrderBean();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `ssyh_main`.`return_order` WHERE `id` = ?;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				bean = fetchResultSet(rs);
			}
		} catch (SQLException e) {
			logger.error("getById exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return bean;
	}

	/**
	 * 从查询ResultSet中解析出各属性
	 * @param resultSet		查询ResultSet
	 * @return				已设置各属性的bean
	 * @throws SQLException	SQL异常
	 */
	private static ReturnOrderBean fetchResultSet(ResultSet resultSet) throws SQLException
	{
		ReturnOrderBean bean = new ReturnOrderBean();
		ResultSetMetaData metaData = resultSet.getMetaData();	// ResultSet元数据
		int columnCount = metaData.getColumnCount();			// 获取ResultSet列数
		for(int i = 1; i <= columnCount; i++) {
			if (metaData.getColumnName(i).equals("id")) {
				bean.setId(resultSet.getLong("id"));
				continue;
			}
			if (metaData.getColumnName(i).equals("uid")) {
				bean.setUid(resultSet.getString("uid"));
				continue;
			}
			if (metaData.getColumnName(i).equals("order_id")) {
				bean.setOrderId(resultSet.getString("order_id"));
				continue;
			}
			if (metaData.getColumnName(i).equals("is_received")) {
				bean.setIsReceived(resultSet.getInt("is_received"));
				continue;
			}
			if (metaData.getColumnName(i).equals("reason")) {
				bean.setReason(resultSet.getInt("reason"));
				continue;
			}
			if (metaData.getColumnName(i).equals("refund_amount")) {
				bean.setRefundAmount(resultSet.getDouble("refund_amount"));
				continue;
			}
			if (metaData.getColumnName(i).equals("remark")) {
				bean.setRemark(resultSet.getString("remark"));
				continue;
			}
			if (metaData.getColumnName(i).equals("img1")) {
				bean.setImg1(resultSet.getString("img1"));
				continue;
			}
			if (metaData.getColumnName(i).equals("img2")) {
				bean.setImg2(resultSet.getString("img2"));
				continue;
			}
			if (metaData.getColumnName(i).equals("img3")) {
				bean.setImg3(resultSet.getString("img3"));
				continue;
			}
			if (metaData.getColumnName(i).equals("img4")) {
				bean.setImg4(resultSet.getString("img4"));
				continue;
			}
		}
		return bean;
	}
	
}
