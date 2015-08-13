package cn.suishou.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.FlashSellItem;
import cn.suishou.bean.HomePageBanner;
import cn.suishou.bean.SelfItem;
import cn.suishou.bean.Tag2item;
import cn.suishou.bean.TaobaoItem;
import cn.suishou.bean.VIPItem;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.ramdata.FavoriteCacher;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.HomePageBannerCacher;
import cn.suishou.ramdata.NotifyFlashSellItemCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.ramdata.Tag2ItemCacher;
import cn.suishou.ramdata.TaobaoItemCacher;
import cn.suishou.ramdata.VIPItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

/**
 * @category 获取首页推荐商品
 */

@WebServlet("/api/homePageRecomItem")
public class GetHomePageRecomItem extends HttpServlet {
	private static Logger logger = Logger.getLogger(GetHomePageRecomItem.class);
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		List<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		
		try{			
			String uid = ParamUtil.getParameter(request, "uid");
			String platform = ParamUtil.getParameter(request, "platform", true);
			int page = ParamUtil.getParameterInt(request, "page", 1);
			int pageSize = Value.page_size;
			
			if(page==1){  //添加现场惠商品
				HomePageBanner xianchanghui=new HomePageBanner();
				Map<String, String> map = HomePageBannerCacher.getInstance().getAll();
				
				for(Map.Entry<String, String> m : map.entrySet()){
					HomePageBanner homePageBanner = gson.fromJson(m.getValue(), HomePageBanner.class);
					if(homePageBanner.getKey().equals("xianchang")){
						xianchanghui = homePageBanner;
					}					
				}
				
				String xchTagId = xianchanghui.getTagId();
				List<Tag2item> xchItems = Tag2ItemCacher.getInstance().getItemsByTagid(xchTagId, page, Integer.MAX_VALUE);
				for(Tag2item tag2item : xchItems){
					HashMap<String, Object> xchMap = null;
					int itemChannel = tag2item.getFromChannel();
					String itemId = tag2item.getItemId();
					if(itemChannel==Value.item_channel_self){
						SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);					
						xchMap = selfItem.toMap();
						xchMap.put("itemChannel", itemChannel);	
						if(tag2item.getIsFlashSell()==1){
							FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(itemId);	
							xchMap.put("isFlashSell", 1);
							xchMap.put("flashSellShowType", 0); //0 未到时间点击显示闹钟提醒   1 未到时间仅仅不能购买
							xchMap.put("flashSellStartTime", flashSellItem.getStartTimestamp());
							xchMap.put("flashSellEndTime", flashSellItem.getEndTimestamp());						
						}else{
							xchMap.put("isFlashSell", 0);
						}
					}					
					
					if(!"".equals(uid)){
						HashSet<String> userSet = NotifyFlashSellItemCacher.getInstance().getNotifyUser(itemId);
						if(userSet.contains(uid+","+platform)){
							xchMap.put("isSetNotified", 1);
						}else{
							xchMap.put("isSetNotified", 0);
						}	
					}
				
					itemList.add(xchMap);
				}				
			}
			
			
			List<Tag2item> tag2items = Tag2ItemCacher.getInstance().getItemsByTagid("27", page, pageSize);
			int count = Tag2ItemCacher.getInstance().getTagItemCount("27");
			for(Tag2item tag2item : tag2items){
				HashMap<String, Object> map = null;
				int itemChannel = tag2item.getFromChannel();
				String itemId = tag2item.getItemId();
				if(itemChannel==Value.item_channel_taobao){
					TaobaoItem taobaoItem = TaobaoItemCacher.getInstance().getItem(itemId);
					map = taobaoItem.toMap();
					map.put("itemChannel", itemChannel);
					map.put("isFlashSell", 0);
				}else if(itemChannel==Value.item_channel_self){
					SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);					
					map = selfItem.toMap();
					map.put("itemChannel", itemChannel);
					map.put("isFlashSell", 0);
				}else if(itemChannel==Value.item_channel_vip){
					VIPItem vipItem = VIPItemCacher.getInstance().getItem(itemId);
					map = vipItem.toMap();
					map.put("itemChannel", itemChannel);
					map.put("isFlashSell", 1);
					map.put("flashSellShowType", 1);
					map.put("flashSellStartTime", vipItem.getStart_time());
					map.put("flashSellEndTime", vipItem.getEnd_time());	
				}
				
				
				if(uid != null && !"".equals(uid)){
					HashSet favSet = FavoriteCacher.getInstance().getFavorite(uid);
					if(favSet != null && favSet.contains(itemId+","+itemChannel)){
						map.put("isFavorite", 1);
					}else{
						map.put("isFavorite", 0);
					}
				}
				
				itemList.add(map);
			}	
		
			int totalPage = count / pageSize ;
			if((count % pageSize)>0){
				totalPage ++;
			}
			
			Date currentTime = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			retMap.put("currentTime", sdf.format(currentTime));
			retMap.put("itemList", itemList);
			retMap.put("page", page);
			retMap.put("totalPage", totalPage);
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
