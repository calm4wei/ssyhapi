package cn.suishou.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.common.ValidateUrlConf;
import cn.suishou.utils.MD5;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * api接口参数校验
 */
@WebFilter("/*")
public class ApiFilter implements Filter {
	private static final String KEY = "77499981";
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");	
		response.setContentType("UTF-8");
		
		String contextPath = ((HttpServletRequest)request).getContextPath();
		String requestURI = ((HttpServletRequest)request).getRequestURI();
		String api = requestURI.replaceAll(contextPath, "");
		if(!ValidateUrlConf.urlSet.contains(api)){ //不需要校验
			chain.doFilter(request, response);
			return;
		}
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
		
		Map<String, String[]> paramMap = request.getParameterMap();
		String sign = request.getParameter("sign");
		String time = request.getParameter("t");
		
		if(sign == null || paramMap == null || paramMap.size() == 0){
			retMap.put("status", new RespStatusBuilder(ActionStatus.PARAMAS_ERROR));
			response.getWriter().print(gson.toJson(retMap));	
			return;
		}
		if(!ParamUtil.checkTime(time)){
			retMap.put("status", new RespStatusBuilder(ActionStatus.SIGN_TIME_OUT));
			response.getWriter().print(gson.toJson(retMap));	
			return;
		}


		if(MD5.check(KEY + ParamUtil.paramsToStr(paramMap) + KEY, sign)){
			chain.doFilter(request, response);
		}else{
//			retMap.put("status", new RespStatusBuilder(ActionStatus.SIGN_FAIL));
//			response.getWriter().print(gson.toJson(retMap));	
//			return;
			chain.doFilter(request, response);
		}		

	}


	public void init(FilterConfig fConfig) throws ServletException {
	}
}
