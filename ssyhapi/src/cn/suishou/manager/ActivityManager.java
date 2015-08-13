package cn.suishou.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.apache.log4j.Logger;

import cn.suishou.activity.ActivityConfig;
import cn.suishou.bean.Activity;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.DateUtil;
import cn.suishou.utils.StringUtil;

public class ActivityManager{
	protected static Logger logger = Logger.getLogger(ActivityManager.class);
	
	private static ActivityManager instance = null;
	
	private ActivityManager() {
	}

	public static ActivityManager getInstance() {
		if (instance == null)
			instance = new ActivityManager();
		return instance;
	}
	
	public Activity getActivityByKey(String key){
		Activity bean = new Activity();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`activity` where `key`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			if(rs.next()){
				bean.setId(rs.getString("id"));
				bean.setDescription(rs.getString("description"));
				bean.setName(rs.getString("name"));
				bean.setFrequency(rs.getInt("frequency"));
				bean.setJfNum(rs.getInt("jf_num"));
				bean.setKey(rs.getString("key"));		
				bean.setStartTime(StringUtil.timestamp2datetime(rs.getTimestamp("start_time")));
				bean.setEndTime(StringUtil.timestamp2datetime(rs.getTimestamp("end_time")));
				bean.setCreateTime(StringUtil.timestamp2datetime(rs.getTimestamp("create_time")));
			}else{
				bean = null;
			}
		}catch (Exception e) {
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return bean;
	}
	
	public boolean isExsitKey(String key){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select `id` from `ssyh_main`.`activity` where `key`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			if(rs.next()){
				flag = true;
			}
		}catch (Exception e) {
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return flag;
	}
	
	public int joinActivity(String key, String uid){
		int ret = ActivityConfig.ACTIVITY_EXCEPTION;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn =  DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`activity` where `key`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			if(rs.next()){
				long startTime = StringUtil.timestamp2long(rs.getTimestamp("start_time"));
				long endTime = StringUtil.timestamp2long(rs.getTimestamp("end_time"));
				if(System.currentTimeMillis() < startTime){
					ret = ActivityConfig.ACTIVITY_NOT_START;          //活动未开始
					return ret;
				}
				if(System.currentTimeMillis() > endTime){
					ret = ActivityConfig.ACTIVITY_HAS_END;            //活动已结束
					return ret;
				}
				String activityId = rs.getString("id");
				long lastJoinTime = ActivityJoinManager.getInstance().getLastJoinTime(uid, activityId);
				int frequency = rs.getInt("frequency");
				boolean allow = false;
				if(frequency == 1){          //只能参加一次
					if(lastJoinTime == 0){
						allow = true;
					}
				}else if(frequency == 2){     //一天参加一次
					if(checkDay(lastJoinTime)){
						allow = true;
					}
				}else if(frequency == 3){
					if(checkWeek(lastJoinTime)){  //一周参加一次
						allow = true;
					}
				}else if(frequency == 4){      //每次都可以参加
					allow = true;
				}
				if(!allow){
					ret = ActivityConfig.ACTIVITY_HAS_JOIN;            //已参加过
				}else{
					ret = ActivityConfig.ACTIVITY_CHECK_PASS;       //通过
				}
			}
		}catch (Exception e) {
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return ret;
	}
	
	/**
	 * 检测lastTime是否在今天,在则返回false,不在则返回true   //lastTime < now
	 * @param lastTime
	 * @return
	 */
	private boolean checkDay(long lastTime){
		long diff = DateUtil.differ(new Date(), new Date(lastTime));
		if(diff == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 检测lastTime是否在本周,在则返回false,不在则返回true   //lastTime < now
	 * @param lastTime
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private boolean checkWeek(long lastTime){
		if(new Date(lastTime).getDay() > new Date().getDay() || DateUtil.differ(new Date(), new Date(lastTime)) >= 7){
			return true;
		}else {
		    return false;
		}
	}
	
	
}
