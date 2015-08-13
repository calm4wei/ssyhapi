package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.suishou.bean.ItemAd;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.StringUtil;

public class ItemAdDAO {
	protected static Logger logger = Logger.getLogger(ItemAdDAO.class);
	
	public static final int TYPE_SIGN = 1;
	public static final int TYPE_REGISTER = 2;    

	
	private static ItemAdDAO instance = null;
	
	private ItemAdDAO(){}
	
	public static ItemAdDAO getInstance(){
		if(instance == null){
			instance = new ItemAdDAO();
		}
		return instance;
	}
	
	/**
	 * 获取签到广告
	 * @param uid
	 * @param adId
	 * @return
	 */
	public ItemAd getSignAd(boolean needCallback){
		ItemAd itemAd = new ItemAd();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_admin`.`sign_ads` where `type` =? and `start_time`< ? and `end_time`>? and (`has_click_num`<`click_num` or `click_num`=-1) order by (`click_num`-`has_click_num`) desc, rand() limit 0,1;";
			ps = conn.prepareStatement(sql);
		
			ps.setInt(1, TYPE_SIGN);
			long now = System.currentTimeMillis();
			ps.setTimestamp(2, StringUtil.long2timestamp(now));
			ps.setTimestamp(3, StringUtil.long2timestamp(now));
			rs = ps.executeQuery();
			if(rs.next()){	
				itemAd.setAdId(rs.getString("id"));
				itemAd.setClickUrl(rs.getString("click_url"));
				itemAd.setItemChannel(rs.getInt("from_channel"));
				itemAd.setImg(rs.getString("img"));	
				if(needCallback){
					itemAd.setClickUrlNeedCallback();
				}				
			}else{
				itemAd = null;
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return itemAd;
	}	
	
	public ItemAd getAd(int adId){
		ItemAd itemAd = new ItemAd();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_admin`.`sign_ads` where `id` =? ";
			ps = conn.prepareStatement(sql);
		
			ps.setInt(1, adId);
			long now = System.currentTimeMillis();
			ps.setTimestamp(2, StringUtil.long2timestamp(now));
			ps.setTimestamp(3, StringUtil.long2timestamp(now));
			rs = ps.executeQuery();
			if(rs.next()){
				itemAd.setAdId(rs.getString("id"));
				itemAd.setClickUrl(rs.getString("click_url"));
				itemAd.setItemChannel(rs.getInt("from_channel"));
				itemAd.setImg(rs.getString("img"));	
			}else{
				itemAd = null;
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return itemAd;
	}
	
	public boolean isSignAd(String adId){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select `type` from `ssyh_admin`.`sign_ads` where `id`=?;";
			ps = conn.prepareStatement(sql);
			ps.setString(1, adId);
			rs = ps.executeQuery();
			if(rs.next()){
				int type = rs.getInt("type");
				if(TYPE_SIGN == type){
					flag = true;
				}
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return flag;
	}
	
	public boolean updateItemAdClickTime(String adId){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_admin`.`sign_ads` set `click_num`=`click_num`+1 where `id`=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, adId);		
			ps.executeUpdate();			
			flag = true;			
		} catch (SQLException e) {
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(ps,conn);
		}
			return flag;
	}
	
	public ItemAd getRegisterAd(){
		ItemAd itemAd = new ItemAd();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_admin`.`sign_ads` where `type` =? and `start_time`< ? and `end_time`>? order by rand() limit 0,1;";
			ps = conn.prepareStatement(sql);
		
			ps.setInt(1, TYPE_REGISTER);
			long now = System.currentTimeMillis();
			ps.setTimestamp(2, StringUtil.long2timestamp(now));
			ps.setTimestamp(3, StringUtil.long2timestamp(now));
			rs = ps.executeQuery();
			if(rs.next()){	
				itemAd.setAdId(rs.getString("id"));
				itemAd.setClickUrl(rs.getString("click_url"));
				itemAd.setItemChannel(rs.getInt("from_channel"));
				itemAd.setImg(rs.getString("img"));								
			}else{
				itemAd = null;
			}
		}catch(Exception e){
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs, ps, conn);
		}
		return itemAd;
	}
	
	
}
