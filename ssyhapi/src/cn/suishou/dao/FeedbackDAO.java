package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.suishou.bean.Feedback;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.DateUtil;

/**
 * 意见反馈/客服回复DAO
 * @author  haol
 * @date	2015-01-07
 */
public class FeedbackDAO 
{
	public static Logger logger = Logger.getLogger(FeedbackDAO.class);
	/** 意见反馈/客服回复DAO单例 */
	private static FeedbackDAO instance;
	/** 获取意见反馈/客服回复DAO单例 */
	public static FeedbackDAO getInstance() 
	{
		if (instance == null) {
			instance = new FeedbackDAO();
		}
		return instance;
	}
	
	/**
	 * 新增或更新意见反馈/客服回复，返回执行结果
	 * @param bean	意见反馈/客服回复Bean
	 * @return		生成的ID
	 */
	public long addFeedback(Feedback bean) 
	{
		long id = 0L;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "INSERT INTO `ssyh_main`.`feed_back`(`uid`,`nick`,`avatar`,`timestamp`,`type`,`content`) VALUES (?,?,?,?,?,?);";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, bean.getUid());
			ps.setString(2, bean.getNick());
			ps.setString(3, bean.getAvatar());
			ps.setTimestamp(4, DateUtil.stampToSQLTimestamp(System.currentTimeMillis()));
			ps.setInt(5, bean.getType());
			ps.setString(6, bean.getContent());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();	// 获取新增生成的ID，返回
			if (rs.next()) {
                id = rs.getLong(1);
            }
		} catch (SQLException e) {
			logger.error("addFeedback exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(null, ps, conn);
		}
		return id;
	}

	/**
	 * 获取user指定type的反馈list
	 * @param uid	 uid
	 * @param type	  反馈类型，1：订单问题；2：功能改进
	 * @param lastId 上次分页最后一条数据id，作为本次分页的开始
	 * @return		 user指定type的反馈list
	 */
	public List<Feedback> getPagedFeedbacksByUid(String uid, int type, long cursorId) 
	{
		List<Feedback> list = new ArrayList<Feedback>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `ssyh_main`.`feed_back` WHERE `uid` = ? and `type` = ? and `id` > ? ORDER BY `timestamp` desc limit ?,?;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, type);
			ps.setLong(3, cursorId);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(fetchResultSet(rs));
			}
		} catch (SQLException e) {
			logger.error("getFeedbacksByUid exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 获取user指定type的反馈list
	 * @param uid	uid
	 * @param type	反馈类型，1：订单问题；2：功能改进
	 * @return		user指定type的反馈list
	 */
	public List<Feedback> getFeedbacksByUid(String uid, int type) 
	{
		List<Feedback> list = new ArrayList<Feedback>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM `ssyh_main`.`feed_back` WHERE `uid` = ? and `type` = ? ORDER BY `timestamp` desc;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, type);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(fetchResultSet(rs));
			}
		} catch (SQLException e) {
			logger.error("getFeedbacksByUid exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 获取user指定type的反馈总数
	 * @param uid	uid
	 * @param type	反馈类型，1：订单问题；2：功能改进
	 * @return		user指定type的反馈总数
	 */
	public int countFeedbacksByUid(String uid, int type) 
	{
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM `ssyh_main`.`feed_back` WHERE `uid` = ? and `type` = ?;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, type);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("countFeedbacksByUid exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return count;
	}

	/**
	 * 从查询ResultSet中解析出各属性
	 * @param resultSet		查询ResultSet
	 * @return				已设置各属性的bean
	 * @throws SQLException	SQL异常
	 */
	private static Feedback fetchResultSet(ResultSet resultSet) throws SQLException
	{
		Feedback bean = new Feedback();
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
			if (metaData.getColumnName(i).equals("nick")) {
				bean.setNick(resultSet.getString("nick"));
				continue;
			}
			if (metaData.getColumnName(i).equals("avatar")) {
				bean.setAvatar(resultSet.getString("avatar"));
				continue;
			}
			if (metaData.getColumnName(i).equals("timestamp")) {
				bean.setTimestamp(resultSet.getTimestamp("timestamp"));
				continue;
			}
			if (metaData.getColumnName(i).equals("is_replied")) {
				bean.setIsReplied(resultSet.getInt("is_replied"));
				continue;
			}
			if (metaData.getColumnName(i).equals("reply_to_uid")) {
				bean.setReplyToUid(resultSet.getString("reply_to_uid"));
				continue;
			}
			if (metaData.getColumnName(i).equals("type")) {
				bean.setType(resultSet.getInt("type"));
				continue;
			}
			if (metaData.getColumnName(i).equals("content")) {
				bean.setContent(resultSet.getString("content"));
				continue;
			}
		}
		return bean;
	}
	
}
