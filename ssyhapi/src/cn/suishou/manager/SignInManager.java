package cn.suishou.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.suishou.activity.ActivityClient;
import cn.suishou.activity.ActivityConfig;
import cn.suishou.activity.ActivityRequest;
import cn.suishou.bean.ItemAd;
import cn.suishou.bean.ItemAdCallBackResponse;
import cn.suishou.bean.RunningDay;
import cn.suishou.bean.SignIn;
import cn.suishou.bean.UserAccount;
import cn.suishou.dao.ItemAdDAO;
import cn.suishou.dao.UserAccountDAO;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.DateUtil;
import cn.suishou.utils.FenHongUtil;
import cn.suishou.utils.StringUtil;

public class SignInManager {
	private static Logger logger = Logger.getLogger(SignInManager.class);
	
	public static final String key = "signin";	
	private static final String SIGN_ACTIVITY_ID = "1";	
	private static final int STATUS_NOSIGN = 0;   //未签到	
	private static final int STATUS_SIGN = 1;      //已签到，未点击广告	
	private static final int STATUS_CLICKAD = 2;    //已点击广告	
	private static SimpleDateFormat ft1 = new SimpleDateFormat("yyyy/MM/dd");
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
	
	private static SignInManager instance = null;	
	private SignInManager(){}	
	public static SignInManager getInstance(){
		if(instance == null){
			instance = new SignInManager();
		}
		return instance;
	}
	
