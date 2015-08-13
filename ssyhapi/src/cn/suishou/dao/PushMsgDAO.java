package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import cn.suishou.bean.Action;
import cn.suishou.bean.PushMessage;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.StringUtil;

public class PushMsgDAO {
	protected static Logger logger = Logger.getLogger(PushMsgDAO.class);
	private static PushMsgDAO instance = null;

	private PushMsgDAO(){
	}
	
	public static PushMsgDAO getInstance(){
		if(instance == null) instance = new PushMsgDAO();
		return instance;
	}
	
	public boolean update(PushMessage bean){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`pushmessage` set `code`=? ,`content`=?, start_time=?, `end_time`=? , `range`=? ,`platform` =? ,`formula`=? , `action_type`=? , `action_value`=?, `title` = ?, `icon` = ?  where `pid` = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bean.getCode());
			ps.setString(2, bean.getContent());
			ps.setTimestamp(3, StringUtil.datetime2timestamp(bean.getStarttime()));
			ps.setTimestamp(4, StringUtil.datetime2timestamp(bean.getEndtime()));		
			ps.setInt(5, bean.getRange());
			ps.setString(6, bean.getPlatform());
			ps.setString(7, bean.getFormula());		
			ps.setString(8, bean.getAction().getActionType());
			ps.setString(9, bean.getAction().getActionValue());
			ps.setString(10, bean.getTitle());
			ps.setString(11, bean.getIcon());
			ps.setString(12, bean.getpId());
			int i =ps.executeUpdate();
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
	
