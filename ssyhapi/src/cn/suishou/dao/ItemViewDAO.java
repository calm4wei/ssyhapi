package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.suishou.bean.ItemView;
import cn.suishou.utils.DBUtil;

public class ItemViewDAO {
	
	private static ItemViewDAO instance = null;
	
	public static ItemViewDAO getInstance(){
		if(instance == null) instance = new ItemViewDAO();
		return instance;
	}
	
	public void add(ItemView iv){
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = DBUtil.getConnection();
			String sql = "insert into youhui_view.item_view (uid,item_id,itemChannel,from_channel,from_value,timestamp)values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, iv.getUid());
			ps.setString(2, iv.getItemId());
			ps.setInt(3, iv.getItemChannel());
			ps.setString(4, iv.getFromChannel());
			ps.setString(5, iv.getFromValue());
			ps.setLong(6, System.currentTimeMillis());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(ps,conn);
		}
	}
	
}
