package cn.suishou.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.SelfItem;
import cn.suishou.bean.TaobaoItem;
import cn.suishou.bean.VIPItem;
import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.ramdata.TaobaoItemCacher;
import cn.suishou.ramdata.VIPItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.youhui.log4ssy.api.Log4SSY;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Store;
import cn.youhui.log4ssy.utils.Enums.Type;

/**
 * 点击商品写log
 */

@WebServlet("/api/itemClick")
public class ItemClick extends HttpServlet {
	private static Logger logger = Logger.getLogger(GetTagItems.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			int itemChannel = Integer.valueOf(ParamUtil.getParameter(request, "itemChannel", true)); 
			String itemId = ParamUtil.getParameter(request, "itemId", true);
			String uid = ParamUtil.getParameter(request, "uid");			

			if(itemChannel == Value.item_channel_taobao){
				TaobaoItem taobaoItem = TaobaoItemCacher.getInstance().getItem(itemId);
				Log4SSY.Log(Config.suggestSystemURL, "SSYH", uid, Event.VIEW, Type.PRODUCT, Store.TAOBAO, itemId, "view", gson.toJson(taobaoItem.toMap()));
			}else if(itemChannel == Value.item_channel_vip){
				VIPItem vipItem = VIPItemCacher.getInstance().getItem(itemId);
				Log4SSY.Log(Config.suggestSystemURL+"/addlog", "SSYH", uid, Event.VIEW, Type.PRODUCT, Store.VIP, itemId, "view", gson.toJson(vipItem.toMap()));
			}else if(itemChannel == Value.item_channel_self){
				SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);
				Log4SSY.Log(Config.suggestSystemURL, "SSYH", uid, Event.VIEW, Type.PRODUCT, Store.SSYH, itemId, "view", gson.toJson(selfItem.toMap()));
			}

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