	public boolean add(PushMessage bean){  
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`pushmessage`( `title` ,`code` ,`content`,`start_time` , `end_time` ,`range` ,`platform` ," +
					" `formula` , `action_type` , `action_value`,`pid`,`icon`) values(?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, bean.getTitle());
			s.setInt(2, bean.getCode());
			s.setString(3, bean.getContent());
			s.setTimestamp(4, StringUtil.datetime2timestamp(bean.getStarttime()));
			s.setTimestamp(5, StringUtil.datetime2timestamp(bean.getEndtime()));			
			s.setInt(6, bean.getRange());
			s.setString(7, bean.getPlatform());	
			s.setString(8, bean.getFormula());		
			s.setString(9, bean.getAction().getActionType());
			s.setString(10, bean.getAction().getActionValue());
			s.setString(11, bean.getpId());
			s.setString(12, bean.getIcon());	
			int i = s.executeUpdate();
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
	
	public boolean addIndividual(String uid, PushMessage bean){  
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`pushmessage_individual`(`uid`, `title`,`content`,`start_time` , `end_time` ,`platform` ," +
					"`action_type` , `action_value`,`icon`) values(?,?,?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);	
			s.setString(2, bean.getTitle());		
			s.setString(3, bean.getContent());
			s.setTimestamp(4, StringUtil.datetime2timestamp(bean.getStarttime()));
			s.setTimestamp(5, StringUtil.datetime2timestamp(bean.getEndtime()));						
			s.setString(6, bean.getPlatform());				
			s.setString(7, bean.getAction().getActionType());
			s.setString(8, bean.getAction().getActionValue());		
			s.setString(9, bean.getIcon());	
			int i = s.executeUpdate();
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
	
	public boolean addIndividual(HashSet<String> uidSet, PushMessage bean){  
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`pushmessage_individual`(`uid`, `title`,`content`,`start_time` , `end_time` ,`platform` ," +
					"`action_type` , `action_value`,`icon`) values(?,?,?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for(String uid : uidSet){
				s.setString(1, uid);	
				s.setString(2, bean.getTitle());		
				s.setString(3, bean.getContent());
				s.setTimestamp(4, StringUtil.datetime2timestamp(bean.getStarttime()));
				s.setTimestamp(5, StringUtil.datetime2timestamp(bean.getEndtime()));						
				s.setString(6, bean.getPlatform());				
				s.setString(7, bean.getAction().getActionType());
				s.setString(8, bean.getAction().getActionValue());		
				s.setString(9, bean.getIcon());	
				s.addBatch();
			}
			
			conn.setAutoCommit(true);
			int i[] = s.executeBatch();
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
	
	public boolean addIndividual(HashSet<String> uidSet, PushMessage bean, String platform){  
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "insert into `ssyh_main`.`pushmessage_individual`(`uid`, `title`,`content`,`start_time` , `end_time` ,`platform` ," +
					"`action_type` , `action_value`,`icon`) values(?,?,?,?,?,?,?,?,?);";
			PreparedStatement s = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for(String uid : uidSet){
				s.setString(1, uid);	
				s.setString(2, bean.getTitle());		
				s.setString(3, bean.getContent());
				s.setTimestamp(4, StringUtil.datetime2timestamp(bean.getStarttime()));
				s.setTimestamp(5, StringUtil.datetime2timestamp(bean.getEndtime()));						
				s.setString(6, platform);
				s.setString(7, bean.getAction().getActionType());
				s.setString(8, bean.getAction().getActionValue());		
				s.setString(9, bean.getIcon());	
				s.addBatch();
			}
			
			conn.setAutoCommit(true);
			int i[] = s.executeBatch();
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
	
	public static HashSet<String> getAllUidsByTable(String tableName){
		HashSet<String> uidSet = new HashSet<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select `uid` from " + tableName + ";";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {				
				uidSet.add(rs.getString("uid"));
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps,conn);
		}
		return uidSet;
	}
	
	public PushMessage getMsg(String pid){
		PushMessage bean = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`pushmessage` where pid = '" + pid+"';";
			rs = conn.createStatement().executeQuery(sql);
			if(rs.next()) {
				bean = new PushMessage();
				bean.setpId(pid);
				bean.setAction(new Action(rs.getString("action_type"),rs.getString("action_value")));
		
				bean.setCode(rs.getInt("code"));
				bean.setContent(rs.getString("content"));
				bean.setStarttime(StringUtil.timestamp2datetime(rs.getTimestamp("start_time")));
				bean.setEndtime(StringUtil.timestamp2datetime(rs.getTimestamp("end_time")));
				bean.setFormula(rs.getString("formula"));			
				bean.setPlatform(rs.getString("platform"));			
				bean.setRange(rs.getInt("range"));
				bean.setTitle(rs.getString("title"));
				bean.setIcon(rs.getString("icon"));
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{			
			DBUtil.close(ps,conn);
		}
		return bean;
	}
	
	public List<HashMap<String, Object>> getMsgList(String uid, int page, int pageSize){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionSlave();
			String sql = "select * from "
					+ "(SELECT `title`,`content`,`action_value`,`icon`,`start_time` from `ssyh_main`.`pushmessage` where `range`=0 and `status`=0 "
					+ "UNION SELECT `title`,`content`,`action_value`,`icon`,`start_time` from `ssyh_main`.`pushmessage_individual` where uid=?) aa "
					+ "ORDER BY aa.start_time DESC limit  ?,?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setInt(2, (page - 1)*pageSize);
			ps.setInt(3, pageSize);
			rs = conn.createStatement().executeQuery(sql);
			if(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("title", rs.getString("title"));
				map.put("content", rs.getString("content"));
				map.put("clickUrl", rs.getString("action_value"));
				map.put("icon", rs.getString("icon"));
				map.put("title", rs.getString("title"));
				map.put("time", rs.getString("start_time"));
				
				list.add(map);							
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{			
			DBUtil.close(ps,conn);
		}
		return list;
	}
	
	public List<HashMap<String, Object>> getMsgList(String uid){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnectionSlave();
			String sql = "select * from "
					+ "(SELECT `title`,`content`,`action_value`,`icon`,`start_time` from `ssyh_main`.`pushmessage` where `range`=0 and `status`=0 "
					+ "UNION SELECT `title`,`content`,`action_value`,`icon`,`start_time` from `ssyh_main`.`pushmessage_individual` where uid=?) aa "
					+ "ORDER BY aa.start_time DESC";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			rs = conn.createStatement().executeQuery(sql);
			if(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("title", rs.getString("title"));
				map.put("content", rs.getString("content"));
				map.put("clickUrl", rs.getString("action_value"));
				map.put("icon", rs.getString("icon"));
				map.put("title", rs.getString("title"));
				map.put("time", rs.getString("start_time"));
				
				list.add(map);							
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{			
			DBUtil.close(ps,conn);
		}
		return list;
	}

}
