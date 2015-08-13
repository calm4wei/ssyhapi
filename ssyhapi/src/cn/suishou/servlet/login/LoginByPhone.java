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
import cn.suishou.utils.AES256;
import cn.suishou.utils.MD5;
import cn.suishou.utils.NetManager;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

@WebServlet("/login/loginphone")
public class LoginByPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginByPhone.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("UTF-8");	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		Gson gson = gb.create();
		try{
			logger.info("-------loginphone start");
			String phoneNum_encrypt = ParamUtil.getParameter(request, "phone_num", true);
			String password_encrypt = ParamUtil.getParameter(request, "password", true);
			
			phoneNum_encrypt = phoneNum_encrypt.replaceAll(" ", "+"); 
			password_encrypt = password_encrypt.replaceAll(" ", "+");
	
			String phoneNum = AES256.decrypt(Config.AES_PWD, phoneNum_encrypt);
			String password = AES256.decrypt(Config.AES_PWD, password_encrypt);
		
			if(!UserDAO.getInstance().isPhoneExist(phoneNum)){
				String content = NetManager.getInstance().send(Config.old_version_url + "/newversion/login","phone_num="+phoneNum_encrypt+"&password="+password_encrypt);
				
				JSONObject obj = JSONObject.fromObject(content);
				int status = (Integer)obj.get("status");
				if(status == 0){
					User user = new User();
					String gstr = obj.get("user").toString();	
					user.setUid(JSONObject.fromObject(gstr).getString("uid"));
					user.setPlatform(StringUtil.getSaftyFromGson(gstr, "platform", null));
					user.setPhoneNum(JSONObject.fromObject(gstr).getString("phoneNum"));
					user.setTaobaoNick(StringUtil.getSaftyFromGson(gstr, "taobaoNick", null));
					user.setImei(JSONObject.fromObject(gstr).getString("imei"));
					user.setTaobaoUid(StringUtil.getSaftyFromGson(gstr, "taobaoUid", null));
					user.setImsi(JSONObject.fromObject(gstr).getString("imsi"));
					user.setPassword(JSONObject.fromObject(gstr).getString("password"));
					user.setChannel(JSONObject.fromObject(gstr).getString("channel"));
					user.setCreatTime(getSaftyTimestamp(gstr,"creatTime"));			
					user.setLastTime(getSaftyTimestamp(gstr,"lastTime"));
									
					gstr = ((JSONObject)obj.get("userAccount")).toString();						
					int yuE = JSONObject.fromObject(gstr).getInt("yuE");
					int flCheckNum = JSONObject.fromObject(gstr).getInt("flCheckNum");
					int fl_add_jfb = JSONObject.fromObject(gstr).getInt("fl_add_jfb");
					double flXJ =JSONObject.fromObject(gstr).getDouble("flXJ");
				
					UserDAO.getInstance().insertShiftUser(user, yuE+flCheckNum, flXJ, fl_add_jfb);
					
			//		TPool.getInstance().doit(new ClearJF(user.getUid()));	//清空老系统集分宝

					retMap.put("user", user.toMap());
					retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));					
					response.getWriter().print(gson.toJson(retMap));
					return;					
				}else if(status == 1){
					retMap.put("status", new RespStatusBuilder(ActionStatus.PARAMAS_ERROR));			
					response.getWriter().print(gson.toJson(retMap));
					return;
				}else if(status == 2){
					retMap.put("status", new RespStatusBuilder(ActionStatus.PHONENUM_NOT_EXIST));			
					response.getWriter().print(gson.toJson(retMap));
					return;
				}else if(status == 3){
					retMap.put("status", new RespStatusBuilder(ActionStatus.PASSWORD_ERROR));			
					response.getWriter().print(gson.toJson(retMap));	
					return;
				}			
		
			}
			
			password = MD5.digest(password);
			User user = UserDAO.getInstance().login(phoneNum, password);
			if(user == null || user.getUid() == null || "".equals(user.getUid())){
				retMap.put("status", new RespStatusBuilder(ActionStatus.PASSWORD_ERROR));
				response.getWriter().print(gson.toJson(retMap));
			}else{		
				retMap.put("user", user.toMap());
				retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));	
				response.getWriter().print(gson.toJson(retMap));	
				logger.info("-------loginphone end\n");
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
	
	class ClearJF extends Thread{		
		private String uid;		
	
		protected ClearJF(String uid){
			this.uid = uid;		
		}
		
		public void run() {			
			try {	
				String checkcode = AES256.encrypt(Config.AES_PWD, uid);
				String content = NetManager.getInstance().send(Config.old_version_url + "/newversion/clearJF","uid="+uid+"&checkcode="+checkcode);
				JSONObject obj = JSONObject.fromObject(content);
				int status = (Integer)obj.get("status");
				if(status == 0){
					UserDAO.getInstance().updateIsClearJF(uid);
				}
			} catch (Exception e) {
				logger.error(e);
			}
		}	
	}
	
	private Timestamp getSaftyTimestamp(String gstr, String key){
		long timeMillis = 0;
		try{
			timeMillis = JSONObject.fromObject(gstr).getLong(key);
		}catch(Exception e){
			return new Timestamp(System.currentTimeMillis());
		}	
		return StringUtil.long2timestamp(timeMillis);
	}
	
	public static void main(String[] args) 	{
		try {
//			System.out.println(AES256.encrypt(Config.AES_PWD, "13770751893"));
//			System.out.println(AES256.encrypt(Config.AES_PWD, "112600"));
			
			
			HashMap<String, Object> retMap = new HashMap<String, Object>();
			Gson gson = new Gson();
			Timestamp currentTimestamp = StringUtil.long2timestamp(System.currentTimeMillis());
			retMap.put("currentTimestamp", currentTimestamp.toString());
			String str = gson.toJson(retMap);
			System.out.println(str);
			
			JSONObject obj = JSONObject.fromObject("{\"currentTimestamp\":\"Dec 2, 2014 5:43:44 PM\"}");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
