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

import cn.suishou.bean.Order;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.OrderDAO;
import cn.suishou.dao.ProviderDAO;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.SendSMS;

@WebServlet("/api/notifyDelivery")
public class NotifyDelivery extends HttpServlet {
	private static Logger logger = Logger.getLogger(NotifyDelivery.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
	
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);
			String orderId = ParamUtil.getParameter(request, "orderId", true);				
			
			Order order = OrderDAO.getInstance().getOrder(orderId,uid);	
			String title = order.getTitle();
			String province = order.getAddress_province();
			String name = order.getName();
			String providerId = order.getProviderId();
			
			if(order.getNotify_logistics_time()>1){
				retMap.put("status", new RespStatusBuilder(ActionStatus.HAS_NOTIFIED_ERROR));
			}else{
				String providerPhoneNum = ProviderDAO.getInstance().getProvider(providerId).getPhoneNum();
				String content = "您有一笔订单【" + title + "】发往【" + province +"】,客户姓名【" + name +"】,请尽快发货！";
				SendSMS.send(providerPhoneNum, content);
				OrderDAO.getInstance().updateNotifyLogisticsTime(uid, orderId);
				retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			}

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
