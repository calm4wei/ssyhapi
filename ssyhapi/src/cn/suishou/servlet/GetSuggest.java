package cn.suishou.servlet;

import java.io.IOException;
import java.net.URLEncoder;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import cn.suishou.bean.FlashSellItem;
import cn.suishou.bean.SelfItem;
import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.ramdata.FlashSellItemCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.utils.NetManager;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

@WebServlet("/api/getSuggest")
public class GetSuggest extends HttpServlet {
	private static Logger logger = Logger.getLogger(FavoriteItem.class);
	
	private static final long serialVersionUID = 1L;    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		List<HashMap<String,Object>> itemList = new ArrayList<HashMap<String,Object>>();
		try{
			String uid = ParamUtil.getParameter(request, "uid");
			String itemId =  ParamUtil.getParameter(request, "itemId", true);	
	        
			int getSize = 8;
			int suggestNum = 4; //给客户端商品个数
			
			String content = "";

			if("".equals(uid)){
				SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);		
				content = NetManager.getInstance().getContent(Config.suggestSystemURL+"/getItemSuggest?pid="+itemId+"&title="+URLEncoder.encode(selfItem.getName(), "utf-8")+"&cat="+selfItem.getItem_type()+"&size="+getSize);
			}else{
				content = NetManager.getInstance().getContent(Config.suggestSystemURL+"/getItemSuggest?uid="+uid);
			}
			
			int  count = 0;
			
			JsonArray ja = new JsonParser().parse(content).getAsJsonArray();
			for(int i=0; i<ja.size(); i++){
				if(count >=suggestNum) break;
				String tmpItemId = ja.get(i).getAsString();
				FlashSellItem flashSellItem = FlashSellItemCacher.getInstance().getFlashSellItem(tmpItemId);
				if(flashSellItem == null){
					SelfItem selfItem = SelfItemCacher.getInstance().getItem(tmpItemId);
					HashMap<String, Object> map = null;
					map = selfItem.toMap();
					map.put("itemChannel", 1);
					map.put("isFlashSell", 0);
					itemList.add(map);
					count ++;
				}	
			}
	
			retMap.put("itemList",itemList);
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
