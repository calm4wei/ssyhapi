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
import cn.suishou.ramdata.NotifySuperDiscountItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

@WebServlet("/api/notifySuperDiscountItem")
public class NotifySuperDiscountItem extends HttpServlet {
	private static Logger logger = Logger.getLogger(NotifySuperDiscountItem.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			String type = ParamUtil.getParameter(request, "type", true);	//add 、 delete
			
			String uid =  ParamUtil.getParameter(request, "uid", true);
			String platform = ParamUtil.getParameter(request, "platform", true);
			
			String date = ParamUtil.getParameter(request, "date", true);
			String itemId = ParamUtil.getParameter(request, "itemId", true);
			
			String title = ParamUtil.getParameter(request, "title", true);
			String clickurl = ParamUtil.getParameter(request, "clickurl", true);
			String icon = ParamUtil.getParameter(request, "icon", true);
			int itemChannel = ParamUtil.getParameterInt(request, "itemChannel");			
			
			if(!StringUtil.checkDateFormat(date, "yyyy-MM-dd")){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PARAMAS_ERROR));
				response.getWriter().print(gson.toJson(retMap));	
				return;
			}
			
			if(type.equals("add")){
				NotifySuperDiscountItemCacher.getInstance().addNotifyUser(uid, date, itemId, platform);
				NotifySuperDiscountItemCacher.getInstance().setItemInfo(date, itemId, title, clickurl, icon, itemChannel);
			}else if(type.equals("delete")){
				NotifySuperDiscountItemCacher.getInstance().deleteNotifyUser(uid, date, itemId, platform);
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
