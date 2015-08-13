package cn.suishou.tuiguang;

import cn.suishou.dao.UserDevtokenDAO;
import cn.suishou.ramdata.IphoneDevTokenCacher;
import cn.suishou.utils.NetManager;

/**
 * 注册后推送签到页面
 */
public class PushAfRegisterThread extends Thread{	
	private String uid = "";	
	private String openudid = "";
	private String version = "";
	
	public PushAfRegisterThread(String uid, String openudid, String version){
		this.uid = uid;
		this.openudid = openudid;
		this.version = version;
	}

	public void run() {
		if(openudid == null || "".equals(openudid)){
			return;
		}
		String devtoken = UserDevtokenDAO.getInstance().getDevtokenByOpenudid(openudid);
		if(devtoken != null && !"".equals(devtoken)){                  //存储 devtoken
			IphoneDevTokenCacher.getInstance().addDevToken(devtoken);

			if(uid != null && !"".equals(uid)){
				IphoneDevTokenCacher.getInstance().add(uid, devtoken);
				String url = "http://10.10.61.223:8080/MSGPush/MSGPushById?id=1410785324181&formula=uid=" + uid;
				try {
					NetManager.getInstance().getContent(url);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
