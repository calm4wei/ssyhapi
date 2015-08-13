package cn.suishou.tuiguang;

import java.util.Random;

import cn.suishou.common.Config;

public class ReduceCallback {
	private static int callbackPercent = 9;//回调90%
	
	public static boolean isCallback(){
		boolean flag = true;   //是否回调   true为回调
		
		if(Config.isCutCallBack){
			//1~10 随机数
			int number = new Random().nextInt(10)+1;
			if(number > callbackPercent){
				flag = false;
			}else{
				flag = true;
			}
		}else{
			flag = true;   //不开启时都回调
		}
		
		return flag;
	}
}
