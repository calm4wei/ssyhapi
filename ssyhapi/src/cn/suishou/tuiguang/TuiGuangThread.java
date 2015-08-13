package cn.suishou.tuiguang;

import java.sql.Timestamp;

import cn.suishou.utils.StringUtil;

public class TuiGuangThread implements Runnable{
	private String uid;
	private Timestamp activatetime;
	private ParamBean bean;
	private int from;//1:激活,2:注册,3:签到
	
	public TuiGuangThread(ParamBean bean,Timestamp activatetime,String uid,int from) {
		this.bean = bean;
		this.activatetime = activatetime;
		this.uid = uid;
		this.from = from;
	}
	
	public void run() {
		try{
			String adalias = bean.getAdalias(); 
			String idfa = bean.getIdfa();
			String activateip = bean.getActivateip();
			String code = bean.getCode();//APP版本
			String openudid = bean.getOpenudid();
			
			//多级反向代理造成获得多个IP，取第一个作为有用IP：114.253.210.114, 223.202.80.24
			if(activateip.indexOf(",")>-1){
				activateip = activateip.substring(0, activateip.indexOf(","));
			}
			
			if(idfa == null || "".equals(idfa)) {
				return ;
			}
			
			String device_token = TuiGuangDao.getInstance().getDeviceToken(idfa);
			
			if(device_token != null && !"".equals(device_token)){
				if(from == 1){
					activate(device_token,idfa,activatetime,adalias,activateip,code,openudid);
				}
				if(from == 2){
					register(device_token,idfa,activatetime,adalias,activateip,code,openudid,uid);
				}
				if(from == 3){
					sign(device_token,idfa,activatetime,adalias,activateip,code,openudid,uid);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//激活
	public void activate(String device_token,String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid){
		//获取设备ID
		String did = TuiGuangDao.getInstance().getDid(device_token);
		if(did != null && !"".equals(did)){
			//获取推广商ID
			String spid = TuiGuangDao.getInstance().getSpid(did);
			
			if(!TuiGuangDao.getInstance().isActivateExist(did, spid)){
				//保存激活信息
				TuiGuangDao.getInstance().saveActivateInfo(did, spid, activatetime,device_token);
				//更新总表时间
				TuiGuangDao.getInstance().updateCenterTable1(did, activatetime);
				//验证是否超时
				if(isOntime(did, activatetime)){
					//设置激活状态
					TuiGuangDao.getInstance().setStatus(TuiGuangDao.NOT_LOGIN, device_token,TuiGuangDao.INITIAL);
					//是否回调
					String callback = TuiGuangDao.getInstance().getIsCallback(spid);
					if(callback.equals("100") || callback.equals("101") || callback.equals("110") || callback.equals("111")){
						//应该回调，是否扣除
						isCallback(device_token,idfa,activatetime,adalias,activateip,code,openudid,did,spid);
					}
				}else{
					TuiGuangDao.getInstance().setStatus(TuiGuangDao.TIME_OUT, device_token,TuiGuangDao.INITIAL);
				}
			}
		}
	}
	
	//注册
	public void register(String device_token,String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid,String uid){
		//获取设备ID
		String did = TuiGuangDao.getInstance().getDid(device_token);
		if(did != null && !"".equals(did)){
			//获取推广商ID
			String spid = TuiGuangDao.getInstance().getSpid(did);
			
			if(!TuiGuangDao.getInstance().isRegisterExist(did, spid)){
				//保存注册信息
				TuiGuangDao.getInstance().saveRegisterInfo(did, spid, activatetime,uid,device_token);
				//更新总表时间
				TuiGuangDao.getInstance().updateCenterTable2(did, activatetime,uid);
				//检查是否没有拿到激活数据
				check(did, device_token, activatetime);
				//是否回调
				String callback = TuiGuangDao.getInstance().getIsCallback(spid);
				if(callback.equals("010") || callback.equals("011") || callback.equals("110") || callback.equals("111")){
					//应该回调，是否扣除
					isCallback(device_token,idfa,activatetime,adalias,activateip,code,openudid,did,spid);
				}
			}
		}
	}
	
	//签到
	public void sign(String device_token,String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid,String uid){
		//获取设备ID
		String did = TuiGuangDao.getInstance().getDid(device_token);
		if(did != null && !"".equals(did)){
			//获取推广商ID
			String spid = TuiGuangDao.getInstance().getSpid(did);
			
			if(!TuiGuangDao.getInstance().isSignExist(did, spid)){
				//保存签到信息
				if(TuiGuangDao.getInstance().saveSignInfo(did, spid, activatetime,uid,device_token)){
					//更新总表时间
					TuiGuangDao.getInstance().updateCenterTable3(did, activatetime,uid);
					//检查是否没有拿到激活数据
					check(did, device_token, activatetime);
					//是否回调
					String callback = TuiGuangDao.getInstance().getIsCallback(spid);
					if(callback.equals("001") || callback.equals("011") || callback.equals("101") || callback.equals("111")){
						//应该回调，是否扣除
						isCallback(device_token,idfa,activatetime,adalias,activateip,code,openudid,did,spid);
					}
				}
			}
		}
	}
	
	public void check(String did,String device_token,Timestamp activatetime){
		String status = TuiGuangDao.getInstance().getStatus(device_token);
		//激活数据丢失的情况处理
		if(status.equals("-1")){
			if(isOntime2(did, activatetime)){
				//设置激活状态
				TuiGuangDao.getInstance().setStatus(TuiGuangDao.NOT_LOGIN, device_token,TuiGuangDao.INITIAL);
			}else{
				TuiGuangDao.getInstance().setStatus(TuiGuangDao.TIME_OUT, device_token,TuiGuangDao.INITIAL);
			}
		}
	}
	
	//激活时判断是否超时
	public boolean isOntime(String did,Timestamp activatetime) {
		Timestamp createteTime = TuiGuangDao.getInstance().getCreateTime(did);
		if((StringUtil.timestamp2long(activatetime) >= StringUtil.timestamp2long(createteTime)) && (StringUtil.timestamp2long(activatetime)-StringUtil.timestamp2long(createteTime) <= 86400000*3)) {
			return true;
		} else {
			return false;
		}
	}
	
	//拿不到激活数据时，判断是否超时
	public boolean isOntime2(String did,Timestamp activatetime) {
		Timestamp updateTime = TuiGuangDao.getInstance().getUpdateTime(did);
		if((StringUtil.timestamp2long(activatetime) >= StringUtil.timestamp2long(updateTime)) && (StringUtil.timestamp2long(activatetime)-StringUtil.timestamp2long(updateTime) <= 86400000*3)) {
			return true;
		} else {
			return false;
		}
	}
	
	//保存回调信息
	public void saveInfo(boolean isSuccess,String did,String spid,String device_token,Timestamp time){
		if(isSuccess){
			TuiGuangDao.getInstance().saveCallbackInfo(did, spid, time,device_token);
			//更新总表时间
			TuiGuangDao.getInstance().updateCenterTable4(did, time);
		}else{
			//设置回调状态
			TuiGuangDao.getInstance().setStatus(TuiGuangDao.FAIL_CALLBACK, device_token,TuiGuangDao.DO_CALLBACK);
		}
	}
	
	//要不要扣除回调
	public void isCallback(String device_token,String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid,String did,String spid){
		if(ReduceCallback.isCallback()){
			//设置回调状态
			boolean isSuccess = TuiGuangDao.getInstance().setStatus(TuiGuangDao.DO_CALLBACK, device_token,TuiGuangDao.NOT_LOGIN);
			if(isSuccess){
				doCallback(did,spid,device_token,idfa, activatetime, adalias, activateip, code, openudid);
			}
		}else{
			//设置回调扣除状态
			TuiGuangDao.getInstance().setStatus(TuiGuangDao.NOT_CALLBACK, device_token,TuiGuangDao.NOT_LOGIN);
		}
	}
	
	//回调
	public void doCallback(String did,String spid,String device_token,String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid){
		boolean isSuccess = false;
		//安沃
		if(spid.equals("1000")){
			isSuccess = new AdWoCallback().callback(idfa, activatetime, adalias, activateip, code, openudid);
			saveInfo(isSuccess,did,spid,device_token,StringUtil.long2timestamp(System.currentTimeMillis()));
		}
		//米迪
		if(spid.equals("1001")){
			isSuccess = new MiDiCallback().callback(idfa, activatetime, adalias, activateip, code, openudid);
			saveInfo(isSuccess,did,spid,device_token,StringUtil.long2timestamp(System.currentTimeMillis()));
		}
		//点入
		if(spid.equals("1002")){
			isSuccess = new DianRuCallback().callback(idfa, activatetime, adalias, activateip, code, openudid);
			saveInfo(isSuccess,did,spid,device_token,StringUtil.long2timestamp(System.currentTimeMillis()));
		}
		//有米
		if(spid.equals("1003")){
			isSuccess = new YouMiCallback().callback(idfa, activatetime, adalias, activateip, code, openudid);
			saveInfo(isSuccess,did,spid,device_token,StringUtil.long2timestamp(System.currentTimeMillis()));
		}
		//多盟
		if(spid.equals("1004")){
			isSuccess = new DomobCallback().callback(idfa, activatetime, adalias, activateip, code, openudid);
			saveInfo(isSuccess,did,spid,device_token,StringUtil.long2timestamp(System.currentTimeMillis()));
		}
		//易积分
		if(spid.equals("1005")){
			isSuccess = new YiJiFenCallback().callback(idfa, activatetime, adalias, activateip, code, openudid);
			saveInfo(isSuccess,did,spid,device_token,StringUtil.long2timestamp(System.currentTimeMillis()));
		}
		//掌上互动
		if(spid.equals("1006")){
			isSuccess = new ZSHDCallback().callback(idfa, activatetime, adalias, activateip, code, openudid);
			saveInfo(isSuccess,did,spid,device_token,StringUtil.long2timestamp(System.currentTimeMillis()));
		}
	}
}
