package cn.suishou.servlet.urlConvert;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.suishou.common.Config;
import cn.suishou.dao.AdUrlConvertDAO;
import cn.suishou.utils.StringUtil;
import sun.misc.BASE64Decoder;

@WebServlet("/AdUrlConvertServlet")
public class AdUrlConvertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     

    public AdUrlConvertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String url_address = StringUtil.getSaftStringFromRequest(request, "url_address", "");
		String adid = StringUtil.getSaftStringFromRequest(request, "adid", "");
		String encode = StringUtil.getSaftStringFromRequest(request, "encode", "");
		String url_name = StringUtil.getSaftStringFromRequest(request, "url_name", "");
		String url_describtion = StringUtil.getSaftStringFromRequest(request, "url_describtion", "");
		String id = StringUtil.getSaftStringFromRequest(request, "id", "");
		String uid = StringUtil.getSaftStringFromRequest(request, "uid", "suishouyouhui");
		String keyname = StringUtil.getSaftStringFromRequest(request, "key", "");
		
		if(url_address==null||"".equals(url_address)) {
			response.getWriter().println("");
			return;
		}
		
		if(url_address != null && !"".equals(url_address)) {
			url_address = new String(url_address.getBytes("iso-8859-1"), "utf-8");
		}
		
		if("1".equals(encode)) {
			url_address = new String(new BASE64Decoder().decodeBuffer(url_address));
		}
		
		if(url_describtion == null || url_describtion.equals("")) {
			url_describtion =url_name;
		}
	
		String url = "";		
		String key =  "";
		Pattern pattern1=Pattern.compile("http://");
		Matcher content=pattern1.matcher(url_address); 
		if(content.find()) {
			key = AdUrlConvertDAO.insert(adid,id,keyname,url_address,url_name,url_describtion,uid);
			url = Config.serverHost + request.getContextPath() + "/pageSkip?urlkey=" +key;
		} else {
			url = "";
		}	
		response.getWriter().println(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
