package cn.suishou.servlet;

import java.io.IOException;
import java.util.HashMap;
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
import cn.suishou.bean.Tag2item;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.manager.FlashSellItemManager;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.HomePageBannerCacher;
import cn.suishou.ramdata.Tag2ItemCacher;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

@WebServlet("/api/currentFlashSellItem")
public class CurrentFlashSellItem extends HttpServlet {
	private static Logger logger = Logger.getLogger(CurrentFlashSellItem.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();			
		try{				
			HomePageBanner xianchanghui=new HomePageBanner();
			Map<String, String> map = HomePageBannerCacher.getInstance().getAll();
			
			for(Map.Entry<String, String> m : map.entrySet()){
				HomePageBanner homePageBanner = gson.fromJson(m.getValue(), HomePageBanner.class);
				if(homePageBanner.getKey().equals(Value.home_page_banner_key_xianchanghui)){
					xianchanghui = homePageBanner;
				}	
				
				
				String xchTagId = xianchanghui.getTagId();
				List<Tag2item> xchItems = Tag2ItemCacher.getInstance().getItemsByTagid(xchTagId, 1, Integer.MAX_VALUE);
				
				for(Tag2item tag2item : xchItems){
					String itemId = tag2item.getItemId();
					
					FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(itemId);	
					if (FlashSellItemManager.isInFlashSellTime(flashSellItem)){		
						retMap.put("clickUrl", "suishou://app.suishou.cn/SelfItem?itemId="+itemId);
						retMap.put("flashSellStartTime", flashSellItem.getStartTimestamp());
						retMap.put("flashSellEndTime", flashSellItem.getEndTimestamp());						
					}
				}
			}
			retMap.put("currentTime", StringUtil.long2datetime(System.currentTimeMillis()));
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
