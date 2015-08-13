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

import cn.suishou.bean.Order;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.dao.OrderDAO;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/deleteOrder")
public class DeleteOrder extends HttpServlet {
	private static Logger logger = Logger.getLogger(DeleteOrder.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		List<HashMap<String,Object>> orderList = new ArrayList<HashMap<String,Object>>();
	
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
			String orderId = ParamUtil.getParameter(request, "orderId", true);				
			
			Order order = OrderDAO.getInstance().getOrder(orderId,uid);	
			if(order.getStatus() == Value.order_status_unpaid){
				OrderDAO.getInstance().deleteOrder(uid, orderId);
			}else{
				OrderDAO.getInstance().updateClientShownStatus(uid, orderId, Value.order_client_unshown);
			}
		
			retMap.put("orderList", orderList);
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