	/**
	 * 获取今天签到情况
	 * @param uid
	 * @return
	 */
	public SignIn getSignIn(String uid){
		SignIn signIn = new SignIn();
		signIn.setUid(uid);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			RunningDay runningDay = getRunningDays(uid, conn);
			
			if(runningDay.getSignInStatus()==1){      //今天已经签到过
				signIn.setRunningDays(runningDay.getRunningDays());
				signIn.setStatus(runningDay.getAdClickStatus());
				signIn.setJfNum(runningDay.getJfNum());

			}else if(runningDay.getSignInStatus()==2){ //今天未签到
				signIn.setStatus(STATUS_NOSIGN);
				signIn.setRunningDays(runningDay.getRunningDays());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
		    DBUtil.close(null, conn);
		}
		return signIn;
	}

	
	/**
	 * 签到
	 * @param uid
	 * @return
	 */
	public SignIn sign(String uid){
		SignIn signIn = new SignIn();
		signIn.setUid(uid);
		long now = System.currentTimeMillis();
		signIn.setCreateTime(StringUtil.long2datetime(System.currentTimeMillis()));
		signIn.setDate(ft.format(new Date(now)));
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int rDays = 1;
			RunningDay runningDay = getRunningDays(uid, conn);
			if(runningDay.getSignInStatus()==1){                   //今天已签到过
				signIn.setRunningDays(runningDay.getRunningDays());
				signIn.setStatus(runningDay.getAdClickStatus());
				signIn.setJfNum(runningDay.getJfNum());
				ItemAd itemAd = null;
				if(runningDay.getAdClickStatus()==1){ //签到未点击广告
					itemAd = ItemAdDAO.getInstance().getSignAd(true);
				}else if(runningDay.getAdClickStatus()==2){ //签到已点击广告
					itemAd = ItemAdDAO.getInstance().getSignAd(false);
				}
				
				if(itemAd != null){
			//		itemAd.setNeedCallBack(true);
					signIn.setItemAd(itemAd);
				}
				add(signIn, conn);
			}else if(runningDay.getSignInStatus()==2){  //今天未签到
				rDays = runningDay.getRunningDays()+1;				
				int jfNum = getJFNum(uid, rDays);
				signIn.setRunningDays(rDays);
				signIn.setStatus(STATUS_SIGN);
				signIn.setJfNum(jfNum);
				
				ItemAd itemAd = ItemAdDAO.getInstance().getSignAd(true);
				if(itemAd != null){
			//		itemAd.setNeedCallBack(true);
					signIn.setItemAd(itemAd);
				}				
				add(signIn, conn);							
			}
		}catch (Exception e) {
			logger.error("error stack",e);
		}finally{
		   DBUtil.close(null, conn);
		}
		return signIn;
	}
	
	private boolean add(SignIn si, Connection conn){
		boolean flag = false;
		try{
			String sql = "insert into `ssyh_main`.`signin` (`uid`,`date`,`running_days`,`jf_num`,`ad_id`,`status`,`create_time`,`update_time`)values(?,?,?,?,?,?,?,?) ON DUPLICATE KEY update `ad_id`=?,  `update_time` = ?  ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, si.getUid());
			ps.setString(2, si.getDate());
			ps.setInt(3, si.getRunningDays());
			ps.setInt(4, si.getJfNum());
			ps.setString(5, si.getItemAd()==null?null:si.getItemAd().getAdId());
			ps.setInt(6, si.getStatus());
			ps.setTimestamp(7, StringUtil.datetime2timestamp(si.getCreateTime()));
			ps.setTimestamp(8, StringUtil.datetime2timestamp(si.getCreateTime()));
			ps.setString(9, si.getItemAd()==null?null:si.getItemAd().getAdId());
			ps.setTimestamp(10, StringUtil.datetime2timestamp(si.getCreateTime()));
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}
		return flag;
	}
	
	public RunningDay getRunningDays(String uid, Connection conn){		
		RunningDay runningDay = new RunningDay();
		try {
			String sql = "select `ad_id`,`date`,`running_days`,`jf_num`,`status` from `ssyh_main`.`signin` where `uid` = ? order by `create_time` desc limit 0,1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){			
				Date lastDate = null;
				String date = rs.getString("date");
				if(date != null && !"".equals(date)){
					lastDate = ft.parse(date);
					long diffdays = DateUtil.differ(new Date(), lastDate);
					if(diffdays == 0){   //今天签到过
						runningDay.setSignInStatus(1);
						runningDay.setRunningDays(rs.getInt("running_days"));
						runningDay.setAdClickStatus( rs.getInt("status"));
						runningDay.setAdId(rs.getInt("ad_id"));
						runningDay.setJfNum(rs.getInt("jf_num"));			
					}else if(diffdays == 1){   //昨天签到过
						runningDay.setSignInStatus(2);
						runningDay.setRunningDays(rs.getInt("running_days"));				
					}else{
						runningDay.setSignInStatus(2);
						runningDay.setRunningDays(0);						
					}
				}
			}else{
				runningDay.setSignInStatus(2);
				runningDay.setRunningDays(0);		
			}
		} catch (Exception e) {		
			logger.error("error stack",e);
		}		
		return runningDay;
	}
	
	public int getJFNum(String uid, int days){
		int ret = 0;
		UserAccount ua = UserAccountDAO.getInstance().getUserAccount(uid);
		int level = ua.getLevel();
		if(level == FenHongUtil.LEVEL_NO_HEART){
			if(days <=3){
				ret = 1;
			}else{
				ret = getRandom(1, 3);
			}
		}else if(level == FenHongUtil.LEVEL_HEART){
			if(days <=3){
				ret = 2;
			}else{
				ret = getRandom(2, 4);
			}
		}else if(level == FenHongUtil.LEVEL_DIAMOND){
			if(days <=3){
				ret = 3;
			}else{
//				ret = getRandom(3, 8);
				Map<Integer,Integer> scale2 = new LinkedHashMap<Integer,Integer>();
				scale2.put(3, 20);
				scale2.put(4, 40);
				scale2.put(5, 25);
				scale2.put(6, 6);
				scale2.put(7, 5);
				scale2.put(8, 4);
				ret = getRandom(scale2);
			}
		}else if(level == FenHongUtil.LEVEL_CROWN){
			if(days <=3){
				ret = 5;
			}else{
//				ret = getRandom(5, 15);
				Map<Integer,Integer> scale3 = new LinkedHashMap<Integer,Integer>();
				scale3.put(5, 9);
				scale3.put(6, 12);
				scale3.put(7, 15);
				scale3.put(8, 15);
				scale3.put(9, 10);
				scale3.put(10, 9);
				scale3.put(11, 8);
				scale3.put(12, 7);
				scale3.put(13, 6);
				scale3.put(14, 5);
				scale3.put(15, 4);
				ret = getRandom(scale3);
			}
		}else if(level == FenHongUtil.LEVEL_GOLDENCROWN){
			if(days <=3){
				ret = 8;
			}else{
//				ret = getRandom(8, 20);
				Map<Integer,Integer> scale4 = new LinkedHashMap<Integer,Integer>();
				scale4.put(8, 10);
				scale4.put(9, 15);
				scale4.put(10, 20);
				scale4.put(11, 10);
				scale4.put(12, 9);
				scale4.put(13, 8);
				scale4.put(14, 5);
				scale4.put(15, 4);
				scale4.put(16, 3);
				scale4.put(17, 3);
				scale4.put(18, 1);
				scale4.put(19, 1);
				scale4.put(20, 1);
				ret = getRandom(scale4);
			}
		}else if(level == FenHongUtil.LEVEL_SUPER){
			if(days <=3){
				ret = 10;
			}else{
				ret = getRandom(10, 30);
			}
		}
		return ret;
	}
	
	public String getNextJFNum(String uid, int days){
		String ret = "";
		UserAccount ua = UserAccountDAO.getInstance().getUserAccount(uid);
		int level = ua.getLevel();
		if(level == FenHongUtil.LEVEL_NO_HEART){
			if(days <=3){
				ret = "1";
			}else{
				ret = "1-3";
			}
		}else if(level == FenHongUtil.LEVEL_HEART){
			if(days <=3){
				ret = "2";
			}else{
				ret = "2-4";
			}
		}else if(level == FenHongUtil.LEVEL_DIAMOND){
			if(days <=3){
				ret = "3";
			}else{
				ret = "3-8";
			}
		}else if(level == FenHongUtil.LEVEL_CROWN){
			if(days <=3){
				ret = "5";
			}else{
				ret = "5-15";
			}
		}else if(level == FenHongUtil.LEVEL_GOLDENCROWN){
			if(days <=3){
				ret = "8";
			}else{
				ret = "8-20";
			}
		}else if(level == FenHongUtil.LEVEL_SUPER){
			if(days <=3){
				ret = "10";
			}else{
				ret = "10-30";
			}
		}
		return ret;
	}
	
	public boolean updateClicked(String uid, String date){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`signin` set `status`=? where `uid`=? and `data`=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_CLICKAD);
			ps.setString(2, uid);
			ps.setString(3, date);			
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}finally{
			DBUtil.close(ps, conn);
		}
		return flag;
	}
	
	public ItemAdCallBackResponse clickSignAd(String uid, String adId){
		ItemAdCallBackResponse rsp = new ItemAdCallBackResponse();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_main`.`signin` set `status`=?,`update_time`=? where `uid`=? and `ad_id`=? and `date`=?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, STATUS_CLICKAD);
			ps.setTimestamp(2, StringUtil.long2timestamp(System.currentTimeMillis()));
			ps.setString(3, uid);
			ps.setString(4, adId);	
			String date = ft.format(new Date());
			ps.setString(5, date);
			if(ps.executeUpdate() > 0){
				sql = "select `jf_num`,`running_days` from `ssyh_main`.`signin` where `uid`=? and `date`=?;";
				ps.close();
				ps = conn.prepareStatement(sql);
				ps.setString(1, uid);
				ps.setString(2, date);
				rs = ps.executeQuery();
				if(rs.next()){				
					rsp.setSuccess(true);
					int jfNum = rs.getInt("jf_num");
					ActivityRequest request = new ActivityRequest(uid, key, getUniqueId(uid), jfNum, rs.getInt("running_days"));
					int re = ActivityClient.execut(request);
					if(ActivityConfig.ACTIVITY_JOIN_SUCCESS == re){
						rsp.setJfNum(jfNum);
					}
				}
			}
			
		}catch (Exception e) {
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return rsp;
	}

	private String getUniqueId(String uid){
		String today = ft.format(new Date());
		return today + uid;
	}
	
	/**
	 * 是否真正签到发放了集分宝
	 * @return
	 */
	public boolean isRealSign(String uid, String date, Connection conn){
		boolean flag = false;
		try{
			String sql = "SELECT id FROM `youhui_cn_fanli`.`tyh_activity_join` where `uid` = ? and id like ? and activity_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, date + "%");
			ps.setString(3, SIGN_ACTIVITY_ID);
			ResultSet rs = ps.executeQuery();
			System.out.println(",,,,,,,,,,,issin...." + ps.toString());
			if(rs.next()){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	private int getRandom(int start, int end){    
		int ran = 0;
		int sub = end - start + 1;
		if(sub < 0){
			return ran;
		}
		ran = new Random().nextInt(sub) + start;
		return ran;
	}
	
	private int getRandom(Map<Integer,Integer> scale){    
		int sum = 0;
		for(Map.Entry<Integer, Integer> m : scale.entrySet()){
			sum += m.getValue();
		}
		int ran = new Random().nextInt(sum);
		for(Map.Entry<Integer, Integer> m : scale.entrySet()){
			if(ran - m.getValue() < 0)
				return m.getKey();
			else ran = ran - m.getValue();
		}
		return 0;
	}
	
	/**
	 * 今天签到次数是否超过
	 * @param uid
	 * @return
	 */
	public boolean isLimitToday(String uid){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String today = ft1.format(new Date());
			String sql = "select count(id) as acount from `ssyh_main`.`activity_join` where `uid` = ? and `activity_id` = ? and `date`=?;";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, uid);
			s.setString(2, "1");
			s.setString(3, today);
			rs = s.executeQuery();
			if(rs.next()){
				int count = rs.getInt("acount");
				if(count > 1){
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.error("error stack",e);
		}finally{
		    DBUtil.close(rs, ps, conn);
		}
		return flag;
	}
	

}
