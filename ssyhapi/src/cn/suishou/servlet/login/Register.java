package cn.suishou.servlet.login;

import java.io.IOException;
import java.sql.Timestamp;
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

import cn.suishou.bean.User;
import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.UserDAO;
import cn.suishou.ramdata.SMSRandomCacher;
import cn.suishou.tuiguang.ParamBean;
import cn.suishou.tuiguang.PushAfRegisterThread;
import cn.suishou.tuiguang.TuiGuangThread;
import cn.suishou.utils.AES256;
import cn.suishou.utils.MD5;
import cn.suishou.utils.NetManager;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;
import cn.suishou.utils.TPool;
import cn.suishou.utils.WebUtil;

@WebServlet("/login/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Register.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("UTF-8");	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		Gson gson = gb.create();		
		try{
			String imei = ParamUtil.getParameter(request, "imei");
			String imsi = ParamUtil.getParameter(request, "imsi");
			String platform = ParamUtil.getParameter(request, "platform");
			String channel = ParamUtil.getParameter(request, "channel");
			String openudid = ParamUtil.getParameter(request, "openudid");
			String version = request.getParameter("version");
			String phoneNum = ParamUtil.getParameter(request, "phone_num", true);
			String password = ParamUtil.getParameter(request, "password", true);
			String randomNum = ParamUtil.getParameter(request, "random", true);
			
			phoneNum = phoneNum.replaceAll(" ", "+");
			password = password.replaceAll(" ", "+");
			phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum);
			password = AES256.decrypt(Config.AES_PWD, password);

			String randomNCa = SMSRandomCacher.getInstance().getRandomNum(phoneNum);
			
			if(!StringUtil.checkPass(password)){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PASSWORD_FORMAT_ERROR));					
				response.getWriter().print(gson.toJson(retMap));
				return;
			}
			if(!(randomNCa != null && (randomNCa.equals(randomNum) ))){
				retMap.put("status", new RespStatusBuilder(ActionStatus.AUTH_CODE_ERROR));					
				response.getWriter().print(gson.toJson(retMap));
				return;
			}
			
			if(UserDAO.getInstance().isPhoneExist(phoneNum)){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PHONENUM_EXIST));					
				response.getWriter().print(gson.toJson(retMap));
				return;
			}else{
				String content = NetManager.getInstance().send(Config.old_version_url + "/newversion/isPhoneNumExit","phone_num="+phoneNum);
				JSONObject obj = JSONObject.fromObject(content);
				int status = (Integer)obj.get("status");
				if(status == 1){
					retMap.put("status", new RespStatusBuilder(ActionStatus.PHONENUM_EXIST));					
					response.getWriter().print(gson.toJson(retMap));
					return;
				}
			}
			
			password = MD5.digest(password);
			
			User user = new User("", "" , "" , phoneNum, password, imei, imsi, channel, platform);
			UserDAO.getInstance().registerByPhone(user);        //注册
			if(user == null || null == user.getUid() || "".equals(user.getUid())){
				retMap.put("status", new RespStatusBuilder(ActionStatus.REGISTER_FAIL));					
				response.getWriter().print(gson.toJson(retMap));
			}else{
				ParamBean bean = new ParamBean();
				bean.setActivateip(WebUtil.getIP(request));
				bean.setIdfa(ParamUtil.getParameter(request, "idfa"));
				bean.setCode(ParamUtil.getParameter(request, "version"));
				bean.setOpenudid(ParamUtil.getParameter(request, "openudid"));
				TPool.getInstance().execute(new TuiGuangThread(bean,new Timestamp(System.currentTimeMillis()), user.getUid(),2));
				TPool.getInstance().execute(new PushAfRegisterThread(user.getUid(), openudid, version));	
				retMap.put("user", user.toMap());	
				retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));	
			}
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
