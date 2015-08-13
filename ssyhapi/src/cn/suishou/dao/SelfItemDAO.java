package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import cn.suishou.bean.SelfItem;
import cn.suishou.utils.DBUtil;

public class SelfItemDAO {
	protected static Logger logger = Logger.getLogger(SelfItemDAO.class);
	private static SelfItemDAO instance = null;

	private SelfItemDAO(){
	}
	
	public static SelfItemDAO getInstance(){
		if(instance == null) instance = new SelfItemDAO();
		return instance;
	}

	
	public boolean updateStock(SelfItem selfItem){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "update `ssyh_merchant`.`item_info` set stock=?, details=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, selfItem.getStock());
			ps.setString(2, selfItem.getDetails().toString());
			int i = ps.executeUpdate();
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
	
	


}
