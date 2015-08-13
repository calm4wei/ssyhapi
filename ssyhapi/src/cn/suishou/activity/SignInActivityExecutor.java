package cn.suishou.activity;

import cn.suishou.manager.SignInManager;

/**
 * 签到活动执行者
 */
public class SignInActivityExecutor extends ActivityExecutor{
	
	private static final int LIMIT_JF_NUM = 30;   //最多领取集分宝数量
	
	@Override
	public void isException() {
		super.isException();
		if(SignInManager.getInstance().isLimitToday(request.getUid()) || request.getJfNum() > LIMIT_JF_NUM){
	//		ActivityExceptionManager.getInstance().addException(request, activity);
		}
	}
	
}
