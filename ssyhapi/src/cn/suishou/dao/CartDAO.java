package cn.suishou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.suishou.bean.CartItem;
import cn.suishou.utils.DBUtil;
import cn.suishou.utils.StringUtil;

public class CartDAO {
	protected static Logger logger = Logger.getLogger(CartDAO.class);
	private static CartDAO instance = null;

	private CartDAO(){
	}
	
	public static CartDAO getInstance(){
		if(instance == null) instance = new CartDAO();
		return instance;
	}
	
	public boolean insertCartItem(CartItem cartItem){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			long now = System.currentTimeMillis();
			conn = DBUtil.getConnection();
			
			String sql = "insert `ssyh_main`.`cart`(`uid`,`itemId`,`title`,`icon`,`price`,`num`,`sku`) into values(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE `createTime`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cartItem.getUid());
			ps.setString(2, cartItem.getItemId());
			ps.setString(3, cartItem.getTitle());
			ps.setString(4, cartItem.getIcon());
			ps.setDouble(5, cartItem.getPrice());
			ps.setInt(6, cartItem.getNum());
			ps.setString(7, cartItem.getSku());
			ps.setTimestamp(8, StringUtil.long2timestamp(now));
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
	
	public boolean deleteCartItem(String uid, String[] itemIdSet){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{		
			conn = DBUtil.getConnection();			
			String sql = "delete from `ssyh_main`.`cart` where `uid`='"+uid+"' and `itemId` in (";
			for(String itemId : itemIdSet){
				sql += "'"+itemId+"',";
			}
			sql = sql.substring(0, sql.length()-1) + ")";
			ps = conn.prepareStatement(sql);
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
	
	public boolean updateCartItem(CartItem cartItem){
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try{	
			conn = DBUtil.getConnection();			
			String sql = "update `ssyh_main`.`cart` set `num`=? where `uid`=? and `itemId`=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, cartItem.getUid());
			ps.setString(2, cartItem.getItemId());
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
	
	public ArrayList<CartItem> getCart(String uid, int page, int pageSize){
		ArrayList<CartItem> cartItemList = new ArrayList<CartItem>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from `ssyh_main`.`cart` where `uid`=? order by `createTime` desc limit ?,? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);		
			ps.setInt(2, (page - 1)*pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				CartItem cartItem = new CartItem();
				cartItem.setId(rs.getString("id"));			
				cartItem.setUid(rs.getString("uid"));
				cartItem.setItemId(rs.getString("itemId"));
				cartItem.setTitle(rs.getString("title"));
				cartItem.setPrice(rs.getDouble("price"));
				cartItem.setNum(rs.getInt("num"));
				cartItem.setSku(rs.getString("sku"));	
				cartItemList.add(cartItem);
			}
		} catch (SQLException e){
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs,ps,conn);
		}
		return cartItemList;
	}
	
	public int getCartItemCount(String uid){	
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from `ssyh_main`.`cart` where `uid`=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, uid);			
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e){
			logger.error("error stack",e);
		}finally{		
			DBUtil.close(rs,ps,conn);
		}
		return count;
	}
	

}
