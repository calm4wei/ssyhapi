package cn.suishou.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.suishou.bean.Activity;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.StringUtil;

public class ActivityJoinManager {
	protected static Logger logger = Logger.getLogger(ActivityJoinManager.class);
	
	private static ActivityJoinManager instance = null;
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
	private static SimpleDateFormat ft1 = new SimpleDateFormat("yyyyMMdd");
	
	private static final int NORMAL = 1;	
	
	private ActivityJoinManager(){}
	
	public static ActivityJoinManager getInstance(){
		if(instance == null) instance = new ActivityJoinManager();
		return instance;
	}	

	public long getLastJoinTime(String uid, String activityId){
		long lastTime = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`activity_join` where `uid` = ? and `activity_id`=? order by `create_time` desc;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, activityId);
			rs = ps.executeQuery();
			if(rs.next()){
				lastTime = StringUtil.timestamp2long(rs.getTimestamp("create_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		    DBUtil.close(rs, ps, conn);
		}
		return lastTime;
	}
	
	public int join(Activity activity, String uid){
		return join(activity, uid, NORMAL);
	}
	
	public int join(Activity activity, String uid, int status){
		return join(activity, uid, 0, status);
	}
	
	public int join(Activity activity, String uid, int runningDay, int status){
		return join(activity, uid, runningDay, status, "" + System.currentTimeMillis());
	}
	
	public int join(Activity activity, String uid, String activityUniqueId, int status){
		return join(activity, uid, 0, status, activityUniqueId);
	}
	
	public int join(Activity activity, String uid, int runningDay, String activityUniqueId){
		return join(activity, uid, runningDay, NORMAL, activityUniqueId);
	}
	
	public int join(Activity activity, String uid, int runningDay,int status, String activityUniqueId){
		int ret = 0;	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			if(activity == null || activity.getId() == null || "".equals(activity.getId()) || uid == null || "".equals(uid)){
				return ret;
			}
			
			if(isExsit(activity.getId(), activityUniqueId)){
				ret = 2;
				return ret;
			}
			
			String sql = "insert into `ssyh_main`.`activity_join`(`id`,`uid`,`date`,`running_days`,`create_time`,`jf_num`,`activity_id`,`activity_name`,`status`,`activity_unique_id`) values(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			String today = ft.format(new Date());
			
			int n = -1;
			int j = 0;
			String tradeId = null;
			while(n<0 && j++<10){
				tradeId = getTradeId(uid);
				ps.setString(1, tradeId);
				ps.setString(2, uid);
				ps.setString(3, today);
				ps.setInt(4, runningDay);
				ps.setTimestamp(5, StringUtil.long2timestamp(System.currentTimeMillis()));
				ps.setInt(6, activity.getJfNum());
				ps.setString(7, activity.getId());
				ps.setString(8, activity.getName());
				ps.setInt(9, status);
				ps.setString(10, activityUniqueId);
				n = ps.executeUpdate();
				if(n > 0){
					ret = 1;					
				}
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		} catch (Exception e) {
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return ret;
	}
	
	private boolean isExsit(String activityId, String uniqueId){
		boolean flag = false;	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id from youhui_cn_fanli.tyh_activity_join where activity_id =? and `activity_unique_id` = ?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, activityId);
			ps.setString(2, uniqueId);
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (SQLException e) {
			logger.error("error stack",e);
		} catch (Exception e) {
			logger.error("error stack",e);
		} finally{
			DBUtil.close(rs, ps, conn);
		}
		return flag;
	}
	
	private String getTradeId(String uid){
		String today = ft1.format(new Date());
		int i = 8 - uid.length();
		String uuid = uid;
		if(i >= 0){
			for(;i>0;i--){
				uuid = "0"+uuid;
			}
		}else{
			uuid = uuid.substring(-i, uuid.length());
		}
		String cuid = "";
		for(int j = 0; j < uuid.length()/2; j++){
			int s = Integer.parseInt(String.valueOf(uuid.charAt(j)));
			int e = Integer.parseInt(String.valueOf(uuid.charAt(uuid.length()-j-1)));
			cuid += (s + e)%10;
		}
		String rank = getRankNum(8);
		return today + rank + cuid;
	}
	
	private String getRankNum(int size){
		StringBuffer sb = new StringBuffer();
		Random rm = new Random();
		for(int i = 0;i<size;i++){
			sb.append(rm.nextInt(10));
		}
		return sb.toString();
	}
	
}
