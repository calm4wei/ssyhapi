package cn.suishou.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;


public class DateUtil 
{
	private static Logger logger = Logger.getLogger(DateUtil.class);
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
	
	private static SimpleDateFormat ft1 = new SimpleDateFormat("M月dd日");
	
	private static SimpleDateFormat ftt = new SimpleDateFormat("yyyyMM");

	/** 年月 格式 */
    public static final String YEARMONTH = "yyyyMM";
	/** 年-月 格式 */
    public static final String YEAR_MONTH = "yyyy-MM";
    /** 年-月-日 格式:yyyy-MM-dd */
    public static final String DATE = "yyyy-MM-dd";
    /** 年月日 格式:yyyyMMdd */
    public static final String DATE_WITHOUT_SEPERATOR = "yyyyMMdd";
    /** 小时 格式 */
    public static final String HOUR_ONLY = "HH";
    /** 年月日小时 格式 */
    public static final String DATE_HOUR = "yyyyMMddHH";
    /** 年-月-日 小时 格式 */
    public static final String DATE_SPACE_HOUR = "yyyy-MM-dd HH";
    /** 年-月-日 小时:分钟 格式 */
    public static final String DATE_SPACE_MINUTE = "yyyy-MM-dd HH:mm";
    /** 年-月-日 小时:分钟:秒 格式 */
    public static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    /** 一秒的毫秒数 */
    public static int ONE_SECOND_MILLI_SECONDS = 1000;
    /** 一分钟的毫秒数 */
    public static int ONE_MINUTE_MILLI_SECONDS = 60 * 1000;
    /** 一小时的毫秒数 */
    public static int ONE_HOUR_MILLI_SECONDS = 60 * 60 * 1000;
    /** 一天的毫秒数 */
    public static int ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;
    /** 一天的秒数 */
    public static int ONE_DAY_SECONDS = 24 * 60 * 60;
    
	private static final int LAST_SECOND_OF_MINUTE = 59;
    private static final int LAST_MINUTE_OF_HOUR = 59;
    private static final int LAST_HOUR_OF_DAY = 23;
    
