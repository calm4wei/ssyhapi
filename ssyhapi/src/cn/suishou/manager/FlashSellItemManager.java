package cn.suishou.manager;

import cn.suishou.bean.FlashSellItem;
import cn.suishou.utils.StringUtil;

public class FlashSellItemManager {
	
	public static boolean isInFlashSellTime(FlashSellItem flashSellItem){		
		long startTimeMillis = StringUtil.datetime2long(flashSellItem.getStartTimestamp());
		long endTimeMillis = StringUtil.datetime2long(flashSellItem.getEndTimestamp());
		long now = System.currentTimeMillis();
		if(startTimeMillis<now && endTimeMillis>now){
			return true;
		}else{
			return false;
		}		
	}
	
	
}
