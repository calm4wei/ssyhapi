package cn.suishou.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.HomePageBanner;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.ramdata.HomePageBannerCacher;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/homePageBanner")
public class GetHomePageBanner extends HttpServlet {
	private static Logger logger = Logger.getLogger(GetHomePageBanner.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			List<HashMap<String,Object>> bannerList = new ArrayList<HashMap<String,Object>>();
			Map<String, String> map = HomePageBannerCacher.getInstance().getAll();
			
			for(Map.Entry<String, String> m : map.entrySet()){
				HomePageBanner homePageBanner = gson.fromJson(m.getValue(), HomePageBanner.class);
				if(homePageBanner.getKey().indexOf("menu")==-1){
					bannerList.add(homePageBanner.toMap());
				}
			}
			
			retMap.put("bannerList", bannerList);
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
	
}
