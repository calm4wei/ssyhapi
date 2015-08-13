package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.suishou.bean.AdConvert;
import cn.suishou.utils.DBUtil;

public class AdUrlConvertDAO {
	private static Logger logger = Logger.getLogger(TradeMXDAO.class);
	
	public static String insert( String adid,String id,String keyname,String url_address,String url_name,String url_description ,String uid){
		
		if(id.equals("")) {
			id=null;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		long time = System.currentTimeMillis();
	
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into `ssyh_main`.`page_manager`(`from_id`,`id`,`keyname`,`url_address`,`page_title`,`page_description`,`create_time`,`uid`) values(?,?,?,?,?,?,?,?) ON DUPLICATE KEY update  `keyname` = ? ,`url_address` =?,`page_title` = ? ,`page_describtion` =?,`create_time`=?;");
			int rows = -1;
			int i = 0;
			if(keyname == null || keyname.equals("")) {
				keyname = randKey();
			}
			while(rows < 1&& i<100) {
				ps.setString(1, adid);
				ps.setString(2,id );		
				ps.setString(3,keyname );		
				ps.setString(4,url_address );		
				ps.setString(5,url_name );		
				ps.setString(6,url_description );	
				ps.setLong(7, time);
				ps.setString(8, uid);
				ps.setString(9,keyname );		
				ps.setString(10,url_address );		
				ps.setString(11,url_name );		
				ps.setString(12,url_description );	
				ps.setLong(13, time);
				rows = ps.executeUpdate();
				if(rows<1) {
					keyname = randKey();
				}
				i++;
			}	
		} catch (SQLException e) {
			logger.error("error stack",e);
			
		} finally {
			DBUtil.close(ps,conn);
		}
		
		return keyname;
	}
	
	public static String key = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static String randKey() {
		StringBuffer sb = new StringBuffer("X");
		Random rm = new Random();
		int i = 5;
		while(i > 0) {
			i--;
			int index = (int)(rm.nextFloat() * key.length());
			index = Math.min(index, key.length()-1);
			index = Math.max(0, index);
			sb.append(key.charAt(index));
		}
		return sb.toString();
	}
	
	public static AdConvert getAdBean(String urlkey) {
		AdConvert bean = null;		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select `url_address`,`id` from `ssyh_main`.`page_manager` where `keynam`= ?;");
			ps.setString(1,urlkey);
			rs = ps.executeQuery();
			if(rs.next()) {
				bean = new AdConvert();
				bean.setId(rs.getString("id"));
				bean.setUrl(rs.getString("url_address"));
			}
		} catch (Exception e) {
			logger.error("error stack",e);
		} finally {
			DBUtil.close(rs,ps,conn);
		}
		return bean;
	}
	
	
	
}
