package cn.suishou.servlet.login;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.UserDAO;
import cn.suishou.ramdata.SMSRandomCacher;
import cn.suishou.utils.AES256;
import cn.suishou.utils.MD5;
import cn.suishou.utils.NetManager;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

/**
 * 重设密码并登陆（未登陆状态）
 */
@WebServlet("/login/resetpassandlogin")
public class ResetPasswordAndLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ResetPasswordAndLogin.class);
	
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
			String newPass_encrypt = ParamUtil.getParameter(request, "password", true);
			String phoneNum_encrypt = ParamUtil.getParameter(request, "phone_num", true);

			phoneNum_encrypt = phoneNum_encrypt.replaceAll(" ", "+");
			String phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum_encrypt);
		
			newPass_encrypt = newPass_encrypt.replaceAll(" ", "+");
			String newPass = AES256.decrypt(Config.AES_PWD, newPass_encrypt);
			
			if(!StringUtil.checkPass(newPass)){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PASSWORD_FORMAT_ERROR));			
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
			if(!UserDAO.getInstance().isPhoneExist(phoneNum)){
				String checkcode = AES256.encrypt(Config.AES_PWD, phoneNum);
				String content = NetManager.getInstance().send(Config.old_version_url + "/newversion/resetpass","phone_num="+phoneNum_encrypt+"&password="+newPass_encrypt+"&checkcode="+checkcode);
				
				JSONObject obj = JSONObject.fromObject(content);
				int status = (Integer)obj.get("status");
				if(status != 0){
					retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
					response.getWriter().print(gson.toJson(retMap));
					return;					
				}
			}else{
				UserDAO.getInstance().resetPassword(newPass, phoneNum);
			}
			
			String content = NetManager.getInstance().send("http://127.0.0.1:"+request.getLocalPort()+request.getContextPath() + "/login/loginphone","phone_num="+phoneNum_encrypt+"&password="+newPass_encrypt);
			response.getWriter().print(content);	

		}catch(Exception e){
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.PARAMAS_ERROR));			
			response.getWriter().print(gson.toJson(retMap));	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
