package cn.suishou.activity;

import cn.suishou.bean.Activity;
import cn.suishou.manager.ActivityJoinManager;
import cn.suishou.manager.ActivityManager;

/**
 * 活动执行者父类
 */
public class ActivityExecutor {
	
	Activity activity = null;
	ActivityRequest request = null;
	
	public int execut(ActivityRequest request){
		if(request == null){
			return ActivityConfig.ACTIVITY_EXCEPTION;
		}
		this.request = request;
		this.activity = ActivityManager.getInstance().getActivityByKey(request.getActivityKey());
		int ret = check();
		if(ActivityConfig.ACTIVITY_CHECK_PASS == ret){
			ret = join();
		}
		return ret;
	}
	
	public int check(){
		return ActivityManager.getInstance().joinActivity(request.getActivityKey(), request.getUid());
	}
	
	public int join(){
		if(activity.getJfNum() == 0){
			activity.setJfNum(request.getJfNum());
		}
		int r = ActivityJoinManager.getInstance().join(activity, request.getUid(), request.getRunningDays(), request.getActivityUniqueId());
		if(r == 1){
			return ActivityConfig.ACTIVITY_JOIN_SUCCESS; 
		}else if(r == 2){
			return ActivityConfig.ACTIVITY_UNIQUEID_EXSIT;
		}else{
			return ActivityConfig.ACTIVITY_SERVER_ERROR;
		}
	}
	
	public void isException(){}
}