	/**
	 * 计算两个时间相隔天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long differ(Date date1, Date date2){
		long quot = 0;
		try {
			date1 = ft.parse(ft.format(date1));
			date2 = ft.parse(ft.format(date2));
			quot = date1.getTime() - date2.getTime();
			quot = Math.abs(quot);
		}catch (ParseException e){
			e.printStackTrace();
		}
		quot = quot/1000/60/60/24;
		return Math.abs(quot);
	}
	
	/**
	 * 获取date当天凌晨的时间，如2013-05-16 21:20:00得到2013-05-16 00:00:00
	 * @param date
	 * @return
	 */
	public static long getMidnight(Date date){
		long ret = 0;
		try {
			ret = ft.parse(ft.format(date)).getTime();
		}catch (ParseException e){
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 将时间 转化为中文时间
	 * @param time
	 * @return
	 */
	public static String changeCH(long time){
		String timeStr = "";
		long midnight = getMidnight(new Date());
		if(time > midnight){
			timeStr = "今天";
		}else if(time > midnight - 24*60*60*1000){
			timeStr = "昨天";
		}else{
			timeStr = ft1.format(new Date(time));
		}
		return timeStr;
	}
	
	/**
	 * 获取剩余时间
	 * @param time
	 * @return
	 */
	public static String getRestTimeCH(long time){
		long now =  System.currentTimeMillis();
		if(time <= now){
			return "";
		}
		long mn = time - now;
		long onedaymn = 24*60*60*1000;   //一天毫秒数
		long df = mn / onedaymn;
		if(df > 0){
			df ++;
			return df + "天";
		}else{
			long onehourmn = 60*60*1000;   //一个小时毫秒数
			long hour = mn / onehourmn;
			if(hour > 0){
				hour ++;
				return hour + "小时";
			}else{
				long onemiumn = 60*1000;
				long miu = mn / onemiumn;
				if(miu >0){
					miu ++;
					return miu + "分钟";
				}else{
					long m = mn / 1000;
					m ++;
					return m + "秒";
				}
			}
		}
	}

	
	public static String getRestTimeCH2(long time){
		long now =  System.currentTimeMillis();
		if(time <= now){
			return "";
		}
		long mn = (time - now)/1000;
		
		if(mn<=60*10){
			return mn/60+"分"+mn%60+"秒";
		}else{
			return getRestTimeCH(time);
		}
		
	}
	
	
	public static String getRestTimeCH3(long time){
		long now =  System.currentTimeMillis();
		if(time <= now){
			return "";
		}
		long mn = time - now;
		long onedaymn = 24*60*60*1000;   //一天毫秒数
		long df = mn / onedaymn;
		if(df > 0){
			df ++;
			return df + "天";
		}else{
			String str="";
			long onehourmn = 60*60*1000;   //一个小时毫秒数
			long hour = mn / onehourmn;
			if(hour > 0){
				str= hour + "小时";
			}
			long onemiumn = 60*1000;
			long miu = mn % onehourmn/onemiumn;
			if(miu >0){
				miu ++;
				str=str+ miu + "分钟";
			}
			return str;
		}
	}
	public static void main(String[] args) {
		System.out.println(getRestTimeCH3(Long.parseLong("1404792900000")));
	}
	
	/**
	 * 获取date当月第一天的时间，如2013-05-16 21:20:00得到2013-05-01 00:00:00
	 * @param date
	 * @return
	 */
	public static long getMonthFirst(Date date){
		long ret = 0;
		try {
			ret = ftt.parse(ftt.format(date)).getTime();
		}catch (ParseException e){
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 将毫秒的时间格式转换成 几月几日
	 * @param time
	 * @return
	 */
	public static String getMonthday(Long time){
		SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
		Date d=new Date(time);
		return sdf.format(d);
	}
	
	/**
	 * 将毫秒的时间格式转换成对应的月份
	 * @param time
	 * @return
	 */
	public static String getMonth(Long time){
		SimpleDateFormat sdf=new SimpleDateFormat("MM");
		Date d=new Date(time);
		return sdf.format(d)+"月";
		
	}

    /**
     * 以当期日期为基准，返回指定日期类型日期增量日期。
     * @param dateType 日期类型
     * @param count 增加数量，可以为负数
     * @return
     */
    public static Date getMagicDate(int dateType, int count)
    {
        Calendar calendar = new GregorianCalendar();
        Date todayTime = new Date();
        calendar.setTime(todayTime);
        calendar.add(dateType, count);
        return calendar.getTime();
    }

    /**
     * 返回指定日期类型日期增量日期
     * 
     * @param dateType 日期类型
     * @param count 增加数量，可以为负数
     * @param baseDate 参照日期
     * @return
     */
    public static Date getMagicDate(int dateType, int count, Date baseDate)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(baseDate);
        calendar.add(dateType, count);
        return calendar.getTime();
    }

    /**
     * 获取interval个月之后的日期，interval可以为负数，表示n个月之前的一个月
     * @param interval 间隔的月份，interval可以为负数，表示n个月之前的一个月
     * @return		   interval个月之后的日期
     */
    public static Date getMagicMonth(int interval)
    {
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) + interval));
    	Date date = calendar.getTime();
    	return date;
    }

    /**
     * 获取interval个月之后的月份，interval可以为负数，表示n个月之前的一个月
     * @param interval 间隔的月份，interval可以为负数，表示n个月之前的一个月
     * @return		   interval个月之后的月份
     */
    public static int getMagicYearMonth(int interval)
    {
    	Date date = getMagicMonth(interval);
    	String yearMonth = dateToString(date, YEARMONTH);
    	
    	return NumberUtils.isNumber(yearMonth) ? Integer.parseInt(yearMonth) : 0;
    }
    
