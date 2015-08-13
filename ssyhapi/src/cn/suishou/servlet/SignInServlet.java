package cn.suishou.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.SignIn;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.manager.SignInManager;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

/**
 * 签到接口
 */
@WebServlet("/api/sign")
public class SignInServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(SignInServlet.class);
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			String uid = ParamUtil.getParameter(request, "uid", true);		
			String type = ParamUtil.getParameter(request, "type", true);	
		
			SignIn signIn = null;
			if(!isInTime()){
				signIn = new SignIn();
				signIn.setStatus(4);
			}else{
				if("query".equals(type)){
					signIn = SignInManager.getInstance().getSignIn(uid);
				}else if("sign".equals(type)){
					signIn = SignInManager.getInstance().sign(uid);
				}
			}
			if(signIn == null){
				retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
				response.getWriter().print(gson.toJson(retMap));			
			}else{
				retMap.put("signIn", signIn.toMap());
				retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));			
				response.getWriter().print(gson.toJson(retMap));
			}
		}catch(Exception e){			
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
			response.getWriter().print(gson.toJson(retMap));
		}
		
	}

	/**
	 * 是否在签到时间内
	 * @return
	 */
	private static boolean isInTime(){
		if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 8){
			return true;
		}
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
}
