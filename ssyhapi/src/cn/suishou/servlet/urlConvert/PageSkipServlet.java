package cn.suishou.servlet.urlConvert;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.bean.AdConvert;
import cn.suishou.bean.SkipBean;
import cn.suishou.common.Config;
import cn.suishou.dao.AdUrlConvertDAO;
import cn.suishou.dao.SkipDAO;
import cn.suishou.utils.StringUtil;
import cn.suishou.utils.WebUtil;

@WebServlet("/pageSkip")
public class PageSkipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String error = "http://m.taobao.com/";
	
	private static ExecutorService ThreadPool = Executors.newFixedThreadPool(10);

    public PageSkipServlet(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String urlkey= (String) request.getAttribute("urlkey");
		String uid = StringUtil.getSaftStringFromRequest(request, "uid", "");
		String channel =StringUtil.getSaftStringFromRequest(request, "channel", "");
		String version = StringUtil.getSaftStringFromRequest(request, "version", "");
		String platform = StringUtil.getSaftStringFromRequest(request, "platform", "");
		String app =  StringUtil.getSaftStringFromRequest(request,"app","");
		String tag_id = StringUtil.getSaftStringFromRequest(request,"tag_id","");
		
		if(urlkey == null || "".equals(urlkey)) {
			response.sendRedirect(error);
			return;
		}
		
        if(urlkey.subSequence(0, 1).equals("X")) {
			AdConvert bean = AdUrlConvertDAO.getAdBean(urlkey);
			
			if (bean != null) {				
				if(Config.isNeedSaveItem){
					SkipBean skipBean = new SkipBean( urlkey,"" , uid, bean.getId(), channel, version, tag_id, platform, "adjump", app, System.currentTimeMillis(), WebUtil.getIP(request), "") ;		
					ThreadPool.execute(new ItemSkipThread(skipBean));
				}
				Map<String, String[]> params = request.getParameterMap();
				params.put("sche", new String[]{"suishou"});
				response.sendRedirect(addParams(bean.getUrl(), params));
			} else {
				response.sendRedirect(error);
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	private String addParams(String url, Map<String, String[]> params){
		String returl = url;
		if(params != null && params.size() > 0){
			if(returl.indexOf("?") == -1){
				returl += "?";
			}
			for(Map.Entry<String, String[]> m : params.entrySet()){
				returl += "&" + m.getKey() + "=" + m.getValue()[0];
			}
			if(returl.lastIndexOf("?") == returl.length() - 1){
				returl = returl.substring(0, returl.length() -1);
			}
		}
		return returl;
	}
	
	public class ItemSkipThread extends Thread{
		private SkipBean skipBean;		
		
		public ItemSkipThread(SkipBean skipBean){
			this.skipBean = skipBean;
			
		}

		@Override
		public void run(){
			try {
				SkipDAO.addSkip(skipBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	


}
