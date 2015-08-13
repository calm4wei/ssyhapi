package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.suishou.bean.User;
import cn.suishou.common.Value;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.StringUtil;

public class UserDAO {
	private static Logger logger = Logger.getLogger(UserDAO.class);
	
	private static UserDAO instance = null;
	public static UserDAO getInstance(){
		if(instance == null) instance = new UserDAO();
		return instance;
	}
	
	public boolean isPhoneExist(String phoneNum){
		boolean flag = false;
		if(phoneNum == null || "".equals(phoneNum)){
			return flag;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select uid from `ssyh_main`.`user` where `phone_num`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, phoneNum);
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs,ps,conn);
		}
		return flag;
	}
	
	public boolean isUidExist(String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select uid from `ssyh_main`.`user` where `uid`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs,ps,conn);
		}
		return flag;
	}
	
	public User login(String phoneNum, String password){
		User user = new User();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`user` where `phone_num`=? and `password`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, phoneNum);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				user.setUid(rs.getString("uid"));
				user.setPhoneNum(rs.getString("phone_num"));	
				user.setTaobaoUid(rs.getString("taobao_uid"));
				user.setIcon(rs.getString("icon"));
			}else{
				user = null;
			}
		} catch (SQLException e){
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs,ps,conn);
		}
		return user;
	}
	
//	public boolean insertShiftUser(User user){
//		boolean flag = false;
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			String sql = "insert into `ssyh_main`.`user` (`uid`,`imei`,`imsi`,`taobao_nick`,`taobao_uid`,`platform`,`phone_num`,`password`,`is_from_old_version`) values(?,?,?,?,?,?,?,?,?) ";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, user.getUid());
//			ps.setString(2, user.getImei());
//			ps.setString(3, user.getImsi());
//			ps.setString(4, user.getTaobaoNick());
//			ps.setString(5, user.getTaobaoUid());	
//			ps.setString(6, user.getPlatform());
//			ps.setString(7, user.getPhoneNum());
//			ps.setString(8, user.getPassword());
//			ps.setInt(9, 1);
//			
//			ps.executeUpdate();			
//			flag = true;			
//		} catch (SQLException e) {
//			logger.error(e);
//		}finally{		
//			DBUtil.close(conn);
//		}
//		return flag;
//	}
	
	@SuppressWarnings("resource")
	public boolean insertShiftUser(User user, int jf_num, double fl_xj, int fl_add_jf){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql1 = "insert into `ssyh_main`.`user` (`uid`,`imei`,`imsi`,`taobao_nick`,`taobao_uid`,`channel`,`platform`,`phone_num`,`password`,`is_from_old_version`,`create_time`,`last_time`) values(?,?,?,?,?,?,?,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql1);
			ps.setString(1, user.getUid());
			ps.setString(2, user.getImei());
			ps.setString(3, user.getImsi());
			ps.setString(4, user.getTaobaoNick());
			ps.setString(5, user.getTaobaoUid());	
			ps.setString(6, user.getChannel());
			ps.setString(7, user.getPlatform());
			ps.setString(8, user.getPhoneNum());
			ps.setString(9, user.getPassword());
			ps.setInt(10, 1);		
			ps.setTimestamp(11, user.getCreatTime());
			ps.setTimestamp(12, user.getLastTime());			
			ps.executeUpdate();	
			
			String sql2 = "insert into `ssyh_main`.`trade_mx_"+ user.getUid().substring(0, 1) +"` (`trade_id`,`uid`,`jf_num`,`type`,`status`,`description`) values(?,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, StringUtil.generateTradeId());
			ps.setString(2, user.getUid());
			ps.setInt(3, jf_num);
			ps.setInt(4, Value.trade_mx_type_shift);
			ps.setInt(5, Value.trade_mx_status_success);
			ps.setString(6, Value.trade_mx_type_shift_desc);
			ps.executeUpdate();	
			
			String sql3 = "update `ssyh_main`.`user_account` set `fl_xj`=?, `fl_add_jf`=? where `uid`=? ";
			ps = conn.prepareStatement(sql3);
			ps.setDouble(1, fl_xj);
			ps.setDouble(2, fl_add_jf);
			ps.setString(3, user.getUid());
			ps.executeUpdate();	
			
			conn.commit();
			conn.setAutoCommit(true);		
			
			flag = true;			
		} catch (SQLException e) {
			logger.error("error stack",e);
			DBUtil.rollback(conn);
		}finally{		
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean updateIsClearJF(String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`user` set `is_clear_jf`=1 where `uid`=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);		
			ps.executeUpdate();			
			flag = true;			
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
			return flag;
	}
	
	public void registerByPhone(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			int i = 0;
			int j = -1;
			String uid = null;
			while(i<100 && j <1){
				i++;
				uid = getRandomUid();
				String sql = "insert into `ssyh_main`.`user`(uid,imei,imsi,creat_time,last_time,channel,platform,phone_num,password) values (?,?,?,?,?,?,?,?,?,?) ";
				ps = conn.prepareStatement(sql);
				ps.setString(1, uid);
				ps.setString(2,user.getImei());
				ps.setString(3, user.getImsi());
				long now = System.currentTimeMillis();
				ps.setTimestamp(4, StringUtil.long2timestamp(now));		
				ps.setTimestamp(5, StringUtil.long2timestamp(now));
				ps.setString(6, user.getChannel());
				ps.setString(7, user.getPlatform());
				ps.setString(8, user.getPhoneNum());
				ps.setString(9, user.getPassword());
				j = ps.executeUpdate();
			}

		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
	}
	
	private String getRandomUid(){
		Random r = new Random();
		int a = r.nextInt(100000000);
		int b = 10000000 + a;
		return b+"";
	}
	
	public String getPhone(String uid){
		String phoneNum = null;
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select phone_num from ssyh_main.user where `uid`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			if(rs.next()){
				phoneNum = rs.getString("phone_num");
			}else{
				phoneNum = null;
			}
		} catch (Exception e){
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs,ps,conn);
		}
		return phoneNum;
	}
	
	/**
	 * 修改密码
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public boolean resetPassword(String password ,String phoneNum){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps =null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update ssyh_main.user set `password`=? where `phone_num`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, phoneNum);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean recordeUserActive(String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps =null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update ssyh_main.user set `last_time`=?,`time`=`time`+1 where `uid`=?;";
			ps = conn.prepareStatement(sql);		
			ps.setTimestamp(1, StringUtil.long2timestamp(System.currentTimeMillis()));			
			ps.setString(2, uid);
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean updateUserIcon(String iconUrl, String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`user` set `icon`=? where `uid`=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, iconUrl);
			ps.setString(2, uid);		
			ps.executeUpdate();			
			flag = true;			
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
			return flag;
	}
	
	public User getUser(String uid){
		User user = new User();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`user` where `uid`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);		
			rs = ps.executeQuery();
			if(rs.next()){
				user.setUid(rs.getString("uid"));
				user.setPhoneNum(rs.getString("phone_num"));
				user.setTaobaoUid(rs.getString("taobao_uid"));
				user.setIcon(rs.getString("icon"));
			}
		} catch (SQLException e){
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs,ps,conn);
		}
		return user;
	}

}
