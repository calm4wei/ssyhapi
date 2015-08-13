package cn.suishou.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.suishou.bean.PushMessage;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.ramdata.PushMessageCacher;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/api/androidGetPushMessage")
public class AndroidGetPushMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AndroidGetPushMessage.class);
    public AndroidGetPushMessage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String defaultuid = "00000000";
		try{
			
			String uid = request.getParameter("uid");
			
			if(uid == null){
				uid = "";
			}
			
			List<PushMessage> retMsgList = new ArrayList<PushMessage>();
			
			List<PushMessage> msgList = PushMessageCacher.getInstance().getMsg(defaultuid);		
			for(PushMessage msg : msgList){
				if(StringUtil.datetime2long(msg.getStarttime())-System.currentTimeMillis()>1000*60*6){
					retMsgList.add(msg);
				}
			}
			
			if(!"".equals(uid)){
				msgList = PushMessageCacher.getInstance().getMsg(uid);	
			
				for(int i=0; i<msgList.size(); i++){
					if(StringUtil.datetime2long(msgList.get(i).getStarttime())-System.currentTimeMillis()>1000*60*6){
						retMsgList.add((msgList.get(i)));			
						msgList.remove(i);
						i--;
					}
				}
				
				PushMessageCacher.getInstance().resetMsgList(uid, msgList);	
			}		

			retMap.put("retMsgList", retMsgList);
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));	
			response.getWriter().print(gson.toJson(retMap));
	
		}catch(Exception e){
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));
			response.getWriter().print(gson.toJson(retMap));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		
		for(int i=0; i<list.size(); i++){
			System.out.println("----1:"+list.get(i));
			if(list.get(i).equals("2") || list.get(i).equals("5")){
				System.out.println("----2:"+list.get(i));
				list.remove(i);
				i--;
			}
		}
		
		for(String st : list){
			System.out.println("----3:"+st);
		}
		
	}

}
