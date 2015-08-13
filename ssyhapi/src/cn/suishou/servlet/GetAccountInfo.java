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

import cn.suishou.bean.User;
import cn.suishou.bean.UserAccount;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.UserAccountDAO;
import cn.suishou.dao.UserDAO;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

/**
 * 提供账户信息
 */
@WebServlet("/api/getAccountInfo")
public class GetAccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(GetAccountInfo.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		try {
			String uid = ParamUtil.getParameter(request, "uid", true);
			UserAccount ua = UserAccountDAO.getInstance().getUserAccount(uid);
			User user = UserDAO.getInstance().getUser(uid);
			
			int getJF = ua.getShiftJF()+ua.getAcAddJF()+ua.getFlAddJF();
			int payJF = ua.getPayJF();
			int yueJF = ua.getYueJF();
			
			retMap.put("icon", user.getIcon());			
			retMap.put("getJF", getJF);
			retMap.put("payJF", payJF);
			retMap.put("yueJF", yueJF);
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			response.getWriter().print(gson.toJson(retMap));	
		} catch (Exception e){			
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));					
			response.getWriter().print(gson.toJson(retMap));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
