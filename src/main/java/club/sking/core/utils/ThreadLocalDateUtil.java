package club.sking.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDateUtil {
	 private static final String date_format = "dd/MM/yyyy";
	    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
	  
	    public static DateFormat getDateFormat()
	    { 
	        DateFormat df = threadLocal.get(); 
	        if(df==null){ 
	            df = new SimpleDateFormat(date_format); 
	            threadLocal.set(df); 
	        } 
	        return df; 
	    }
	 
	    public static String formatDate(Date date) throws ParseException {
	        return getDateFormat().format(date);
	    }
	 
	    public static Date parse(String strDate) throws ParseException {
	        return getDateFormat().parse(strDate);
	    }   
	    
}
