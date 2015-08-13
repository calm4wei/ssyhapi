package cn.suishou.utils;

public class SendSMS {
	
	private static String sendmsgPath = "http://121.199.16.178/webservice/sms.php?method=Submit";

	private static boolean sendCode(String phone, String content){
		String params = "&account=cf_suishou&password=19880109";
		params = params + "&mobile=" + phone + "&content=" + content;
		String ret = null;
		try {
			ret = NetManager.getInstance().send(sendmsgPath, params);
		} catch (Exception e) {
			e.printStackTrace();
			ret = null;
		}
		System.out.println(ret);
		if(ret != null && ret.indexOf("<code>2</code>") > -1) return true;
		else return false;
	}
	
	public static boolean sendForgCode(String phone, String code){
		String content = "验证码 【"+code+"】,您正在使用随手科技产品，需要进行验证码校验。【为保证账号安全请勿将此验证码提供给他人】";
		return sendCode(phone, content);
	}
	
	public static boolean sendRegCode(String phone, String code){
		String content = "验证码 【"+code+"】,您正在使用随手科技产品，需要进行验证码校验。【为保证账号安全请勿将此验证码提供给他人】";
		return sendCode(phone, content);
	}
	
	public static boolean send(String phone, String content){	
		return sendCode(phone, content);
	}
	
	public static void main(String[] args) {
		System.out.println(sendRegCode("15850673417", "123456"));
	}
}