    /**
     * 日期字符串转换为日期格式，转换出错时将返回null。
     * @param dateStr 日期字符串
     * @param dateFormat 日期格式
     * @return date对象
     */
    public static Date strToDate(String dateStr, String dateFormat)
    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date date = null;
        try 
        {
        	date = format.parse(dateStr);
		} 
        catch (ParseException e) 
		{
			logger.error(e, e);
		}
        return date;
    }

    /**
     * 将"yyyy-MM-dd"格式的日期字串转为时间戳
     * @param dateStr	"yyyy-MM-dd"格式的日期字串
     * @return			"yyyy-MM-dd"格式的日期字串对应的时间戳
     */
    public static long dateStr10ToStamp(String dateStr) 
    {
		Date date = strToDate(dateStr, DATE);
    	return date.getTime();
	}
    
    /**
     * 将long型时间戳转为java.sql.Timestamp
     * @param stamp	long型时间戳，如果<=0，则重置为当前时间戳
     * @return		java.sql.Timestamp
     */
    public static Timestamp stampToSQLTimestamp(long stamp) 
    {
    	if (stamp <= 0) {
			stamp = System.currentTimeMillis();
		}
    	Timestamp timestamp = new Timestamp(stamp);
    	return timestamp;
	}

    /**
     * 将java.sql.Timestamp转为Date
     * @param stamp	java.sql.Timestamp型时间戳，如果为null，则重置为当前时间
     * @return		java.util.Date
     */
    public static Date sqlTimestampToDate(Timestamp stamp) 
    {
    	if (stamp == null) {
    		stamp = new Timestamp(System.currentTimeMillis());
		}
    	Date stampDate = new Date(stamp.getTime());
    	return stampDate;
	}

    /**
     * 将java.sql.Timestamp转为字串（格式：yyyy-MM-dd HH:mm:ss）
     * @param stamp	java.sql.Timestamp型时间戳，如果为null，则重置为当前时间
     * @return		时间字串，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String sqlTimestampToStr(Timestamp stamp) 
    {
    	if (stamp == null) {
    		stamp = new Timestamp(System.currentTimeMillis());
		}
    	String dateStr = stampToSecondStr(stamp.getTime());
    	return dateStr;
	}

    /**
     * 将时间戳转为"yyyy-MM-dd"格式的日期
     * @param mill	时间戳
     * @return		"yyyy-MM-dd"格式的日期
     */
    public static String stampToDateStr(long mill) 
    {
    	Date date = new Date(mill);
    	String strs = "";
    	try 
    	{
    		SimpleDateFormat sdf = new SimpleDateFormat(DATE);
    		strs = sdf.format(date);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return strs;
    }

    /**
     * 将时间戳转为"yyyy-MM-dd HH"格式的小时
     * @param mill	时间戳
     * @return		"yyyy-MM-dd HH"格式的小时
     */
    public static String stampToHourStr(long mill) 
    {
    	Date date = new Date(mill);
    	String strs = "";
    	try 
    	{
    		SimpleDateFormat sdf = new SimpleDateFormat(DATE_SPACE_HOUR);
    		strs = sdf.format(date);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return strs;
    }

    /**
     * 将时间戳转为"yyyy-MM-dd HH:mm"格式的分钟
     * @param mill	时间戳
     * @return		"yyyy-MM-dd HH:mm"格式的分钟
     */
    public static String stampToMinuteStr(long mill) 
    {
    	Date date = new Date(mill);
    	String strs = "";
    	try 
    	{
    		SimpleDateFormat sdf = new SimpleDateFormat(DATE_SPACE_MINUTE);
    		strs = sdf.format(date);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return strs;
    }

    /**
     * 将时间戳转为"yyyy-MM-dd HH:mm:ss"格式的时间字串
     * @param mill	时间戳
     * @return		"yyyy-MM-dd HH:mm:ss"格式的时间字串
     */
    public static String stampToSecondStr(long mill) 
    {
    	Date date = new Date(mill);
    	String strs = "";
    	try 
    	{
    		SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP);
    		strs = sdf.format(date);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return strs;
    }
    
    /**
     * 获得当前日期与本周日相差的天数
     * 
     * @return 星期日是第一天，星期一是第二天......
     */
    public static int getMondayPlus()
    {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期一是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1)
        {
            return 0;
        }
        else
        {
            return 1 - dayOfWeek;
        }
    }

    /**
     * @brief 获取一个时间，这个时间是今天的第0秒
     * @return 时间
     */
    public static Date getTodayAtFirstSecond()
    {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * @brief 获取一个时间，这个时间是今天的最后一秒
     * @return 时间
     */
    public static Date getTodayAtLastSecond()
    {
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), LAST_HOUR_OF_DAY,
                LAST_MINUTE_OF_HOUR, LAST_SECOND_OF_MINUTE);
        return cal.getTime();
    }

    /**
     * @brief 获取一个时间戳，这个时间是今天的第0秒
     * @return 时间戳
     */
    public static long getTodayStampAtFirstSecond()
    {
    	Date todayStart = getTodayAtFirstSecond();
    	return todayStart.getTime();
    }

    /**
     * 获取本周六的日期
     * 
     * @return 本周六的日期
     */
    public static Date getSaturdayDate()
    {
        // 当前日历对象
        Calendar calendar = Calendar.getInstance();
        // 获取今天是一周中的哪一天，取值为1(周日)~7(周六)。
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 计算距离周六(星期最后一天)的天数
        int diff = Calendar.SATURDAY - dayOfWeek;
        // 如果今天就是周六，则返回今日日期。
        if (diff == 0)
        {
            return calendar.getTime();
        }
        // 获取本周六日期
        Date saturday = DateUtil.getMagicDate(Calendar.DAY_OF_YEAR, diff);
        return saturday;
    }

    /**
     * 获取指定日期所在周最后一天(周六)的日期
     * 
     * @param date 基准日期，将取该日期所在周最后一天(周六)的日期。
     * @return 指定日期所在周最后一天(周六)的日期
     */
    public static Date getSaturdayDate(Date date)
    {
        // 当前日历对象
        Calendar calendar = Calendar.getInstance();
        // 设置基准日期，将取该日期所在月份最后一天的日期。
        calendar.setTime(date);
        // 获取今天是一周中的哪一天，取值为1(周日)~7(周六)。
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 计算距离周六(星期最后一天)的天数
        int diff = Calendar.SATURDAY - dayOfWeek;
        // 如果今天就是周六，则返回今日日期。
        if (diff == 0)
        {
            return calendar.getTime();
        }
        // 获取本周六日期
        Date saturday = DateUtil.getMagicDate(Calendar.DAY_OF_YEAR, diff);
        return saturday;
    }

    /**
     * 获取本周一的日期，从0点0刻开始。
     * @return 本周一的日期，从0点0刻开始。
     */
    public static Date getThisMondayDate()
    {
        // 当前日历对象
        Calendar calendar = new GregorianCalendar();
        Date todayStart = getTodayAtFirstSecond();
        calendar.setTime(todayStart);
        // 获取今天是一周中的哪一天，取值为1(周日)~7(周六)。
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 计算今天距离周一的天数
        int diff = Calendar.MONDAY - dayOfWeek;
        // 如果今天就是周一，则返回今日日期。
        if (diff == 0)
        {
            return calendar.getTime();
        }
        // 如果今天是周末，则以今天为本周最后一天，取周一日期。
        if (diff == Calendar.MONDAY - Calendar.SUNDAY) {
			Date monday = DateUtil.getMagicDate(Calendar.DAY_OF_MONTH, -6, calendar.getTime());
			return monday;
		}
        // 获取本周一日期
        Date monday = DateUtil.getMagicDate(Calendar.DAY_OF_MONTH, diff, calendar.getTime());
        return monday;
    }

    /**
     * 获取本周一的日期时间戳，从0点0刻开始。
     * @return 本周一的日期时间戳，从0点0刻开始。
     */
    public static long getThisMondayDateStamp()
    {
        return getThisMondayDate().getTime();
    }

    /**
     * 获取上周一的日期，从0点0刻开始。
     * @return 上周一的日期，从0点0刻开始。
     */
    public static Date getLastMondayDate()
    {
        // 先获取本周一日期，然后计算得到上周一日期。
    	Date thisMonday = getThisMondayDate();
        Date lastmonday = DateUtil.getMagicDate(Calendar.DAY_OF_MONTH, -7, thisMonday);
        
        return lastmonday;
    }

    /**
     * 获取上周一的日期时间戳，从0点0刻开始。
     * @return 上周一的日期时间戳，从0点0刻开始。
     */
    public static long getLastMondayDateStamp()
    {
        return getLastMondayDate().getTime();
    }

    /**
     * 获取上个月的第一天，从0点0刻开始。
     * @return	上个月的第一天，从0点0刻开始。
     */
    public static Date getLastMonthFirstDay() 
    {
    	// 获取日历实例，月份为上个月(当前月份减1)，日期为1号，时分秒均为0。
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, -1);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	
    	Date lastDay = calendar.getTime();
    	return lastDay;
    }

    /**
     * 获取上个月的第一天时间戳，从0点0刻开始。
     * @return	上个月的第一天，从0点0刻开始。
     */
    public static long getLastMonthFirstDayStamp() 
    {
    	Date firstDay = getLastMonthFirstDay();
    	
    	return firstDay.getTime();
    }
    
    /**
     * 获取上个月的最后一天，时间为23:59:59。
     * @return	上个月的最后一天，时间为23:59:59。
     */
    public static Date getLastMonthLastDay() 
    {
    	// 获取日历实例，月份为上个月，日期为上月最后1天，时间为23:59:59。
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.DAY_OF_MONTH, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR_OF_DAY);
    	calendar.set(Calendar.MINUTE, LAST_MINUTE_OF_HOUR);
    	calendar.set(Calendar.SECOND, LAST_SECOND_OF_MINUTE);
    	
    	Date lastDay = calendar.getTime();
    	return lastDay;
    }

    /**
     * 获取上个月的最后一天时间戳，时间为23:59:59。
     * @return	上个月的最后一天，时间为23:59:59。
     */
    public static long getLastMonthLastDayStamp() 
    {
    	Date lastDay = getLastMonthLastDay();
    	
    	return lastDay.getTime();
    }

    /**
     * 日期转换为指定格式的字符
     * 
     * @param date 日期
     * @param dateFormat 日期格式
     * @return
     */
    public static String dateToString(Date date, String dateFormat)
    {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null)
        {
            df = new SimpleDateFormat(dateFormat);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    /**
     * 日期字符串转换为日期格式
     * 
     * @param dateStr 日期字符串
     * @param dateFormat 日期格式
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String dateStr, String dateFormat) throws ParseException
    {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        try
        {
            return sf.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            throw new ParseException("日期解析错误！", 0);
        }
    }

    /**
     * 将日期字串转为时间戳，日期字串为空返回0。
     * @param dateStr	日期字串
     * @return			日期字串对应时间戳
     */
    public static long strToStamp(String dateStr) {
		
    	// 如果日期字串为空，则返回0。
    	if (dateStr == null || "".equals(dateStr)) {
			return 0;
		}
    	Date date = null;
		try {
			date = stringToDate(dateStr, TIMESTAMP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return date==null?0:date.getTime();
	}
    
    /**
     * 获取上周日日期，时间为23:59:59。
     * @return	上周日日期，时间为23:59:59。
     */
    public static Date getLastSundayLastTime() {
    	// 获取日历实例，设置为上周日最后时刻。
		Calendar calendar = Calendar.getInstance();
		// 获取今日与周日的天数差，如果今日就是周日，则返回7天前日期。
		int diff = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
		if (diff == 0) {
			calendar.setTime(getMagicDate(Calendar.DAY_OF_MONTH, -7));
		}
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR_OF_DAY);
    	calendar.set(Calendar.MINUTE, LAST_MINUTE_OF_HOUR);
    	calendar.set(Calendar.SECOND, LAST_SECOND_OF_MINUTE);
    	
    	Date lastSunday = calendar.getTime();
    	return lastSunday;
	}

    /**
     * 获取上周日日期时间戳，时间为23:59:59。
     * @return	上周日日期时间戳，时间为23:59:59。
     */
    public static long getLastSundayLastTimestamp() {
    	
    	Date lastSunday = getLastSundayLastTime();
    	return lastSunday.getTime();
	}
    
    /**
     * 计算两个时间戳之间的天数差，end - start，结果可能为负数。
     * @param start	开始时间戳
     * @param end	结束时间戳
     * @return		两个时间戳之间的天数差
     */
    public static int diffDaysOfStamp(long start, long end) {
		
    	Long diffDays = (end - start)/(24 * 60 * 60 * 1000);
    	return diffDays.intValue();
	}
    
    /**
     * 传入Calendar Field(例Calendar.MINUTE)和数值num，判断是否大于当前时刻的对应数值。
     * @param calenderField	Calendar Field(例Calendar.MINUTE)
     * @param num			Calendar Field数值
     * @return				是否大于当前时刻的对应数值
     */
    public static boolean isSpecifiedFieldBigger(int calendarField, int num) {
    	// 获取日历实例，设置为上周日最后时刻。
    	Calendar calendar = Calendar.getInstance();
    	int specifiedNum = calendar.get(calendarField);
    	
    	return specifiedNum > num;
	}
    
    /**
     * 获取当前小时字串，格式为“yyyyMMddHH”。
     * @return	当前小时字串，格式为“yyyyMMddHH”。
     */
    public static String getCurHourStr() {
    	String hourStr = DateUtil.dateToString(new Date(), DATE_HOUR);
    	return hourStr;
	}

    /**
     * 获取当前小时字串，格式为“HH”。
     * @return	当前小时字串，格式为“HH”。
     */
    public static String getCurHourOnly() {
    	String hourStr = DateUtil.dateToString(new Date(), HOUR_ONLY);
    	return hourStr;
	}

    /** 获取当前的日期数 */
    public static int getCurDayInt() {
    	Calendar calendar = Calendar.getInstance();
    	int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
    	return dayNum;
	}

    /** 获取当前的小时数，24小时制 */
    public static int getCurHourInt() {
    	Calendar calendar = Calendar.getInstance();
    	int hourNum = calendar.get(Calendar.HOUR_OF_DAY);
    	return hourNum;
	}

    /**
     * 获取指定日期的小时字串，格式为“yyyyMMddHH”。
     * @return	如果date为空，返回null；<br>
     * 			否则返回指定日期的小时字串，格式为“yyyyMMddHH”。
     */
    public static String getDateHourStr(Date date) {
    	if (date == null) {
			return null;
		}
    	String hourStr = DateUtil.dateToString(date, DATE_HOUR);
    	return hourStr;
	}

    /**
     * 获取本月的字串表示，格式为：“yyyy-MM”。
     * @return	本月的字串表示，格式为：“yyyy-MM”。
     */
    public static String getCurMonthStr()
    {
    	String curMonthStr = dateToString(new Date(),"yyyy-MM");
    	return curMonthStr;
    }

    /**
     * 获取本月的字串表示，格式为：“yyyyMM”。
     * @return	本月的字串表示，格式为：“yyyyMM”。
     */
    public static String getCurMonthStr6()
    {
    	String curMonthStr = dateToString(new Date(),"yyyyMM");
    	return curMonthStr;
    }

    /**
     * 获取昨天的日期，时分秒与此刻相同。
     * @return	昨天的日期，时分秒与此刻相同。
     */
    public static Date getYestodayDate() {
		Date yestoday = getMagicDate(Calendar.DAY_OF_MONTH, -1);
		return yestoday;
	}
    
    /**
     * 获取昨天的日期字串，格式为“yyyyMMdd”。
     * @return	昨天的日期字串，格式为“yyyyMMdd”。
     */
    public static String getYestodayDateStr() {
    	Date yestoday = getYestodayDate();
    	String yestodayStr = dateToString(yestoday, DATE_WITHOUT_SEPERATOR);
    	return yestodayStr;
    }

    /**
     * 获取昨天所在月份的字串表示，格式为：“yyyyMM”。
     * @return	昨天所在月份的字串表示，格式为：“yyyyMM”。
     */
    public static String getYestodayMonth6() 
    {
    	Date yestoday = getYestodayDate();
    	String monthStr = dateToString(yestoday, YEARMONTH);
    	return monthStr;
    }
    
    /**
     * 获取昨天最后一秒的日期，即yyyy-MM-dd 23:59:59.
     * @return	昨天最后一秒的日期，即yyyy-MM-dd 23:59:59.
     */
    public static Date getYestodayLastTime() 
    {
    	// 获取昨天的日期，时分秒与此刻相同。
    	Date yestoday = getYestodayDate();
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(yestoday);
    	// 时间设置为23:59:59
    	calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR_OF_DAY);
    	calendar.set(Calendar.MINUTE, LAST_MINUTE_OF_HOUR);
    	calendar.set(Calendar.SECOND, LAST_SECOND_OF_MINUTE);
    	Date yestodayLastTime = calendar.getTime();
    	
    	return yestodayLastTime;
	}

    /**
     * 获取昨天最后一秒的时间戳，即yyyy-MM-dd 23:59:59.
     * @return	昨天最后一秒的时间戳，即yyyy-MM-dd 23:59:59.
     */
    public static long getYestodayLastTimestamp() 
    {
    	Date yestodayLastTime = getYestodayLastTime();
    	return yestodayLastTime.getTime();
    }

	/**
	 * 计算当前时间距离明天凌晨0点30分的秒数
	 * @return	当前时间距离明天凌晨0点30分的秒数
	 */
	public static int todayDiffTomorrow_ZeroThirty() 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 1, 0, 30, 0);
		int seconds = (int) (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000;
		
		return seconds;
	}

	/**
	 * 计算当前时间距离后天凌晨0点30分的秒数
	 * @return	当前时间距离后天凌晨0点30分的秒数
	 */
	public static int getTwoDaysLater_ZeroThirty() 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 2, 0, 30, 0);
		int seconds = (int) (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000;
		
		return seconds;
	}
	
	/**
	 * 将"yyyyMMdd"格式的日期字串转为"yyyy-MM-dd HH:mm:ss"格式的时间字串，分秒数为0。
	 * @param hourStr	"yyyyMMdd"格式的日期字串
	 * @return			"yyyy-MM-dd HH:mm:ss"格式的时间字串
	 */
	public static String dateStr8ToSecondStr(String dateStr) 
	{
		if (StringUtils.isBlank(dateStr) || dateStr.length() != "yyyyMMdd".length()) 
		{
			return null;
		}
		String secondStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8) + " 00:00:00";
		return secondStr;
	}

	/**
	 * 将"yyyyMMdd"格式的日期字串转为"yyyy-MM-dd HH:mm:ss"格式的时间日期，分秒数为0。
	 * @param hourStr	"yyyyMMdd"格式的日期
	 * @return			"yyyy-MM-dd HH:mm:ss"格式的时间日期
	 */
	public static Date dateStr8ToSecondDate(String dateStr) 
	{
		String secondStr = dateStr8ToSecondStr(dateStr);
		Date date = strToDate(secondStr, TIMESTAMP);
		return date;
	}

	/**
	 * 将"yyyyMMdd"格式的日期字串转为"yyyy-MM-dd HH:mm:ss"格式的时间戳，分秒数为0。
	 * @param hourStr	"yyyyMMdd"格式的时间戳
	 * @return			"yyyy-MM-dd HH:mm:ss"格式的时间戳
	 */
	public static long dateStr8ToSecondStamp(String dateStr) 
	{
		Date date = dateStr8ToSecondDate(dateStr);
		long dateStamp = date.getTime();
		return dateStamp;
	}
	
	/**
	 * 将"yyyyMMddHH"格式的小时字串转为"yyyy-MM-dd HH:mm:ss"格式的时间字串，分秒数为0。
	 * @param hourStr	"yyyyMMddHH"格式的小时字串
	 * @return			"yyyy-MM-dd HH:mm:ss"格式的时间字串
	 */
	public static String hourStr10ToSecondStr(String hourStr) 
	{
		if (StringUtils.isBlank(hourStr) || hourStr.length() != "yyyyMMddHH".length()) 
		{
			return null;
		}
		String secondStr = hourStr.substring(0, 4) + "-" + hourStr.substring(4, 6) + "-" + hourStr.substring(6, 8) + " " + hourStr.substring(8, 10) + ":00:00";
		return secondStr;
	}

	/**
	 * 将"yyyyMMddHH"格式的小时字串转为"yyyy-MM-dd HH:mm:ss"格式的时间值，分秒数为0。
	 * @param hourStr	"yyyyMMddHH"格式的小时字串
	 * @return			"yyyy-MM-dd HH:mm:ss"格式的时间值
	 */
	public static Date hourStr10ToSecondDate(String hourStr) 
	{
		String secondStr = hourStr10ToSecondStr(hourStr);
		Date hourDate = strToDate(secondStr, TIMESTAMP);
		return hourDate;
	}

	/**
	 * 将"yyyyMMddHH"格式的小时字串转为"yyyy-MM-dd HH:mm:ss"格式的时间戳，分秒数为0。
	 * @param hourStr	"yyyyMMddHH"格式的小时字串
	 * @return			"yyyy-MM-dd HH:mm:ss"格式的时间戳
	 */
	public static long hourStr10ToSecondStamp(String hourStr) 
	{
		// 先获取当前小时字串对应的日期对象，然后转为时间戳。
		Date hourDate = hourStr10ToSecondDate(hourStr);
		long hourStamp = hourDate.getTime();
		return hourStamp;
	}

	/**
	 * 将"yyyy-MM-dd HH:mm:ss"格式的日期字串转为"yyyy-MM-dd HH:mm"格式的分钟字串。
	 * @param dateStr	"yyyy-MM-dd HH:mm:ss"格式的日期字串
	 * @return			"yyyy-MM-dd HH:mm"格式的分钟字串
	 */
	public static String dateStr19To16(String dateStr) {
		if (StringUtils.isBlank(dateStr) || dateStr.length() != TIMESTAMP.length()) {
			return null;
		}
		String dayStr = dateStr.substring(0, 16);
		return dayStr;
	}
	
	/**
	 * 获取今天的时间字串，格式："yyyy-MM-dd HH:mm:ss"
	 * @return	今天的时间字串，格式："yyyy-MM-dd HH:mm:ss"
	 */
	public static String getTodayStr19() 
	{
		String todayStr19 = dateToString(new Date(), TIMESTAMP);
		return todayStr19;
	}
	
	/**
	 * 将"yyyyMMdd"格式的日期字串转为"yyyy-MM-dd"格式的日期字串。
	 * @param dateStr	"yyyyMMdd"格式的日期字串
	 * @return			"yyyy-MM-dd"格式的日期字串
	 */
	public static String dateStr8To10(String dateStr) {
		if (StringUtils.isBlank(dateStr) || dateStr.length() != "yyyyMMdd".length()) {
			return null;
		}
		String dayStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8);
		return dayStr;
	}
	
	/**
	 * 将"yyyyMMddHH"格式的小时字串转为日期整数，返回整数值：yyyyMMdd。
	 * @param dateHourStr	"yyyyMMddHH"格式的小时字串
	 * @return				整数值：yyyyMMdd
	 */
	public static int hourStr10ToDateInt(String dateHourStr) 
	{
		// 如果小时字串为空，或长度不等于"yyyyMMddHH"，或不能转为整数，则返回0.
		if (StringUtils.isBlank(dateHourStr)) {
			return 0;
		}
		if (dateHourStr.length() != DATE_HOUR.length() || !StringUtils.isNumeric(dateHourStr)) {
			return 0;
		}
		String dateStr = dateHourStr.substring(0, DATE_WITHOUT_SEPERATOR.length());
		return Integer.parseInt(dateStr);
	}
	
	/**
	 * 获取"yyyy-MM-dd"格式的日期与现在的年份之差，如果dateStr为空则返回-1
	 * @param dateStr	"yyyy-MM-dd"格式的日期
	 * @return			"yyyy-MM-dd"格式的日期与现在的年份之差
	 */
	public static int diffYearOfDateStr(String dateStr) 
	{
		if (StringUtils.isBlank(dateStr)) {
			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		
		calendar.setTime(strToDate(dateStr, DATE));
		int thatYear = calendar.get(Calendar.YEAR);
		return thisYear - thatYear;
	}
	
}
