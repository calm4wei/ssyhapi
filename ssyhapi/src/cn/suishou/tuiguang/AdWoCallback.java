package cn.suishou.tuiguang;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import cn.suishou.utils.NetManager;

public class AdWoCallback implements TuiGuang{
	private static Logger logger = Logger.getLogger(AdWoCallback.class);
	
	public boolean callback(String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = "http://offer.adwo.com/iofferwallcharge/ia?adalias="+adalias+"&code="+code+"&idfa="+idfa+"&openudid="+openudid+"&activateip="+activateip+"&acts="+activatetime;
			String ret = NetManager.getInstance().getContent(url);
			logger.info("---------adwo return:" + ret);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
