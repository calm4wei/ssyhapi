package cn.suishou.common;

public interface X {
	
	String DOT = "." ;	
	
	interface CachePrefix {
		String IphoneDevTokenCacherkey = "ssyh_iphone.devtoken"; 
		String AllIphoneDevTokenCacherkey = "ssyh_iphone.alldevtoken";
		
		String CACHE_STATUS = "cache.status";
		
		String SMS_RANDOM_NUM = "ssyh_sms.randomnum.";
		String FAVORITE_CACHE = "ssyh_favorite";		
		
		String TAG_TO_ITEM = "ssyh_tags.tag2item";		
		String FLASH_SELL_ITEM_CACHE = "ssyh_flashsell.items";
		
		String JF_ITEM = "ssyh_sign_items";
		
		String TAG_ALL_TAGS = "ssyh_items.tags";
		String TAG_TO_TAG = "ssyh_tags.parent2children";		
		
		String ITEM_CACHE_TAOBAO = "ssyh_add.items";
		String ITEM_CACHE_SELF = "cn.suishou.ssyh_merchant.item";		
		String ITEM_CACHE_VIP = "ssyh_tags.vippids.detail";
		
		String ITEM_SOLD = "ssyh_sold_num";
		String NOTIFY_FLASH_SELL_NUM = "ssyh_appointment_num";
		
		String ITEM_PURCHASE_LIMITED = "ssyh_limit_num";
		String USR_PURCHASE_ITEM = "ssyh_user_purchase_item";
		
		String HOME_PAGE_AD = "ssyh_advertise";
		String HOME_PAGE_BANNER = "ssyh_model";
		
		String NOTIFY_FLASH_SELL_ITEM_CACHE = "ssyh_notify_flashsell_items";
		String NOTIFY_SUPER_DISCOUNT_ITEM_CACHE = "ssyh_notify_super_discount_items";
		String NOTIFY_SUPER_DISCOUNT_ITEM_INFO_CACHE = "ssyh_notify_super_discount_item_info";

		String PUSH_MESSAGE = "ssyh_android_pushmessage";
		/** 用户收货地址Cache Key */
		String CACHE_USER_ADDRESS = "ssyh_user_address_zset.";
		/** 用户月度活跃时长统计Cache Key */
		String CACHE_MAU_STATS = "ssyh_mau_stats_hash.";
		/** 意见反馈/客服回复Cache Key，以id为score。用户的反馈，与其收到的回复放一起，按id排列 */
		String CACHE_USER_FEEDBACK = "ssyh_feedback_zset.";
	}

}
