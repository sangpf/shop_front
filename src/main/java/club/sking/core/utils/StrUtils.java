package club.sking.core.utils;

public class StrUtils {
	
	/**
	 * 判断是否为null或空串  
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null){
			return true;
		}
		if(str.trim().equals("")){
			return true;	
		}
		return false;
	}
	/**
	 * 判断是否不为null或空串
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	// 将 string 转成 Integer
	public static Integer changeToInt(String str){
		
		Integer valueOf = null;
		if(isNotEmpty(str)){
			valueOf = Integer.valueOf(str.trim());
		}
		return valueOf;
	}
	
	/**
	 * 按分隔符将字符串组装为字符串数组
	 * 如果传入为null 或空串,直接放回空数组
	 * @param toSplit
	 * @param delimiter
	 * @return
	 */
	public static String[] split(String toSplit,String delimiter){
		if(isNotEmpty(toSplit)){
			return toSplit.split(delimiter);
		}
		return new String[]{};
	}
	
	
	
	
}
