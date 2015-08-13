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
import cn.suishou.dao.OrderDAO;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/compliantOrder")
public class CompliantOrder extends HttpServlet {
	private static Logger logger = Logger.getLogger(CompliantOrder.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
			String orderId = ParamUtil.getParameter(request, "orderId", true);
			int compliantReason = ParamUtil.getParameterInt(request, "compliantReason");
			String compliantContact = ParamUtil.getParameter(request, "compliantContact", true);
			
			OrderDAO.getInstance().updateOrderCompliantStatus(uid, orderId, compliantReason, compliantContact);
		
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
