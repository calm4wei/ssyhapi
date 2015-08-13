package cn.suishou.servlet.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

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
import cn.suishou.utils.NetManager;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.SendSMS;

@WebServlet("/login/getrand")
public class GetRandom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(GetRandom.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("UTF-8");	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		Gson gson = gb.create();	
		try {
			String uid = ParamUtil.getParameter(request, "uid");
			String phoneNum = ParamUtil.getParameter(request, "phone_num");
			String type = ParamUtil.getParameter(request, "type", true);
			if(phoneNum == null || "".equals(phoneNum)){
				phoneNum = UserDAO.getInstance().getPhone(uid);
			}else{
				try{
					phoneNum = phoneNum.replaceAll(" ", "+");
					phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
				} catch (Exception e) {
					logger.error("error stack",e);
					retMap.put("status", new RespStatusBuilder(ActionStatus.PARAMAS_ERROR));			
					response.getWriter().print(gson.toJson(retMap));	
					return;
				}
			}
			if(phoneNum == null || "".equals(phoneNum)){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PARAMAS_ERROR));			
				response.getWriter().print(gson.toJson(retMap));
				return;
			}
			if("register".equalsIgnoreCase(type)){
				if(UserDAO.getInstance().isPhoneExist(phoneNum)){
					String content = NetManager.getInstance().send(Config.old_version_url + "/newversion/isPhoneNumExit","phone_num="+phoneNum);
					JSONObject obj = JSONObject.fromObject(content);
					int status = (Integer)obj.get("status");
					if(status == 1){
						retMap.put("status", new RespStatusBuilder(ActionStatus.PHONENUM_EXIST));					
						response.getWriter().print(gson.toJson(retMap));
						return;
					}
				}
			}else if("forgetpass".equalsIgnoreCase(type)){
				if(!UserDAO.getInstance().isPhoneExist(phoneNum)){
					String content = NetManager.getInstance().send(Config.old_version_url + "/newversion/isPhoneNumExit","phone_num="+phoneNum);
					JSONObject obj = JSONObject.fromObject(content);
					int status = (Integer)obj.get("status");
					if(status == 2){
						retMap.put("status", new RespStatusBuilder(ActionStatus.PHONENUM_EXIST));					
						response.getWriter().print(gson.toJson(retMap));
						return;
					}
				}
			}else {
				retMap.put("status", new RespStatusBuilder(ActionStatus.PARAMAS_ERROR));			
				response.getWriter().print(gson.toJson(retMap));
				return;
			}
			String randomNum = SMSRandomCacher.getInstance().getRandomNum(phoneNum);
			if(randomNum == null || "".equals(randomNum)){
				randomNum = getRandomStr();
			}
			SMSRandomCacher.getInstance().saveSMSRandomNum(phoneNum, randomNum);
			
			boolean flag = false;
			if("register".equalsIgnoreCase(type)){
				flag = SendSMS.sendRegCode(phoneNum, randomNum);
			}else if("forgetpass".equalsIgnoreCase(type)){
				flag = SendSMS.sendForgCode(phoneNum, randomNum);
			}
			if(flag){		
				retMap.put("phone_num", changePn(phoneNum));
				retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));			
				response.getWriter().print(gson.toJson(retMap));
			}else{			
				retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
				response.getWriter().print(gson.toJson(retMap));
			}
		} catch (Exception e) {
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));			
			response.getWriter().print(gson.toJson(retMap));
		}
	}
	
	private String changePn(String phoneNum){
		if(phoneNum == null || phoneNum.length() != 11){
			return "";
		}else{
			return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, 11);
		}
	}
	
	private static String getRandomStr(){
		StringBuffer buffer = new StringBuffer("0123456789");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < 6; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
