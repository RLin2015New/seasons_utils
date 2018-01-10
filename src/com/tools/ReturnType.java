package com.tools;


/**
 * @projectName qipai_niuyouba_login
 * @fileName ReturnType.java
 * @description
 * @author lifl
 * @time 2017下午3:26:58
 *
 */

public class ReturnType {

	public static String success = "000000";

	public ReturnType(){
		
	}
	
	public static ReturnType querySuccess(String msg) {
		return new ReturnType(ReturnType.success, msg);
	}

	public static ReturnType querySuccess(Object msg) {
		return new ReturnType(ReturnType.success,
				NyTools.writeValueAsString(msg));
	}

	public static ReturnType queryIllegalParams(String e) {
		return new ReturnType(ErrorCode.code_common_illegalParams, e);
	}

	public static ReturnType queryDataBaseError(String msg) {
		return new ReturnType(ErrorCode.code_common_databaseErr, msg);
	}
	
	public static ReturnType queryUnknowError(String msg){
		return new ReturnType(ErrorCode.code_common_unknowErr,msg);
	}

	public boolean success() {
		if (success.equals(statusCode)) {
			return true;
		}
		return false;
	}

	public ReturnType(String code, String msg) {
		this.statusCode = code;
		this.statusMsg = msg;
	}

	public ReturnType(ErrorCode ec, Object... o) {
		this.statusCode = ec.getCode() + "";
		this.statusMsg = ec.getMsg(o);
	}

	public String toJsonString() {
		return NyTools.writeValueAsString(this);
	}
	/**
	 * TODO 验证是否会造成问题
	 */
	public String toString(){
		return NyTools.writeValueAsString(this);
	}

	private String statusCode;
	private String statusMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

}
