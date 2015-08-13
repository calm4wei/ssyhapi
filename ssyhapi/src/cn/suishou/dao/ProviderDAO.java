package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import cn.suishou.bean.Provider;
import cn.suishou.utils.DBUtil;

public class ProviderDAO {
	protected static Logger logger = Logger.getLogger(ProviderDAO.class);
	private static ProviderDAO instance = null;

	private ProviderDAO(){
	}
	
	public static ProviderDAO getInstance(){
		if(instance == null) instance = new ProviderDAO();
		return instance;
	}
	
	public Provider getProvider(String providerId){	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Provider provider = new Provider();
		try{
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_merchant_center`.`user` where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, providerId);
			rs = ps.executeQuery();
			if(rs.next()){				
				provider.setId(rs.getString("id"));
				provider.setPhoneNum(rs.getString("phone_number"));
				provider.setEmail(rs.getString("email"));
			}			
		}catch(Exception e){		
			logger.error("error stack",e);
		}finally{
			DBUtil.close(rs,ps,conn);
		}
		return provider;
	}
	
	

}
