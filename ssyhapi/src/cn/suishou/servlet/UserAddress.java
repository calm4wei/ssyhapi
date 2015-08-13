package cn.suishou.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import cn.suishou.bean.UserAddrBean;
import cn.suishou.common.Enums.ActionStatus;
import cn.suishou.dao.UserAddressDAO;
import cn.suishou.ramdata.UserAddressCacher;
import cn.suishou.utils.ParamUtil;
import cn.suishou.utils.RespStatusBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 用户收货地址维护API
 * @author  haol
 * @date	2014-12-29
 */
@WebServlet("/api/userAddress")
public class UserAddress extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public static Logger logger = Logger.getLogger(UserAddress.class);
	
	/**
	 * 新增：除了省份和邮编，其余非空
	 * 删除：根据uid和address_id
	 * 修改：比新增多一个address_id
	 * 查询：根据uid或address_id，返回many or one
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		JsonObject successJson = new RespStatusBuilder(ActionStatus.NORMAL_RETURNED).toJsonObject();
		JsonObject errorJson = new RespStatusBuilder(ActionStatus.SERVER_ERROR).toJsonObject();
		JsonObject resultJson = new JsonObject();		// 响应结果JSON
		// "get":根据uid查询，"delete":根据address_id删除
		String type = ParamUtil.getParameter(request, "type", true);
		
		if ("add".equals(type)) {						// 新增：根据uid，要求非空
			UserAddrBean userAddrBean = parseRequestToBean(request);
			// 用户设置了新的默认收货地址时，取消其之前的默认地址（如果有）
			if (UserAddrBean.IS_DEFAULT_YES == userAddrBean.getIsDefault()) {
				UserAddressDAO.getUserAddressDAO().updateNotDefaultByUid(userAddrBean.getUid());
			}
			// DB:新增用户收货地址信息，返回新地址信息ID
			long id = UserAddressDAO.getUserAddressDAO().addUserAddr(userAddrBean);
			if (id > 0) {
				userAddrBean.setId(id);
				reloadDBToCache(userAddrBean.getUid());
				resultJson.add("item", userAddrBean.toJsonObject());
				resultJson.add("status", successJson);
			} else {
				resultJson.add("status", errorJson);
			}
		} else if ("update".equals(type)) {				// 修改：根据uid、addressId，要求非空
			UserAddrBean userAddrBean = parseRequestToBean(request);
			// 如果地址ID非空，则设置到bean
			long addrId = ParamUtil.getParameterLong(request, "addressId");
			userAddrBean.setId(addrId);
			// 用户设置了新的默认收货地址时，取消其之前的默认地址（如果有）
			if (UserAddrBean.IS_DEFAULT_YES == userAddrBean.getIsDefault()) {
				UserAddressDAO.getUserAddressDAO().updateNotDefaultByUid(userAddrBean.getUid());
			}
			boolean dbUpdateOk = UserAddressDAO.getUserAddressDAO().updateUserAddr(userAddrBean);
			if (dbUpdateOk) {
				reloadDBToCache(userAddrBean.getUid());
				resultJson.add("status", successJson);
			} else {
				resultJson.add("status", errorJson);
			}
		} else if ("updateDefaultAddr".equals(type)) {		// 用户修改某条收货地址是否为默认：根据uid、addressId、isDefault，要求非空
			String uid  = ParamUtil.getParameter(request, "uid", true);
			int isDefault = ParamUtil.getParameterInt(request, "isDefault");
			long addressId = ParamUtil.getParameterLong(request, "addressId");
			// 用户设置了新的默认收货地址时，取消其之前的默认地址（如果有）
			if (UserAddrBean.IS_DEFAULT_YES == isDefault) {
				UserAddressDAO.getUserAddressDAO().updateNotDefaultByUid(uid);
			}
			boolean dbUpdateOk = UserAddressDAO.getUserAddressDAO().updateAddrDefaultById(addressId, isDefault);
			if (dbUpdateOk) {
				reloadDBToCache(uid);
				resultJson.add("status", successJson);
			} else {
				resultJson.add("status", errorJson);
			}
		} else if ("delete".equals(type)) {					// 删除：根据uid、addressId，要求非空
			String uid = ParamUtil.getParameter(request, "uid", true);
			long id = ParamUtil.getParameterLong(request, "addressId");
			boolean dbDelOk = UserAddressDAO.getUserAddressDAO().deleteUserAddr(id);
			if (dbDelOk) {
				reloadDBToCache(uid);
				UserAddressCacher.getUserAddressCacher().deleteUserAddr(uid, id);
				resultJson.add("status", successJson);
			}
		} else if ("getAll".equals(type)) {					// 查全部：根据uid，要求非空
			String uid = ParamUtil.getParameter(request, "uid", true);
			JsonArray jsonArray = UserAddressCacher.getUserAddressCacher().getAllUserAddrJsonArray(uid);
			if (jsonArray == null || jsonArray.size() == 0) {	// 如果cache中没有用户收货地址，则从DB中查询
				reloadDBToCache(uid);
				jsonArray = UserAddressCacher.getUserAddressCacher().getAllUserAddrJsonArray(uid);
			}
			resultJson.add("itemList", jsonArray);
			resultJson.add("status", successJson);
		} else if ("getById".equals(type)) {				// 查单个：根据uid、addressId，要求非空
			String uid = ParamUtil.getParameter(request, "uid", true);
			long id = ParamUtil.getParameterLong(request, "addressId");
			JsonObject jsonObject = UserAddressCacher.getUserAddressCacher().getUserAddrJsonObject(uid, id);
			resultJson.add("item", jsonObject);
			resultJson.add("status", successJson);
		}
		response.getWriter().print(new Gson().toJson(resultJson));
	}
	
	/**
	 * 从request解析参数，封装到用户收货地址Bean
	 * @return	用户收货地址Bean
	 */
	private UserAddrBean parseRequestToBean(HttpServletRequest request) 
	{
		String uid  = ParamUtil.getParameter(request, "uid", true);
		String name = ParamUtil.getParameter(request, "name", true);
		String phoneNumber = ParamUtil.getParameter(request, "phoneNumber", true);
		String addressProvince = ParamUtil.getParameter(request, "addressProvince");
		String addressCity = ParamUtil.getParameter(request, "addressCity", true);
		String addressDetail = ParamUtil.getParameter(request, "addressDetail", true);
		String postcode = ParamUtil.getParameter(request, "postcode");
		String isDefaultStr = ParamUtil.getParameter(request, "isDefault");
		int isDefault = NumberUtils.isNumber(isDefaultStr) ? Integer.parseInt(isDefaultStr) : 0;
		
		UserAddrBean userAddrBean = new UserAddrBean(uid, name, phoneNumber, addressProvince, addressCity, addressDetail, postcode, isDefault);
		
		return userAddrBean;
	}
	
	/**
	 * 当新增、修改用户收货地址信息时，先清空该用户的收货地址cache，再从DB中同步到cache
	 * @param uid	uid
	 */
	private void reloadDBToCache(String uid)
	{
		UserAddressCacher.getUserAddressCacher().deleteAllUserAddr(uid);
		List<UserAddrBean> userAddrList = UserAddressDAO.getUserAddressDAO().getAddrsByUid(uid);
		for (UserAddrBean userAddrBean : userAddrList) {
			UserAddressCacher.getUserAddressCacher().addUserAddr(userAddrBean);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
	
}
