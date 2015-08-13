package cn.suishou.servlet.login;

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

import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.UserDAO;
import cn.suishou.ramdata.SMSRandomCacher;
import cn.suishou.utils.AES256;
import cn.suishou.utils.MD5;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

/**
 * 重设密码（已登陆状态）
 *
 */
@WebServlet("/login/resetpass")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ResetPassword.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");		
		response.setContentType("UTF-8");	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		Gson gson = gb.create();
		try{
			String uid = ParamUtil.getParameter(request, "uid");
			String random = ParamUtil.getParameter(request, "random", true);
			String newPass = ParamUtil.getParameter(request, "password", true);
			String phoneNum = ParamUtil.getParameter(request, "phone_num");
			if(StringUtil.isEmpty(phoneNum)){
				phoneNum = UserDAO.getInstance().getPhone(uid);
			}else{
				phoneNum = phoneNum.replaceAll(" ", "+");
				phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
			}
				newPass = newPass.replaceAll(" ", "+");
				newPass = AES256.decrypt(Config.AES_PWD, newPass);
			
			if(!StringUtil.checkPass(newPass)){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PASSWORD_FORMAT_ERROR));			
				response.getWriter().print(gson.toJson(retMap));	
				return;
			}
			if(phoneNum == null || "".equals(phoneNum)){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PHONENUM_NOT_EXIST));			
				response.getWriter().print(gson.toJson(retMap));	
				return;
			}
			String randomNCa = SMSRandomCacher.getInstance().getRandomNum(phoneNum);
			if(!(randomNCa != null && (randomNCa.equals(random)))){
				retMap.put("status", new RespStatusBuilder(ActionStatus.AUTH_CODE_ERROR));			
				response.getWriter().print(gson.toJson(retMap));	
				return;
			}
			newPass = MD5.digest(newPass);
			if(UserDAO.getInstance().resetPassword(newPass, phoneNum)){
				retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));			
				response.getWriter().print(gson.toJson(retMap));	
			}else {
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

}
