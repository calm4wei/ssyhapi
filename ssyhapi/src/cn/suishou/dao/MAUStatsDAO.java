package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import cn.suishou.bean.MAUStatsBean;
import cn.suishou.utils.DBUtil;

/**
 * 用户月度活跃时长统计DAO
 * @author  haol
 * @date	2014-12-31
 */
public class MAUStatsDAO 
{
	public static Logger logger = Logger.getLogger(MAUStatsDAO.class);
	/** 用户月度活跃时长统计DAO单例 */
	private static MAUStatsDAO instance;
	/** 获取用户月度活跃时长统计DAO单例 */
	public static MAUStatsDAO getInstance() 
	{
		if (instance == null) {
			instance = new MAUStatsDAO();
		}
		return instance;
	}
	
	/**
	 * 新增或更新用户月度活跃时长统计，返回执行结果
	 * @param bean	用户月度活跃时长统计Bean
	 * @return		执行结果
	 */
	public boolean addOrUpdateMAUStats(MAUStatsBean bean) 
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `ssyh_main`.`mau_stats`"
				   + "(`month_str`,`uid`,`stay_seconds`) VALUES(?,?,?) "
				   + "ON DUPLICATE KEY UPDATE `stay_seconds`=`stay_seconds` + ?;";
		
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			// insert参数
			ps.setString(1, bean.getMonthStr());
			ps.setString(2, bean.getUid());
			ps.setInt(3, bean.getStaySeconds());
			// update参数
			ps.setInt(4, bean.getStaySeconds());
			
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			logger.error("addOrUpdateMAUStats exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(null, ps, conn);
		}
		return result;
	}

	/**
	 * 新增或更新用户月度活跃时长统计，返回执行结果
	 * @param uidList	  月度活跃uid分页清单
	 * @param millisList 对应用户在线时长list
	 * @param monthStr	  统计月度，yyyyMM
	 * @return			  执行结果
	 */
	public boolean batchAddOrUpdateMAUStats(List<String> uidList, List<String> millisList, String monthStr) 
	{
		if (uidList == null || millisList == null || uidList.isEmpty() || millisList.isEmpty()) {
			return false;
		}
		if (uidList.size() != millisList.size()) {
			return false;
		}
		int listSize = uidList.size();	
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `ssyh_main`.`mau_stats`"
				   + "(`month_str`,`uid`,`stay_seconds`,`stay_minutes`,`stay_hours`) VALUES(?,?,?,?,?) "
				   + "ON DUPLICATE KEY UPDATE `stay_seconds` = ?,`stay_minutes`=?,`stay_hours`=?;";
		try {
			conn = DBUtil.getConnection();
			// 关闭事务自动提交
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			for(int i = 0;i < listSize;i++) {
				String uid = uidList.get(i);
				String millisStr = millisList.get(i);
				long stayMillis = NumberUtils.isNumber(millisStr) ? Long.parseLong(millisStr) : 0L;
				MAUStatsBean bean = new MAUStatsBean(monthStr, uid, stayMillis);
				// insert参数
				ps.setString(1, bean.getMonthStr());
				ps.setString(2, bean.getUid());
				ps.setInt(3, bean.getStaySeconds());
				ps.setInt(4, bean.getStayMinutes());
				ps.setDouble(5, bean.getStayHours());
				// update参数
				ps.setInt(6, bean.getStaySeconds());
				ps.setInt(7, bean.getStayMinutes());
				ps.setDouble(8, bean.getStayHours());
	            ps.addBatch();
			}
			ps.executeBatch();
			// 关闭事务自动提交
			conn.setAutoCommit(true);
			result = true;
		} catch (SQLException e) {
			result = false;
			logger.error("addOrUpdateMAUStats exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(null, ps, conn);
		}
		return result;
	}

	/**
	 * 新增或更新用户月度活跃时长统计，返回执行结果
	 * @param bean	用户月度活跃时长统计Bean
	 * @return		执行结果
	 */
	public boolean batchAddOrUpdateMAUStats(List<MAUStatsBean> beans) 
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `ssyh_main`.`mau_stats`"
				   + "(`month_str`,`uid`,`stay_seconds`,`stay_minutes`,`stay_hours`) VALUES(?,?,?,?,?) "
				   + "ON DUPLICATE KEY UPDATE `stay_seconds` = ?,`stay_minutes`=?,`stay_hours`=?;";
		try {
			conn = DBUtil.getConnection();
			// 关闭事务自动提交
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			for (MAUStatsBean bean : beans) {
				// insert参数
				ps.setString(1, bean.getMonthStr());
				ps.setString(2, bean.getUid());
				ps.setInt(3, bean.getStaySeconds());
				ps.setInt(4, bean.getStayMinutes());
				ps.setDouble(5, bean.getStayHours());
				// update参数
				ps.setInt(6, bean.getStaySeconds());
				ps.setInt(7, bean.getStayMinutes());
				ps.setDouble(8, bean.getStayHours());
	            ps.addBatch();
			}
			ps.executeBatch();
			// 关闭事务自动提交
			conn.setAutoCommit(true);
			result = true;
		} catch (SQLException e) {
			logger.error("addOrUpdateMAUStats exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(null, ps, conn);
		}
		return result;
	}

	/**
	 * 新增或累加用户月度活跃时长统计，返回执行结果
	 * @param bean	用户月度活跃时长统计Bean
	 * @return		执行结果
	 */
	public boolean batchAddOrIncreaseMAUStats(Collection<MAUStatsBean> beans) 
	{
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO `ssyh_main`.`mau_stats`"
				   + "(`month_str`,`uid`,`stay_seconds`) VALUES(?,?,?) "
				   + "ON DUPLICATE KEY UPDATE `stay_seconds`=`stay_seconds` + ?;";
		try {
			conn = DBUtil.getConnection();
			// 关闭事务自动提交
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			for (MAUStatsBean bean : beans) {
				// insert参数
				ps.setString(1, bean.getMonthStr());
				ps.setString(2, bean.getUid());
				ps.setInt(3, bean.getStaySeconds());
				// update参数
				ps.setInt(4, bean.getStaySeconds());
	            ps.addBatch();
			}
			ps.executeBatch();
			// 关闭事务自动提交
			conn.setAutoCommit(true);
			result = true;
		} catch (SQLException e) {
			logger.error("addOrUpdateMAUStats exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(null, ps, conn);
		}
		return result;
	}
	
	/**
	 * 删除指定月份之前的 所有月度活跃时长统计数据
	 * @param yearMonthInt 指定月份，整形
	 * @return			       删除结果
	 */
	public boolean deleteBeforeMonth(int yearMonthInt)
	{
		boolean deleteResult = false;	// 删除结果
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM `ssyh_main`.`mau_stats` WHERE `month_str` < ?;";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, yearMonthInt);
			deleteResult = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			logger.error("deleteBeforeMonth exception:" + e.getMessage(), e);
		} finally {
			DBUtil.close(ps, conn);
		}
		return deleteResult;
	}
	
}
