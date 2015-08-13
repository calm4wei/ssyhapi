package cn.suishou.servlet;

import java.io.IOException;
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

import cn.suishou.bean.TradeMx;
import cn.suishou.common.Value;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.TradeMXDAO;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

/**
 * 提供账户信息
 */
@WebServlet("/api/getTradeList")
public class GetTradeMX extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(GetTradeMX.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		try {
			List<TradeMx> tradeMXList = null;
			String uid = ParamUtil.getParameter(request, "uid", true);
			String type = ParamUtil.getParameter(request, "type", true);
			int page = ParamUtil.getParameterInt(request, "page", 1);
			int pageSize = Value.page_size;
			int totalPage = 0;
			if("add".equals(type)){
				int count = TradeMXDAO.getInstance().getJFAddMXNum(uid);
				tradeMXList = TradeMXDAO.getInstance().getJFAddMX(uid, page, pageSize);
				
				totalPage = count / pageSize ;
				if((count % pageSize)>0){
					totalPage ++;
				}
			}else if("pay".equals(type)){
				int count = TradeMXDAO.getInstance().getJFPayMXNum(uid);
				tradeMXList = TradeMXDAO.getInstance().getJFPayMX(uid, page, pageSize);
				totalPage = count / pageSize ;
				if((count % pageSize)>0){
					totalPage ++;
				}
			}
			retMap.put("totalPage", totalPage);
			retMap.put("tradeList", tradeMXList);
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
