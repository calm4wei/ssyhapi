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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.JfItem;
import cn.suishou.bean.SelfItem;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.ramdata.JfItemCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/getJfItem")
public class GetJfItem extends HttpServlet {
	private static Logger logger = Logger.getLogger(GetPaymentId.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		try{
			List<HashMap<String, Object>> hotList = new ArrayList<HashMap<String, Object>>();
			List<HashMap<String, Object>> allList = new ArrayList<HashMap<String, Object>>();
			List<JfItem> jfItemList = JfItemCacher.getInstance().getJfItems();
			
			for(JfItem jfItem : jfItemList){
				int isHot = jfItem.getIsHot();
				String itemId = jfItem.getItemId();
				SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);	
				HashMap<String, Object> map = selfItem.toDetailMap();
				map.put("itemChannel", Value.item_channel_self);
				if(isHot ==1){
					hotList.add(map);
					allList.add(map);
				}else{
					hotList.add(map);
				}
			}
			
			retMap.put("hotList", hotList);
			retMap.put("allList", allList);			
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
