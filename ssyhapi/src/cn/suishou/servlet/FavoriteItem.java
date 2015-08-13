package cn.suishou.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
import cn.suishou.bean.TaobaoItem;
import cn.suishou.bean.VIPItem;
import cn.suishou.bean.superDiscount.SuperDiscountBean;
import cn.suishou.common.Config;
import cn.suishou.common.Value;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.ramdata.FavoriteCacher;
import cn.suishou.ramdata.SelfItemCacher;
import cn.suishou.ramdata.TaobaoItemCacher;
import cn.suishou.ramdata.VIPItemCacher;
import cn.suishou.redis.superDiscount.SuperDiscountCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;
import cn.youhui.log4ssy.api.Log4SSY;
import cn.youhui.log4ssy.utils.Enums.Event;
import cn.youhui.log4ssy.utils.Enums.Store;
import cn.youhui.log4ssy.utils.Enums.Type;

@WebServlet("/api/favoriteItem")
public class FavoriteItem extends HttpServlet {
	private static Logger logger = Logger.getLogger(FavoriteItem.class);
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		try{
			String type = ParamUtil.getParameter(request, "type", true);	//add 、 delete、get
			String uid =  ParamUtil.getParameter(request, "uid", true);	
			
			if(type.equals("add") || type.equals("delete")){
				String itemId = ParamUtil.getParameter(request, "itemId", true);
				String itemChannel =  ParamUtil.getParameter(request, "itemChannel", true);	
				if(type.equals("add")){
					FavoriteCacher.getInstance().addFavorite(uid, itemId, itemChannel);
					Log4SSY.Log(Config.suggestSystemURL, "SSYH", uid, Event.FAV, Type.PRODUCT, Store.SSYH, itemId, "view", "");
				}else if(type.equals("delete")){
					FavoriteCacher.getInstance().deleteFavorite(uid, itemId, itemChannel);
				}				
			}else if(type.equals("get")){
				int page = ParamUtil.getParameterInt(request, "page", 1);
				int pageSize=Value.page_size;
				int totalPage = 0 ;
				List<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
				HashSet<String> favSet = FavoriteCacher.getInstance().getFavorite(uid);
				
				if(favSet != null){
					totalPage = favSet.size() / pageSize ;
					if((favSet.size() % pageSize)>0){
						totalPage ++;
					}
					
					Iterator<String> it = favSet.iterator();
					int i=0;
					while(it.hasNext()){
						if(i<(page-1)*pageSize){						
							it.next();
							i++;
							continue;
						}else if(i>=(page-1)*pageSize && i<page*pageSize){
							int isSuperDiscountOutOfTime = 0;
							HashMap<String, Object> map = null;
							String[] fav = ((String)it.next()).split(",");
							String thisItemId = fav[0];
							int thisItemChannel = Integer.valueOf(fav[1]);
							
							if(thisItemChannel >=100){
								long current = System.currentTimeMillis();
								String date=getDay(current);
								String jsonStr = SuperDiscountCacher.getInstance().get(date, thisItemId);
								jsonStr = jsonStr==null?"":jsonStr;
								SuperDiscountBean superDiscountBean = new Gson().fromJson(jsonStr, SuperDiscountBean.class);
								if(superDiscountBean != null & !ifOnTime(current)){
									isSuperDiscountOutOfTime = 1;
								}
								thisItemChannel -=100;
							}
							
							if(thisItemChannel==Value.item_channel_taobao){
								TaobaoItem taobaoItem = TaobaoItemCacher.getInstance().getItem(thisItemId);
								map = taobaoItem.toMap();
								map.put("isFavorite", 1);
								map.put("itemChannel", thisItemChannel);
								map.put("isFlashSell", 0);
								map.put("isSuperDiscountOutOfTime", isSuperDiscountOutOfTime);
							}else if(thisItemChannel==Value.item_channel_self){
								SelfItem selfItem = SelfItemCacher.getInstance().getItem(thisItemId);					
								map = selfItem.toMap();
								map.put("isFavorite", 1);
								map.put("itemChannel", thisItemChannel);	
								map.put("isFlashSell", 0);	//闪购的自营商品不会出现在喜欢中
								map.put("isSuperDiscountOutOfTime", isSuperDiscountOutOfTime);
							}else if(thisItemChannel==Value.item_channel_vip){
								VIPItem vipItem = VIPItemCacher.getInstance().getItem(thisItemId);
								map = vipItem.toMap();
								map.put("isFavorite", 1);
								map.put("itemChannel", thisItemChannel);
								map.put("isFlashSell", 1);
								map.put("flashSellShowType", 1); //0 未到时间点击显示闹钟提醒   1 未到时间仅仅不能购买
								map.put("flashSellStartTime", vipItem.getStart_time());
								map.put("flashSellEndTime", vipItem.getEnd_time());	
								map.put("isSuperDiscountOutOfTime", isSuperDiscountOutOfTime);
							}
							itemList.add(map);
							i++;
						}else{						
							break;
						}
						
					}
				}
				retMap.put("itemList", itemList);
				retMap.put("page", page);
				retMap.put("totalPage", totalPage);				
			}
			
			retMap.put("status", new RespStatusBuilder(ActionStatus.NORMAL_RETURNED));			
			response.getWriter().print(gson.toJson(retMap));			
		}catch(Exception e){
			logger.error("error stack",e);
			retMap.put("status", new RespStatusBuilder(ActionStatus.SERVER_ERROR));
			response.getWriter().print(gson.toJson(retMap));
		}
	}
	
	public String getDay(long current){	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(current);		
	}
	
	public boolean ifOnTime(long current){   
		SimpleDateFormat sdf=new SimpleDateFormat("HH");
		String h=sdf.format(current);
		if(Integer.parseInt(h)>Value.super_discount_start_time){
			return true;
		}
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	

	
}
