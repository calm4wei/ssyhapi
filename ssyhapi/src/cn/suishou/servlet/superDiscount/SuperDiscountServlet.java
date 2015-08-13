package cn.suishou.servlet.superDiscount;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cn.suishou.bean.superDiscount.SuperDiscountBean;
import cn.suishou.common.Config;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.redis.superDiscount.DataCacheCacher;
import cn.suishou.redis.superDiscount.EditorCacher;
import cn.suishou.redis.superDiscount.RelationCacher;
import cn.suishou.redis.superDiscount.SuperDiscountCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

/**
 * @author jiangjun
 * @since 2014-12-20
 */
@WebServlet("/SuperDiscount")
public class SuperDiscountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static long current=System.currentTimeMillis();
	public static  SimpleDateFormat sdf3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	public static  SimpleDateFormat sdf2=new SimpleDateFormat("HH");
	
	private static Logger logger = Logger.getLogger(SuperDiscountServlet.class);
    public SuperDiscountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject jo=new JsonObject();
		try{
			String platform=ParamUtil.getParameter(request, "platform",true);
			String method=ParamUtil.getParameter(request, "method",true);
			String uid=ParamUtil.getParameter(request, "uid");
			if(method.equals("trailer")){
				if(ifOnTime()){
					String date=RelationCacher.getInstance().getTrailer().split(",")[0];
					String ids=RelationCacher.getInstance().getTrailer().split(",")[1];
					JsonArray ja=new JsonArray();
					for(String itemId:ids.split(",")){
						ja.add(new Gson().fromJson(SuperDiscountCacher.getInstance().get(date, itemId), SuperDiscountBean.class).toJson(uid));
					}
					
					jo.add("status", new JsonParser().parse(new RespStatusBuilder(ActionStatus.NORMAL_RETURNED).toJson()).getAsJsonObject());
					jo.add("super_discount", ja);
					response.getWriter().print(jo.toString());
				}else{
					String screenType=ParamUtil.getParameterInt(request, "screen_type",3)+"";
					if(platform.equalsIgnoreCase("android")){
						screenType="android";
					}
					jo.add("status", new JsonParser().parse(new RespStatusBuilder(ActionStatus.NORMAL_RETURNED).toJson()).getAsJsonObject());
					jo.addProperty("trailer_img", Config.trailerImg+screenType+".png");//如果是iphone:1(ip4);2(ip5,ip5s);3(p6);4(ip6plus)
					response.getWriter().print(jo.toString());
				}
			}else if(method.equals("today")){
					String date=getDay();
					String ids=RelationCacher.getInstance().get(date);
					JsonObject  editor=new JsonParser().parse(EditorCacher.getInstance().get(date)).getAsJsonObject();
					
					//data是超级惠的缓存数据
					String data=DataCacheCacher.getInstance().get(date);
					if(data==null||"".equals(data)){
						JsonArray ja=new JsonArray();
						for(String itemId:ids.split(",")){
							ja.add(new Gson().fromJson(SuperDiscountCacher.getInstance().get(date, itemId), SuperDiscountBean.class).toJson(uid));
						}
						data=ja.toString();
						DataCacheCacher.getInstance().add(date, data);
					}
					
					
					jo.add("status", new JsonParser().parse(new RespStatusBuilder(ActionStatus.NORMAL_RETURNED).toJson()).getAsJsonObject());
					jo.add("editor", editor);
					jo.add("super_discount", new JsonParser().parse(data).getAsJsonArray());
					jo.addProperty("start_time", sdf.format(System.currentTimeMillis())+ " 10:00:00");
					jo.addProperty("current_time", sdf3.format(System.currentTimeMillis()));
					response.getWriter().print(jo.toString());
			}
		}catch (Exception e) {
			logger.error("error stack",e);
			jo.add("status",new JsonParser().parse(new RespStatusBuilder(ActionStatus.SERVER_ERROR).toJson()).getAsJsonObject());
			response.getWriter().print(jo.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public static void main(String[] args) {
		String h=sdf2.format(current);
		System.out.println(Integer.parseInt(h)>17);
	}
	public boolean ifOnTime(){//是否到了可以看预告的时候（下面的整点时间 h大于16代表 17点过后才满足条件）
		String h=sdf2.format(current);
		if(Integer.parseInt(h)>16){
			return true;
		}
		return false;
	}
	
	public String getDay(){//如果是10点前则返货昨天，10点后返回今日 日期
		
		String h=sdf2.format(current);
		if(Integer.parseInt(h)<10){
			return sdf.format(current-86400000);
		}else {
			return sdf.format(current);
		}
	}
	
}
