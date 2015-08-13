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

import cn.suishou.bean.UserAddrBean;
import cn.suishou.utils.DBUtil;

/**
 * 用户收货地址维护DAO
 * @author  haol
 * @date	2014-12-29
 */
public class UserAddressDAO 
{
	public static Logger logger = Logger.getLogger(UserAddressDAO.class);
	/** 用户收货地址DAO单例 */
	private static UserAddressDAO instance;
	/** 获取用户收货地址DAO单例 */
	public static UserAddressDAO getUserAddressDAO() 
	{
		if (instance == null) {
			instance = new UserAddressDAO();
		}
		return instance;
	}
	
	/**
	 * 新增用户收货地址信息，返回新地址信息ID
	 * @param userAddrBean	用户收货地址Bean
	 * @return	新地址信息ID
	 */
	public long addUserAddr(UserAddrBean userAddrBean) 
	{
		long id = 0L;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO `ssyh_main`.`user_address`"
		+ "(`uid`,`name`,`phone_number`,`address_province`,`address_city`,`address_detail`,`postcode`,`is_default`) VALUES(?,?,?,?,?,?,?,?);";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, userAddrBean.getUid());
			ps.setString(2, userAddrBean.getName());
			ps.setString(3, userAddrBean.getPhoneNumber());
			ps.setString(4, userAddrBean.getAddressProvince());
			ps.setString(5, userAddrBean.getAddressCity());
			ps.setString(6, userAddrBean.getAddressDetail());
			ps.setString(7, userAddrBean.getPostcode());
			ps.setInt(8, userAddrBean.getIsDefault());
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
	 * 根据ID删除用户收货地址信息
	 * @param id	用户收货地址信息ID
	 * @return	删除结果
	 */
	public boolean deleteUserAddr(long id)
	{
		boolean deleteResult = false;	// 删除结果
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM `ssyh_main`.`user_address` WHERE ID = ?;";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			deleteResult = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			logger.error("deleteUserAddr exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(ps, conn);
		}
		return deleteResult;
	}
	
	/**
	 * 更新用户收货地址信息，不更新uid
	 * @param userAddrBean	用户收货地址Bean
	 * @return	更新结果
	 */
	public boolean updateUserAddr(UserAddrBean userAddrBean)
	{
		boolean updateResult = false;	// 更新结果
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `ssyh_main`.`user_address` "
		+ "SET `name` = ?,`phone_number` = ?,`address_province`=?,`address_city`=?,`address_detail`=?,`postcode`=?,`is_default`=? WHERE `ID` = ?;";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, userAddrBean.getName());
			ps.setString(2, userAddrBean.getPhoneNumber());
			ps.setString(3, userAddrBean.getAddressProvince());
			ps.setString(4, userAddrBean.getAddressCity());
			ps.setString(5, userAddrBean.getAddressDetail());
			ps.setString(6, userAddrBean.getPostcode());
			ps.setInt(7, userAddrBean.getIsDefault());
			ps.setLong(8, userAddrBean.getId());
			
			updateResult = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			logger.error("updateUserAddr exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(ps, conn);
		}
		return updateResult;
	}
	
	/**
	 * 用户设置了新的默认收货地址时，取消其之前的默认地址（如果有）
	 * @param uid	uid
	 * @return		取消其之前的默认地址（如果有）是否success
	 */
	public boolean updateNotDefaultByUid(String uid)
	{
		boolean updateResult = false;	// 更新结果
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `ssyh_main`.`user_address` SET `is_default` = 0 WHERE `uid` = ? and `is_default` = 1;";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			updateResult = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			logger.error("updateNotDefaultByUid exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(ps, conn);
		}
		return updateResult;
	}

	/**
	 * 用户修改某条收货地址是否为默认
	 * @param  addressId 收货地址ID
	 * @return isDefault 是否为用户默认收货地址，0：不是，1：是
	 */
	public boolean updateAddrDefaultById(long addressId, int isDefault)
	{
		boolean updateResult = false;	// 更新结果
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE `ssyh_main`.`user_address` SET `is_default` = ? WHERE `id` = ?;";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, isDefault);
			ps.setLong(2, addressId);
			updateResult = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			logger.error("updateAddrDefaultById exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(ps, conn);
		}
		return updateResult;
	}
	
	/**
	 * 根据uid获取当前用户全部收货地址信息list，设为默认的排在第一位
	 * @param uid	当前用户
	 * @return		当前用户全部收货地址信息list，设为默认的排在第一位
	 */
	public List<UserAddrBean> getAddrsByUid(String uid) 
	{
		List<UserAddrBean> addrList = new ArrayList<UserAddrBean>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `ssyh_main`.`user_address` WHERE `uid` = ? ORDER BY `is_default` desc;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while(rs.next()) {
				addrList.add(fetchResultSet(rs));
			}
		} catch (SQLException e) {
			logger.error("getAddrsByUid exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return addrList;
	}

	/**
	 * 根据id获取收货地址信息bean
	 * @param id	收货地址信息ID
	 * @return		收货地址信息bean
	 */
	public UserAddrBean getAddrsById(long id) 
	{
		UserAddrBean userAddrBean = new UserAddrBean();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `ssyh_main`.`user_address` WHERE `uid` = ?;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				userAddrBean = fetchResultSet(rs);
			}
		} catch (SQLException e) {
			logger.error("getAddrsByUid exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return userAddrBean;
	}

	/**
	 * 从查询ResultSet中解析出各属性
	 * @param resultSet		查询ResultSet
	 * @return				已设置各属性的bean
	 * @throws SQLException	SQL异常
	 */
	private static UserAddrBean fetchResultSet(ResultSet resultSet) throws SQLException
	{
		UserAddrBean bean = new UserAddrBean();
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
			if (metaData.getColumnName(i).equals("name")) {
				bean.setName(resultSet.getString("name"));
				continue;
			}
			if (metaData.getColumnName(i).equals("phone_number")) {
				bean.setPhoneNumber(resultSet.getString("phone_number"));
				continue;
			}
			if (metaData.getColumnName(i).equals("address_province")) {
				bean.setAddressProvince(resultSet.getString("address_province"));
				continue;
			}
			if (metaData.getColumnName(i).equals("address_city")) {
				bean.setAddressCity(resultSet.getString("address_city"));
				continue;
			}
			if (metaData.getColumnName(i).equals("address_detail")) {
				bean.setAddressDetail(resultSet.getString("address_detail"));
				continue;
			}
			if (metaData.getColumnName(i).equals("postcode")) {
				bean.setPostcode(resultSet.getString("postcode"));
				continue;
			}
			if (metaData.getColumnName(i).equals("is_default")) {
				bean.setIsDefault(resultSet.getInt("is_default"));
				continue;
			}
		}
		return bean;
	}
	
}
