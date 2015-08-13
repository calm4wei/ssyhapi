package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.suishou.bean.UserAccount;
import cn.suishou.utils.DBUtil;

public class UserAccountDAO {
	private static Logger logger = Logger.getLogger(UserAccountDAO.class);
	
	private static UserAccountDAO instance = new UserAccountDAO();
	public static UserAccountDAO getInstance(){		
		return instance;
	}

	public boolean insertShiftUser(String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`user_account` (`uid`) values(?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);			
			ps.executeUpdate();			
			flag = true;			
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps, conn);
		}
		return flag;
	}
	
	
	public UserAccount getUserAccount(String uid){
		UserAccount ua = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`user_account` where `uid`=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);					
			rs = ps.executeQuery();
			if (rs.next()) {
				ua = new UserAccount();
				ua.setShiftJF(rs.getInt("shift_jf"));
				ua.setAcAddJF(rs.getInt("ac_add_jf"));
				ua.setFhAddJF(rs.getInt("fh_add_jf"));	
				ua.setPayJF(rs.getInt("pay_jf"));				
				ua.setYueJF(rs.getInt("yue_jf"));				
				ua.setFlXJ(rs.getDouble("fl_xj"));
				ua.setFlAddJF(rs.getInt("fl_add_jf"));	
			}					
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs, ps, conn);
		}
		return ua;
	}

}
