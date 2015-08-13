package cn.suishou.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.common.Value;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.PushMsgDAO;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/getPushMsgList")
public class GetPushMsgList extends HttpServlet {
	private static Logger logger = Logger.getLogger(GetPushMsgList.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		int pageSize = Value.page_size;
		int totalPage = 0 ;
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);	
			int page = ParamUtil.getParameterInt(request, "page");
			List<HashMap<String, Object>> retlist = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> list = PushMsgDAO.getInstance().getMsgList(uid);
			
			totalPage = list.size() / pageSize ;
			if((list.size() % pageSize)>0){
				totalPage ++;
			}

			for(int i=(page-1)*pageSize; i<page*pageSize; i++){
				retlist.add(list.get(i));
			}
			
			retMap.put("PushMsg", retlist);
			retMap.put("page", page);
			retMap.put("totalPage", totalPage);
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
