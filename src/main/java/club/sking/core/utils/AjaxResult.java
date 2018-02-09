package club.sking.core.utils;

import java.util.HashMap;

public class AjaxResult extends HashMap<String, Object>{
	private static final long serialVersionUID = 1L;

	/**
	 * 成功并返回信息
	 * @param successMsg
	 * @return
	 */
	public static AjaxResult successResult(String successMsg){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("msg", successMsg);
		return ajaxResult;
	}
	
	public static AjaxResult successResult(){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", true);
		ajaxResult.put("msg", "成功");
		return ajaxResult;
	}
	
	/**
	 * 错误并返回信息
	 * @param errorMsg
	 * @return
	 */
	public static AjaxResult errorResult(String errorMsg){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", false);
		ajaxResult.put("msg", errorMsg);
		return ajaxResult;
	}
	
	public static AjaxResult errorResult(){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", false);
		ajaxResult.put("msg", "失败");
		return ajaxResult;
	}
	
	/**
	 * 错误并返回信息
	 * @param errorMsg
	 * @return
	 */
	public static AjaxResult errorCode(String errorMsg,String errorCode){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", false);
		ajaxResult.put("msg", errorMsg);
		ajaxResult.put("errorCode", errorCode);
		return ajaxResult;
	}
	
	public static AjaxResult errorCode(String errorMsg,Integer errorCode){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("success", false);
		ajaxResult.put("msg", errorMsg);
		ajaxResult.put("errorCode", errorCode);
		return ajaxResult;
	}
	
}
