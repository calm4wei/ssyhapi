package cn.suishou.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import cn.suishou.bean.MAUStatsBean;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.ramdata.MAUStatsCacher;
import cn.suishou.utils.DateUtil;
import cn.suishou.utils.RespStatusBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 用户月度活跃时长统计Servlet
 * @author  haol
 * @date	2015-01-05
 */
@WebServlet("/api/mauStats")
public class MAUStatsServlet extends HttpServlet
{
	private static Logger logger = Logger.getLogger(MAUStatsServlet.class);
	private static final long serialVersionUID = 1L;
	/** server执行正常返回的状态 */
	private static final JsonObject SUCCESS_JSON = new RespStatusBuilder(ActionStatus.NORMAL_RETURNED).toJsonObject();
	/** server执行异常返回的状态 */
	private static final JsonObject ERROR_JSON = new RespStatusBuilder(ActionStatus.SERVER_ERROR).toJsonObject();
	/** app提交参数错误返回的状态 */
	private static final JsonObject PARAM_JSON = new RespStatusBuilder(ActionStatus.PARAMAS_ERROR).toJsonObject();
	/** app请求方式非法返回的状态 */
	private static final JsonObject ILLEGAL_JSON = new RespStatusBuilder(ActionStatus.ILLEGAL_REQUEST).toJsonObject();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JsonObject resultJson = null;
		try {
			// 验证并解析客户端提交数据
			resultJson = validate(request);
			if (resultJson == null) {
				MAUStatsBean bean = parseRequestToBean(request);
				// 为指定 user 增加月度在线时长（毫秒数）
				MAUStatsCacher.getInstance().increaseMAU(bean, DateUtil.getCurMonthStr6());
				resultJson = new JsonObject();
				resultJson.add("status", SUCCESS_JSON);
			}
		} catch (Exception e) {
			logger.error(e, e);
			resultJson = new JsonObject();
			resultJson.add("status", ERROR_JSON);
		} finally {
			response.getWriter().print(new Gson().toJson(resultJson));
		}
	}
	
	/**
	 * 验证并解析客户端提交数据<br>
	 * 1.验证不通过：JsonObject对象包含错误信息
	 * 2.验证通过:JsonObject为null，MAUStatsBean对象包含解析出的字段
	 * @param request	客户端提交数据
	 * @return			验证不通过：JsonObject对象包含错误信息；验证通过:JsonObject为null
	 */
	private JsonObject validate(HttpServletRequest request)
	{
		JsonObject resultJson = null;
		// 确保uid和taoNick非空、停留时间是数字
		String uid = request.getParameter("uid");
		
		String stayMillisStr = request.getParameter("stayMillis");
		if (!NumberUtils.isNumber(stayMillisStr) || StringUtils.isBlank(uid)) {			
			resultJson = new JsonObject();
			resultJson.add("status", PARAM_JSON);
			return resultJson;
		}
		long stayMillis = Long.parseLong(stayMillisStr);	// 解析出毫秒数，确保不大于1天
		if (stayMillis > DateUtil.ONE_DAY_MILLI_SECONDS) {
			resultJson = new JsonObject();
			resultJson.add("status", PARAM_JSON);
			return resultJson;
		}
		
		return resultJson;
	}

	/**
	 * 验证并解析客户端提交数据<br>
	 * 1.验证不通过：JsonObject对象包含错误信息
	 * 2.验证通过:JsonObject为null，MAUStatsBean对象包含解析出的字段
	 * @param request	客户端提交数据
	 * @return			验证不通过：JsonObject对象包含错误信息；验证通过:JsonObject为null
	 */
	private MAUStatsBean parseRequestToBean(HttpServletRequest request)
	{
		// 确保uid非空、停留时间是数字
		String uid = request.getParameter("uid");
		long stayMillis = Long.parseLong(request.getParameter("stayMillis"));
		
		MAUStatsBean bean = new MAUStatsBean(DateUtil.getCurMonthStr6(), uid, stayMillis);
		return bean;
	}

	/** 由于提交的数据中可能包含汉字，所以禁止Get方式提交 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JsonObject resultJson = new JsonObject();
		resultJson.add("status", ILLEGAL_JSON);
		response.getWriter().print(new Gson().toJson(resultJson));
	}
	
	
	
}
