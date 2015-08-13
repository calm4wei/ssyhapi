package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import cn.suishou.bean.SkipBean;
import cn.suishou.utils.DBUtil;

public class SkipDAO {
	private static Logger logger = Logger.getLogger(TradeMXDAO.class);
	
	public static boolean addSkip(SkipBean skipbean) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into `ssyh_main`.`item_skip`(`item_id`,`uid`,`keyid`,`channel`,`version`,`tag_id`,`platform`,`type`,`app`,`timestamp`,`ip`,`from_uid`) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try{   
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, skipbean.getItemid());
			ps.setString(2, skipbean.getUid());
			ps.setString(3, skipbean.getKeyid());
			ps.setString(4, skipbean.getChannel());
			ps.setString(5, skipbean.getVersion());
			ps.setString(6, skipbean.getTag_id());
			ps.setString(7, skipbean.getPlatform());
			ps.setString(8, skipbean.getType());
			ps.setString(9, skipbean.getApp());
			ps.setLong(10, skipbean.getTimestamp());
			ps.setString(11, skipbean.getIp());
			ps.setString(12, skipbean.getFrom_uid());
			
			int i = ps.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("error stack",e);		
		} finally {
			DBUtil.close(ps,conn);
		}
		return flag;
	}
	
}
