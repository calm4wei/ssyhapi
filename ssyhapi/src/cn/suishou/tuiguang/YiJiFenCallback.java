package cn.suishou.tuiguang;

import java.net.URLDecoder;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import cn.suishou.utils.NetManager;

public class YiJiFenCallback implements TuiGuang{
	private static Logger logger = Logger.getLogger(YiJiFenCallback.class);
	
	public boolean callback(String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid){
		boolean flag = false;
		try{
			String url = TuiGuangDao.getInstance().getCallback(idfa);
			String ret = NetManager.getInstance().getContent(URLDecoder.decode(url,"UTF-8"));			
			logger.info("---------yijifen return:" + ret);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
}
