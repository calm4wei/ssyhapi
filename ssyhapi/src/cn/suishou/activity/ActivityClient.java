package cn.suishou.activity;

public class ActivityClient {
	public static int execut(ActivityRequest request) throws Exception{
		String key = request.getActivityKey();
		ActivityExecutor executor = ActivityFactory.getExecutor(key);
		return executor.execut(request);
	}
}