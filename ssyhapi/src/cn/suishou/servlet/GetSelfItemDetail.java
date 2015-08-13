package cn.suishou.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.FlashSellItem;
import cn.suishou.bean.SelfItem;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.ramdata.FavoriteCacher;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.ItemPurchaseLimitedCacher;
import cn.suishou.ramdata.ItemSoldCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

/**
 * @category 获取标签下商品
 */

@WebServlet("/api/getselfitemdetail")
public class GetSelfItemDetail extends HttpServlet {
	private static Logger logger = Logger.getLogger(GetTagItems.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		try{
			String itemId = ParamUtil.getParameter(request, "itemId", true);	
			String uid = ParamUtil.getParameter(request, "uid");
			
			SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);			
			selfItem.setSoldNum(ItemSoldCacher.getInstance().getItemSoldNum(itemId));
			HashMap<String, Object> map = selfItem.toDetailMap();
			
			FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(itemId);
	
			if(flashSellItem != null){ //是闪购商品
				map.put("isFlashSell", 1);
				map.put("flashSellStartTime", flashSellItem.getStartTimestamp());
				map.put("flashSellEndTime", flashSellItem.getEndTimestamp());
				map.put("currentTime", StringUtil.long2datetime(System.currentTimeMillis()));
			}else{
				map.put("isFlashSell", 0); 
			}	
			
			if(uid != null && !"".equals(uid)){
				HashSet<String> favSet = FavoriteCacher.getInstance().getFavorite(uid);
				if(favSet != null && favSet.contains(itemId+",1")){
					map.put("isFavorite", 1);
				}else{
					map.put("isFavorite", 0);
				}
			}
			
			if(uid != null && !"".equals(uid)){
				int limitedNum = ItemPurchaseLimitedCacher.getInstance().getItemPurchaseLimitedNum(itemId);
				if(limitedNum >0){
					int purchaseNum = ItemPurchaseLimitedCacher.getInstance().getUserPurchaseNum(uid, itemId);
					map.put("canBuyNum", limitedNum-purchaseNum);
				}else{
					map.put("canBuyNum", -1); //不限购买数量
				}
			}
			
			
			retMap.put("item", map);
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			response.getWriter().print(gson.toJson(retMap));		
		}catch(Exception e){
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));
			response.getWriter().print(gson.toJson(retMap));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	

	
	
}
