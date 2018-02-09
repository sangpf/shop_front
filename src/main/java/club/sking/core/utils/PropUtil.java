package club.sking.core.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropUtil {

	public static String getValue_from_propties(String source,String key){
		
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(source));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String keyStr = (String) props.get(key);
		
		return keyStr;
	}
	
	/**
	 * 读取配置文件,返回字符串
	 * @return
	 */
	public static String readPropties(String propName,String strKey){
		
		ResourceBundle bundle = ResourceBundle.getBundle(propName);
		String urlStr = bundle.getString(strKey);
		return urlStr;
	}
	
	public static void main(String[] args) {
		String readPropties = readPropties("configure","niSurveyQuestionnaireUrl");
		System.out.println(readPropties);
	}
	
}
