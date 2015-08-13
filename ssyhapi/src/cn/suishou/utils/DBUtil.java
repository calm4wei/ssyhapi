package cn.suishou.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cn.suishou.common.Config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {	
	private static Logger logger = Logger.getLogger(DBUtil.class);
	private static DataSource ds_master = new ComboPooledDataSource(Config.db_master);
	private static DataSource ds_slave  = new ComboPooledDataSource(Config.db_slave);

	public static Connection getConnection(){
		try {	
			return ds_master.getConnection();
		} catch (SQLException e) {			
			logger.error("error stack",e);
			return null;
		}
	}
	
	public static Connection getConnectionSlave(){
		try {
			return ds_slave.getConnection();
		} catch (SQLException e) {
			logger.error("error stack",e);
			return null;
		}
	}

	
	public static void close(PreparedStatement ps, Connection conn){
        try {
        	if(ps != null) ps.close();
        	if(conn != null) conn.close();
		} catch (SQLException e) {		
			logger.error("error stack",e);
		}
	}
	
	public static void close(ResultSet rs , PreparedStatement ps, Connection conn){
        try {
        	if(rs != null) rs.close();
        	if(ps != null) ps.close();
        	if(conn != null) conn.close();
		} catch (SQLException e) {		
			logger.error("error stack",e);
		}
	}
	
	public static void rollback(Connection conn){
        try {
        	conn.rollback();
		} catch (SQLException e) {		
			logger.error("error stack",e);
		}
	}
}
