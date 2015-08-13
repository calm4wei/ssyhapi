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

import cn.suishou.bean.ItemAd;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.ItemAdDAO;
import cn.suishou.utils.RespStatusBuilder;

/**
 * 签到接口
 */
@WebServlet("/api/getRegisterAd")
public class GetRegisterAd extends HttpServlet {
	private static Logger logger = Logger.getLogger(SignInServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		
		try{
			ItemAd itemAd = ItemAdDAO.getInstance().getRegisterAd();			
			retMap.put("Ad", itemAd);
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			response.getWriter().print(gson.toJson(retMap));			
		}catch(Exception e){			
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
			response.getWriter().print(gson.toJson(retMap));
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
