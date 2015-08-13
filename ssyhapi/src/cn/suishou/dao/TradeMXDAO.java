package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.suishou.bean.TradeMx;
import cn.suishou.common.Value;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.StringUtil;

public class TradeMXDAO {
	private static Logger logger = Logger.getLogger(TradeMXDAO.class);
	
	private static TradeMXDAO instance = new TradeMXDAO();
	public static TradeMXDAO getInstance(){		
		return instance;
	}
	
	public boolean insertJFPay(String uid, int jf_num, String title, int num){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`trade_mx_"+ uid.substring(0, 1) +"` (`trade_id`,`uid`,`jf_num`,`type`,`status`,`description`) values(?,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, StringUtil.generateTradeId());
			ps.setString(2, uid);
			ps.setInt(3, jf_num);
			ps.setInt(4, Value.trade_mx_type_pay);
			ps.setInt(5, Value.trade_mx_status_success);
			ps.setString(6, "兑换商品"+title+" X "+num);
			ps.executeUpdate();			
			flag = true;			
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public boolean insertShiftJF(String uid, int jf_num){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`trade_mx_"+ uid.substring(0, 1) +"` (`trade_id`,`uid`,`jf_num`,`type`,`status`) values(?,?,?,?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, StringUtil.generateTradeId());
			ps.setString(2, uid);
			ps.setInt(3, jf_num);
			ps.setInt(4, Value.trade_mx_type_shift);
			ps.setInt(5, Value.trade_mx_status_success);
			ps.executeUpdate();			
			flag = true;			
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
	public List<TradeMx> getJFAddMX(String uid, int page, int pageSize){	
		List<TradeMx> tradeMxList = new ArrayList<TradeMx>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`trade_mx_"+ uid.substring(0, 1) + "` where `uid`=? and type in (1,2,3) limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);	
			ps.setInt(2, (pageSize * (page - 1)));
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				TradeMx tradeMx = new TradeMx();
				tradeMx.setDescription(rs.getString("description"));
				tradeMx.setId(rs.getInt("id"));
				tradeMx.setInsert_time(StringUtil.timestamp2datetime(rs.getTimestamp("insert_time")));
				tradeMx.setJf_num(rs.getInt("jf_num"));
				tradeMx.setStatus(rs.getInt("status"));
				tradeMx.setTrade_id(rs.getString("trade_id"));
				tradeMx.setType(rs.getInt("type"));
				tradeMx.setUid(uid);
				tradeMx.setIcon("http://gi2.md.alicdn.com/bao/uploaded/i2/TB1ypNbHXXXXXc1XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
				tradeMxList.add(tradeMx);
			}					
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs, ps, conn);
		}
		return tradeMxList;
	}
	
	public int getJFAddMXNum(String uid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from `ssyh_main`.`trade_mx_"+ uid.substring(0, 1) + "` where `uid`=? and type in (1,2,3)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);

			rs = ps.executeQuery();
			if (rs.next()) {			
				count = (rs.getInt(1));			
			}					
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs, ps, conn);
		}
		return count;
	}
	
	
	public int getJFPayMXNum(String uid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from `ssyh_main`.`trade_mx_"+ uid.substring(0, 1) + "` where `uid`=? and type in (4)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = (rs.getInt(1));		
			}					
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs, ps, conn);
		}
		return count;
	}
	
	public List<TradeMx> getJFPayMX(String uid, int page, int pageSize){
		List<TradeMx> tradeMxList = new ArrayList<TradeMx>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`trade_mx_"+ uid.substring(0, 1) + "` where `uid`=? and type in (4) limit ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);	
			ps.setInt(2, (pageSize * (page - 1)));
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while (rs.next()) {
				TradeMx tradeMx = new TradeMx();
				tradeMx.setDescription(rs.getString("description"));
				tradeMx.setId(rs.getInt("id"));
				tradeMx.setInsert_time(StringUtil.timestamp2datetime(rs.getTimestamp("insert_time")));
				tradeMx.setJf_num(rs.getInt("jf_num"));
				tradeMx.setStatus(rs.getInt("status"));
				tradeMx.setTrade_id(rs.getString("trade_id"));
				tradeMx.setType(rs.getInt("type"));
				tradeMx.setUid(uid);
				tradeMx.setIcon("http://gi2.md.alicdn.com/bao/uploaded/i2/TB1ypNbHXXXXXc1XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
				tradeMxList.add(tradeMx);	
			}					
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs, ps, conn);
		}
		return tradeMxList;
	}
	
}
