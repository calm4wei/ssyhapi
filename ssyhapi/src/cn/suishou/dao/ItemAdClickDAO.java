package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.suishou.bean.ItemAdClick;
import cn.suishou.utils.StringUtil;

public class ItemAdClickDAO {
	
	public static final int STATUS_CLICK = 1;    //已点击	
	public static final int STATUS_LOAD = 2;     //已加载	
	public static final int STATUS_FF = 3;     //已发放
	
	private static DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	private static ItemAdClickDAO instance = null;
	
	private ItemAdClickDAO(){}
	
	public static ItemAdClickDAO getInstance(){
		if(instance == null){
			instance = new ItemAdClickDAO();
		}
		return instance;
	}
	
	
	public boolean add(ItemAdClick itemAdclick, Connection conn){
		boolean flag = false;
		try{
			String sql = "insert into `ssyh_main`.`item_ad_click` (`uid`,`ad_id`,`jf_num`,`click_time`,`date`,`status`,`from_area`,`ac_unique_id`,`ac_key`,`create_time`)values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, itemAdclick.getUid());
			ps.setString(2, itemAdclick.getAdId());
			ps.setInt(3, itemAdclick.getJfNum());
			ps.setTimestamp(4, StringUtil.datetime2timestamp(itemAdclick.getClickTime()));
			ps.setString(5, itemAdclick.getDate());
			ps.setInt(6, STATUS_CLICK);
			ps.setInt(7, itemAdclick.getFromArea());
			ps.setString(8, itemAdclick.getAcUniqueId());
			ps.setString(9, itemAdclick.getAcKey());
			ps.setTimestamp(10, StringUtil.long2timestamp(System.currentTimeMillis()));			
			int i = ps.executeUpdate();
			if(i > 0){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	
	/**
	 * 获取领取状态
	 * @param adclick
	 * @return
	 */
	public int getStatus(String uid, String adId, Connection conn){
		int status = 0;
		try{
			String sql = "select * from `ssyh_main`.`item_ad_click` where `ad_id`=? and `uid` =? and `date`=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adId);
			ps.setString(2, uid);
			ps.setString(3, df.format(new Date()));
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				status = rs.getInt("status");		
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	
}
