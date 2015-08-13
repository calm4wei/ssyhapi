package cn.suishou.common;

public interface Value {
	
	public static int trade_mx_status_success = 1;
	public static int trade_mx_status_fail = 2;
	
	public static int trade_mx_type_shift = 1;
	public static int trade_mx_type_ac = 2;
	public static int trade_mx_type_fl = 3;
	public static int trade_mx_type_pay = 4;
	
	public static int item_channel_taobao = 0;
	public static int item_channel_self = 1;
	public static int item_channel_vip = 2;
	
	public static String trade_mx_type_shift_desc = "老版本集分宝迁移";

	public static String home_page_banner_key_chaojihui="super_disc";
	public static String home_page_banner_key_baicaihui="baicaijia";
	public static String home_page_banner_key_xianchanghui="xianchang";
	public static String home_page_banner_key_catagory="menu";
	
	public static int order_status_unpaid = 1;
	public static int order_status_paid = 2;
	public static int order_status_deliver = 3;
	public static int order_status_received = 4;
	public static int order_status_refund = 5;
	
	public static int order_credit_pay_status_success = 1;
	public static int order_credit_pay_status_fail = 2;
	
	public static int order_return_status_unreceived = 1;
	public static int order_return_status_received = 2;
	
	public static int order_client_unshown = 0;
	public static int order_client_shown = 1;	
	
	public static int order_pay_platform_ali = 1;
	public static int order_pay_platform_wx = 2;
	
	public static int order_channel_itemlist = 0; //商品列表
	public static int order_channel_orderlist = 1; //订单列表

	public static int page_size = 2;
	
	public static int super_discount_start_time = 10;
	
	public static int item_validte_status_normal = 0;
	public static int item_validte_status_no_on_time = 1;
	public static int item_validte_status_stock_not_enought = 2;
	public static int item_validte_status_unsale = 3;
	public static int item_validte_status_price_changed = 4;
	
	public static String tagStyleWeb = "tagStyleWeb";
	public static String tagStyleAppPage = "tagStyleAppPage";
	
	public static int returnOrderEnableDays = 17;
	
	public static int PAY_MODE_WEIXIN = 0;
	public static int PAY_MODE_ZHIFUBAO = 1;
}
