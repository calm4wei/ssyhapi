package cn.suishou.common;

import cn.youhui.log4ssy.utils.Enums.Debug;

public interface Config {
	public static boolean release = false; // true 为正式
	public static boolean isCutCallBack = true;  //是否开启扣除推广回调量 ，true为开启
	
	public static String db_master = release ? "master" : "test";
	public static String db_slave = release ? "slave" : "test";	
	
	public static String GLB_REDIS_HOST	 = release ? "10.10.33.215" : "10.0.0.21";
	public static int GLB_REDIS_PORT = 6379 ;
	
	public static String AES_PWD = "suishou774999810";
	
	public static String old_version_url = release ? "http://v2.api.njnetting.cn" : "http://10.0.0.21/tyhapi";
//	public static String old_version_url = release ? "http://v2.api.njnetting.cn" : "http://127.0.0.1:8080/TYHServerV5";  //本地测试
	
	public static Debug debug = release ? Debug.FALSE : Debug.TRUE;
	
	//public static String IMG_CENTER_URL = release ? "http://10.10.61.223:8080/imgcenter" : "http://10.0.0.21:8080/imgcenter";
	public static String IMG_CENTER_URL = release ? "http://10.10.61.223:8080/imgcenter" : "http://service.suishouyouhui.com/imgcenter";
	
	public static String serverHost = "http://api.suishouyouhui.com";
	public static boolean isNeedSaveItem = true; //是否保存商品跳转的记录
	
	public static String suggestSystemURL = release?"http://10.10.61.223:8080/suggest" : "http://10.0.0.21:8080/suggest";
	
	public static String trailerImg = release?"http://api.suishouyouhui.com/ssyhapi/img/" : "http://api.suishouyouhui.com/ssyhapi/img/";//1.png(4s);2.png(5,5s);3.png(6);4.png(6plus);
	
}
