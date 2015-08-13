package cn.suishou.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.SelfItem;
import cn.suishou.bean.Tag;
import cn.suishou.bean.Tag2item;
import cn.suishou.bean.TaobaoItem;
import cn.suishou.bean.VIPItem;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.Value;
import cn.suishou.ramdata.FavoriteCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.ramdata.Tag2ItemCacher;
import cn.suishou.ramdata.Tag2TagCacher;
import cn.suishou.ramdata.TaobaoItemCacher;
import cn.suishou.ramdata.VIPItemCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.suishou.utils.StringUtil;

/**
 * @category 获取标签下商品
 */

@WebServlet("/api/tagitems")
public class GetTagItems extends HttpServlet {
	private static Logger logger = Logger.getLogger(GetTagItems.class);
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		List<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		
		try{
			String tagId = ParamUtil.getParameter(request, "tagid", true);
			String uid = request.getParameter("uid");
			int page = Integer.parseInt(ParamUtil.getParameter(request, "page", true));
			int pageSize = Value.page_size;
			int totalPage = Tag2ItemCacher.getInstance().getTotalPageNum(tagId, pageSize);
			List<Tag2item> tag2items = Tag2ItemCacher.getInstance().getItemsByTagid(tagId, page, pageSize);
			HashSet favSet = FavoriteCacher.getInstance().getFavorite(uid);
			for(Tag2item tag2item : tag2items){
				HashMap<String, Object> map = null;
				int itemChannel = tag2item.getFromChannel();
				String itemId = tag2item.getItemId();
				if(itemChannel==Value.item_channel_taobao){
					TaobaoItem taobaoItem = TaobaoItemCacher.getInstance().getItem(itemId);
					map = taobaoItem.toMap();
					map.put("itemChannel", itemChannel);
					map.put("isFlashSell", 0);
				}else if(itemChannel==Value.item_channel_self){
					SelfItem selfItem = SelfItemCacher.getInstance().getItem(itemId);					
					map = selfItem.toMap();
					map.put("itemChannel", itemChannel);
					map.put("isFlashSell", 0);
				}else if(itemChannel==Value.item_channel_vip){
					VIPItem vipItem = VIPItemCacher.getInstance().getItem(itemId);
					map = vipItem.toMap();
					map.put("itemChannel", itemChannel);
					map.put("isFlashSell", 1);
					map.put("flashSellShowType", 1);
					map.put("flashSellStartTime", vipItem.getStart_time());
					map.put("flashSellEndTime", vipItem.getEnd_time());	
				}				
				
				if(uid != null && !"".equals(uid)){					
					if(favSet.contains(itemId+","+itemChannel)){
						map.put("isFavorite", 1);
					}else{
						map.put("isFavorite", 0);
					}
				}
				
				itemList.add(map);
			}				

			List<Tag> tagList = Tag2TagCacher.getInstance().getTagsByParentId(tagId);
			if(tagList != null && tagList.size()>0){
				retMap.put("tagList", tagList);
			}
				
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));
			retMap.put("currentTime", StringUtil.long2datetime(System.currentTimeMillis()));
			retMap.put("itemList", itemList);
			retMap.put("page", page);
			retMap.put("totalPage", totalPage);
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
