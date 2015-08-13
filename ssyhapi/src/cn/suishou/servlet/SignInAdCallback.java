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

import cn.suishou.bean.ItemAdCallBackResponse;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.ItemAdDAO;
import cn.suishou.manager.SignInManager;
import cn.suishou.tuiguang.ParamBean;
import cn.suishou.tuiguang.TuiGuangThread;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;
import cn.suishou.utils.TPool;
import cn.suishou.utils.WebUtil;

@WebServlet("/api/signInAdCallback")
public class SignInAdCallback extends HttpServlet {
	private static Logger logger = Logger.getLogger(SignInAdCallback.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);		
			String adId = ParamUtil.getParameter(request, "adId", true);	
			
			ItemAdCallBackResponse rsp = SignInManager.getInstance().clickSignAd(uid, adId);
			if(rsp.isSuccess()){	
				retMap.put("description", rsp.getDescription());
				retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
				response.getWriter().print(gson.toJson(retMap));				
							
				ParamBean bean = new ParamBean();
				bean.setActivateip(WebUtil.getIP(request));
				bean.setIdfa(ParamUtil.getParameter(request, "idfa"));
				bean.setCode(ParamUtil.getParameter(request, "version"));
				bean.setOpenudid(ParamUtil.getParameter(request, "openudid"));
				TPool.getInstance().execute(new TuiGuangThread(bean,StringUtil.long2timestamp(System.currentTimeMillis()), uid,3));	
				TPool.getInstance().execute(new UpdateItemAdClickTimeThread(adId));	
			}else{
				retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
				response.getWriter().print(gson.toJson(retMap));	
			}
		}catch(Exception e){
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
			response.getWriter().print(gson.toJson(retMap));
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	class UpdateItemAdClickTimeThread extends Thread{
		private String adId;		
		protected UpdateItemAdClickTimeThread(String adId){
			this.adId = adId;		
		}
		
		public void run() {
			ItemAdDAO.getInstance().updateItemAdClickTime(adId)	;
		}
	}
	
}
