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

import cn.suishou.bean.Feedback;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.FeedbackDAO;
import cn.suishou.ramdata.FeedbackCacher;
import cn.suishou.utils.DateUtil;
import cn.suishou.utils.RespStatusBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 反馈与回复Servlet
 * @author  haol
 * @date	2015-01-07
 */
@WebServlet("/api/feedback")
public class FeedbackServlet extends HttpServlet
{
	private static Logger logger = Logger.getLogger(FeedbackServlet.class);
	private static final long serialVersionUID = 1L;
	/** server执行正常返回的状态 */
	private static final JsonObject SUCCESS_JSON = new RespStatusBuilder(ActionStatus.NORMAL_RETURNED).toJsonObject();
	/** server执行异常返回的状态 */
	private static final JsonObject ERROR_JSON = new RespStatusBuilder(ActionStatus.SERVER_ERROR).toJsonObject();
	/** app提交参数错误返回的状态 */
	private static final JsonObject PARAM_JSON = new RespStatusBuilder(ActionStatus.PARAMAS_ERROR).toJsonObject();
	/** 分页页大小 */
	private static int PAGE_SIZE = 10;
	
	/** get处理查询反馈与回复请求 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JsonObject resultJson = null;
		try {
			// 验证并解析客户端提交数据
			resultJson = validateGet(request);
			if (resultJson == null) {
				Feedback bean = parseRequestToBean(request);	// 解析并封装客户端提交数据
				// 1.查Cache:根据uid加载用户type类型的反馈与回复
				JsonArray jsonArray = FeedbackCacher.getInstance().getJsonArrayByUid(bean.getUid(), bean.getType(), bean.getCursorId(), PAGE_SIZE);
				if (jsonArray == null || jsonArray.size() == 0) {
					// 2.查DB:新增或更新意见反馈/客服回复，返回执行结果
					
				}
				
				
				resultJson = new JsonObject();
				resultJson.add("itemList", jsonArray);
				
				
			}
		} catch (Exception e) {
			logger.error(e, e);
			resultJson = new JsonObject();
			resultJson.add("status", ERROR_JSON);
		} finally {
			response.getWriter().print(new Gson().toJson(resultJson));
		}
	}
	
	/** post处理用户发表意见反馈 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		JsonObject resultJson = null;
		try {
			// 验证并解析客户端提交数据
			resultJson = validatePost(request);
			if (resultJson == null) {
				Feedback bean = parseRequestToBean(request); 	// 解析并封装客户端提交数据
				// 1.DB:新增或更新意见反馈/客服回复，返回执行结果
				long id = FeedbackDAO.getInstance().addFeedback(bean);
				bean.setId(id);
				// 2.Cache:将单条意见反馈/客服回复加入反馈与回复zset，以id为score。用户的反馈，与其收到的回复放一起，按id排列
				FeedbackCacher.getInstance().addFeedback(bean);
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
	private JsonObject validateGet(HttpServletRequest request)
	{
		JsonObject resultJson = null;
		// 确保uid非空、type是数字
		String uid = request.getParameter("uid");
		String typeStr = request.getParameter("type");
		String cursorIdStr = request.getParameter("cursorId");
		if (cursorIdStr == null) {
			cursorIdStr = "";
		}
		if (StringUtils.isBlank(uid) || !NumberUtils.isNumber(typeStr) || !StringUtils.isNumeric(cursorIdStr)) {			
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
	private JsonObject validatePost(HttpServletRequest request)
	{
		JsonObject resultJson = null;
		// 确保uid和content非空、type是数字
		String uid = request.getParameter("uid");
		String typeStr = request.getParameter("type");
		String content = request.getParameter("content");
		
		if (StringUtils.isBlank(uid) || !NumberUtils.isNumber(typeStr) || StringUtils.isBlank(content) || content.length() > 500) {			
			resultJson = new JsonObject();
			resultJson.add("status", PARAM_JSON);
			return resultJson;
		}
		return resultJson;
	}

	/**
	 * 解析并封装客户端提交数据<br>
	 * 1.验证不通过：JsonObject对象包含错误信息
	 * 2.验证通过:JsonObject为null，MAUStatsBean对象包含解析出的字段
	 * @param request	客户端提交数据
	 * @return			验证不通过：JsonObject对象包含错误信息；验证通过:JsonObject为null
	 */
	private Feedback parseRequestToBean(HttpServletRequest request)
	{
		// 确保uid、type非空、停留时间为空或是数字
		String uid = request.getParameter("uid");
		int type = Integer.parseInt(request.getParameter("type"));
		String content = request.getParameter("content");
		String cursorIdStr = request.getParameter("cursorId");
		// app第一次请求不传cursorId，从其zset的最后往前检索
		long cursorId = NumberUtils.isNumber(cursorIdStr) ? Long.parseLong(cursorIdStr) : Long.MAX_VALUE;
		if (cursorId == 0) {
			cursorId = Long.MAX_VALUE;
		}
		Feedback bean = new Feedback(uid, DateUtil.getTodayStr19(), type, content, cursorId);
		return bean;
	}
	
}
