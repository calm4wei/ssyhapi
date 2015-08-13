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
import cn.suishou.dao.UserDAO;
import cn.suishou.ramdata.IphoneDevTokenCacher;
import cn.suishou.tuiguang.ParamBean;
import cn.suishou.tuiguang.TuiGuangThread;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;
import cn.suishou.utils.TPool;
import cn.suishou.utils.WebUtil;

/**
 * 提供app参数
 */
@WebServlet("/api/appconfig")
public class GetAppconfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(GetAppconfig.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		try {
			String platform = ParamUtil.getParameter(request, "platform");
			String uid = ParamUtil.getParameter(request, "uid");
			String imei = ParamUtil.getParameter(request, "imei");
			String devtoken = ParamUtil.getParameter(request, "devtoken");
			String version = ParamUtil.getParameter(request, "version");
			String result = "";
			
			ParamBean bean = new ParamBean();
			bean.setActivateip(WebUtil.getIP(request));
			bean.setIdfa(ParamUtil.getParameter(request, "idfa"));
			bean.setCode(ParamUtil.getParameter(request, "version"));
			bean.setOpenudid(ParamUtil.getParameter(request, "openudid"));
			TPool.getInstance().execute(new TuiGuangThread(bean,StringUtil.long2timestamp(System.currentTimeMillis()), uid,1));
			
			if("iphone".equalsIgnoreCase(platform) || "ipad".equalsIgnoreCase(platform)){
				if(devtoken != null && !"".equals(devtoken)){
					IphoneDevTokenCacher.getInstance().addDevToken(devtoken);
					if(uid != null && !"".equals(uid)){
						IphoneDevTokenCacher.getInstance().add(uid, devtoken);
					}
				}
			}			
			
			TPool.getInstance().execute(new UpdateLastTimeThread(uid));
			retMap.put("config", "{\"wx_pay_remark\":\"微信支付即10元\"}");
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));		
			retMap.put("result", result);		
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
	
	public class UpdateLastTimeThread implements Runnable{
		private String uid;
		
		public UpdateLastTimeThread(String uid) {
			this.uid = uid;
		}
		
		public void run() {
			UserDAO.getInstance().recordeUserActive(uid);
		}
	}
	
	public static void main(String[] args) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		map.put("wx_pay_remark", "微信支付即10元");

		
		System.out.println("----------"+gson.toJson(map));

	}	
	
}
