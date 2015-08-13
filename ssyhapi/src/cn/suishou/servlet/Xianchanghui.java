package cn.suishou.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
import cn.suishou.bean.Tag2item;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.manager.FlashSellItemManager;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.NotifyFlashSellItemCacher;
import cn.suishou.ramdata.NotifyFlashSellNumCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.ramdata.Tag2ItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

@WebServlet("/api/XianChangHui")
public class Xianchanghui extends HttpServlet {
	private static Logger logger = Logger.getLogger(Xianchanghui.class);	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		List<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		
		try{
			String tagId = ParamUtil.getParameter(request, "tagId", true);		
			String platform = ParamUtil.getParameter(request, "platform", true);
			String uid = ParamUtil.getParameter(request, "uid", true);
			int page = ParamUtil.getParameterInt(request, "page", 1);
			int pageSize = Value.page_size;
			int totalPage = Tag2ItemCacher.getInstance().getTotalPageNum(tagId, pageSize);
			List<Tag2item> tag2items = Tag2ItemCacher.getInstance().getItemsByTagid(tagId, page, pageSize);
			for(Tag2item tag2item : tag2items){
				HashMap<String, Object> map = null;
				int itemChannel = tag2item.getFromChannel();
				String itemId = tag2item.getItemId();
			
				SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);					
				map = selfItem.toMap();
				
				FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(itemId);	
				int notifyNum = NotifyFlashSellNumCacher.getInstance().getNotifyFlashSellNum(itemId); //预约提醒人数
				map.put("itemChannel", itemChannel);	
				map.put("isFlashSell", 1);
				map.put("flashSellShowType", 0); //0 未到时间点击显示闹钟提醒   1 未到时间仅仅不能购买
				map.put("flashSellStartTime", flashSellItem.getStartTimestamp());
				map.put("flashSellEndTime", flashSellItem.getEndTimestamp());
				map.put("notifyNum", notifyNum);				
		
				if(FlashSellItemManager.isInFlashSellTime(flashSellItem)){
					HashSet<String> userSet = NotifyFlashSellItemCacher.getInstance().getNotifyUser(itemId);
					if(userSet.contains(uid+","+platform)){
						map.put("isSetNotified", 1);
					}else{
						map.put("isSetNotified", 0);
					}
				}
				
				itemList.add(map);
			}
				
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			retMap.put("currentTime", StringUtil.long2datetime(System.currentTimeMillis()));
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
