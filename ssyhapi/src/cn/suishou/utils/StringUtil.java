package cn.suishou.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public class StringUtil {
	private static Logger logger = Logger.getLogger(StringUtil.class);
	
    public static String generateTradeId(){    
    	DateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
    	String timestamp = System.currentTimeMillis()+"";
    	timestamp = timestamp.substring(timestamp.length()-8, timestamp.length());
    	return format.format(new Date()) + timestamp;   
    }
    
	public static boolean checkPass(String pass){
		if(pass == null || "".equals(pass)){
			return false;
		}
		if(pass.length() > 16 || pass.length() < 6){
			return false;
		}
		return pass.matches("[0-9a-zA-Z]*");
	}
	
	public static long timestamp2long(Timestamp timestamp){
		if (timestamp == null) {
			return 0;
		}else{
			return timestamp.getTime();
		}
	}
	
	public static Timestamp long2timestamp(long timeMillis){		
		if (timeMillis == 0) {
			return new Timestamp(System.currentTimeMillis());
		}else{
			return new Timestamp(timeMillis);
		}
	}	
	
	public static long datetime2long(String datatime){
		try {
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			return sdf.parse(datatime).getTime();
		} catch (ParseException e) {
			logger.error("error stack",e);
			return 0;
		}
	}
	
	public static String long2datetime(long timeMillis){
		Date date = new Date(timeMillis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static Timestamp datetime2timestamp(String datatime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return new Timestamp(sdf.parse(datatime).getTime());
		} catch (ParseException e) {
			return new Timestamp(System.currentTimeMillis());
		}		
	}
	
	public static String timestamp2datetime(Timestamp timestamp){
		Date date = new Date(timestamp.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	
    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     * 
     * @param value 待检查的字符串
     * @return true/false
     */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		char[] chars = obj.toString().toCharArray();
		int length = chars.length;
		if(length < 1)
			return false;
		
		int i = 0;
		if(length > 1 && chars[0] == '-')
			i = 1;
		
		for (; i < length; i++) {
			if (!Character.isDigit(chars[i])) {
				return false;
			}
		}
		return true;
	}

    /**
     * 检查指定的字符串列表是否不为空。
     */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}

    /**
     * 过滤不可见字符
     */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD)
					|| ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
					|| ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}
	
	public static String getSaftyFromGson(String gstr, String key, String defaultValue){		
		try{
			return JSONObject.fromObject(gstr).getString("taobaoNick");
		}catch(Exception e){
			return defaultValue;
		}
		
	}
	
	public static String getSaftStringFromRequest(HttpServletRequest req, String key, String defaultString) {
		String returnString = req.getParameter(key);
		if (returnString != null && !"".equals(returnString)){
			return returnString;
		}else{
			return defaultString;
		}
	}
	
	public static boolean checkDateFormat(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date ndate = format.parse(dateTime);
            String str = format.format(ndate);          
            if (str.equals(dateTime))
                return true;	       
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();	        
            return false;
        }
	}
	
	public static boolean checkDateFormat(String value, String dateformat) {
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
        try {
            Date ndate = format.parse(value);
            String str = format.format(ndate);          
            if (str.equals(value))
                return true;	       
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();	        
            return false;
        }
	}
	
    public static void main(String[] args) 	{
    	System.out.println(generateTradeId()); 
    }

}
