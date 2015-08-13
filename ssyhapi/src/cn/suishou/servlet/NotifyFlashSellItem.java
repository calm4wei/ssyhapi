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

import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.ramdata.NotifyFlashSellItemCacher;
import cn.suishou.ramdata.NotifyFlashSellNumCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/notifyFlashSellItem")
public class NotifyFlashSellItem extends HttpServlet {
	private static Logger logger = Logger.getLogger(FavoriteItem.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			String type = ParamUtil.getParameter(request, "type", true);	//add 、 delete
			String uid =  ParamUtil.getParameter(request, "uid", true);
			String itemId = ParamUtil.getParameter(request, "itemId", true);
			String platform = ParamUtil.getParameter(request, "platform", true);
			
			if(type.equals("add")){
				NotifyFlashSellItemCacher.getInstance().addNotifyUser(uid, itemId, platform);
				NotifyFlashSellNumCacher.getInstance().addNotifyFlashSellNum(itemId);
			}else if(type.equals("delete")){
				NotifyFlashSellItemCacher.getInstance().deleteNotifyUser(uid, itemId, platform);
				NotifyFlashSellNumCacher.getInstance().reduceNotifyFlashSellNum(itemId);
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
