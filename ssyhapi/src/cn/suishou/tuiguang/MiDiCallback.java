package cn.suishou.tuiguang;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import cn.suishou.utils.NetManager;

public class MiDiCallback implements TuiGuang{
	private static Logger logger = Logger.getLogger(MiDiCallback.class);
	
	public boolean callback(String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getMiDiCallback(idfa);
			String ret = NetManager.getInstance().getContent(url);			
			logger.info("---------midi return:" + ret);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
