package cn.suishou.common;

public class Enums {

	public enum ActionStatus {
		NORMAL_RETURNED(1000, "OK","成功"),		
		PARAMAS_ERROR(1002, "Parameters Error"),
		SERVER_ERROR(1007, "Server Error"), 
		SIGN_FAIL(1010,"Sign fail"),         //参数校验失败
		SIGN_TIME_OUT(1011, "Sign time out"),   //检验时间错误
		/** 请求方式非法 */
		ILLEGAL_REQUEST(1013, "Sign time out", "请求方式非法"),
		
		NOT_IN_TIME(2000,"Not in time!"),  //闪购商品不在时间诶
		PRICE_ERROR(2001,"price error!"),  //商品价格不对
		HAS_NOTIFIED_ERROR(2001,"has notified!"),  //已经提醒发货
		/** 根据指定参数加载订单失败 */
		RETURN_ORDER_NULL(2004, "Return order is null", "退款订单加载失败"),
		/** 根据指定参数加载订单失败 */
		RETURN_ORDER_STATUS(2005, "Return order status is unpaid or refunded", "亲，订单未支付或已退款"),
		/** 退款金额超过订单总额 */
		RETURN_MONEY_OUT(2006, "Return money more than the order", "亲，退款金额不能超过订单总额哦"),
		/** 该笔订单已申请过退款/退货 */
		RETURN_ALREADY_EXIST(2007, "Already reply to return", "亲，您已申请过退款哦"),
		/** 退款时限已过，具体时长由Value.returnOrderEnableDays定义 */
		RETURN_OUT_DATE(2008, "Return money out of date", "亲，退款时限已过了哦"),
		
		USER_NOT_LOGIN(3006,"User not login"),
		PHONENUM_EXIST(3007, "PhoneNum is exist", "介个手机号码已经注册了哦"),
		PHONENUM_NOT_EXIST(3008, "PhoneNum is not exist", "介个手机号码还没有注册过哦"),
		AUTH_CODE_ERROR(3009, "Auth code is error", "验证码不对哦"),
		REGISTER_FAIL(3010, "Register fail" ,"注册失败"),               //注册或绑定失败
		LOGIN_FAIL(3011, "Login fail", "登陆失败"),
		PASSWORD_ERROR(3012, "Password error", "密码输错拉"),
		PASSWORD_FORMAT_ERROR(3013, "Password format error", "密码格式好像不符合规范哦");		

		int code = -1;
		String desc = "";
		String showDesc = "";

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getShowDesc() {
			return showDesc;
		}

		public void setShowDesc(String showDesc) {
			this.showDesc = showDesc;
		}

		ActionStatus(int code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		
		ActionStatus(int code, String desc, String showDesc) {
			this.code = code;
			this.desc = desc;
			this.showDesc = showDesc;
		}
	}

}
