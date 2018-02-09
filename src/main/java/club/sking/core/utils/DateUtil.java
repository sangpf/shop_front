package club.sking.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static void main(String[] args) {
		Date currentDateByFormat = DateUtil.getCurrentDateByFormat("yyyy-MM-dd");
		System.out.println(currentDateByFormat);
	}
	
	/**
	 * 获取当前系统时间
	 * @param p_format
	 * @return
	 */
	public static Date getCurrentDateByFormat(String p_format){
		Date d = new Date();
		DateFormat sdf = new SimpleDateFormat(p_format);
		String dateStr = sdf.format(d).substring(0, 10);
		
		Date dd = null;
		try {
			dd = toUtilDateFromStrDateByFormat(dateStr, p_format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dd;
	}
    
	/**
	 * 将 字符串改为  日期
	 * @param p_strDate
	 * @param p_format
	 * @return
	 */
    public static Date toUtilDateFromStrDateByFormat(String p_strDate, String p_format){
    	Date l_date = null;
    	DateFormat df = new SimpleDateFormat(p_format);
    	if(StrUtils.isNotEmpty(p_strDate) && StrUtils.isNotEmpty(p_format)){
    		try {
				l_date = df.parse(p_strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
    	
    	return l_date;
    }
    
    
}
