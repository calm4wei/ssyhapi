package cn.suishou.tuiguang;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import cn.suishou.utils.NetManager;

public class ZSHDCallback implements TuiGuang{
	private static Logger logger = Logger.getLogger(ZSHDCallback.class);
	
	public boolean callback(String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getCallback(idfa);
			if(url != null && !"".equals(url)){
				String ret = NetManager.getInstance().getContent(url);				
				logger.info("---------ZSHD return:" + ret);
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
