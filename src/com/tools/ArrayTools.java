package com.tools;
/**
 * @projectName qipai_niuyouquan_common
 * @fileName ArrayTools.java
 * @description
 * @author lifl
 * @time 2017上午11:09:53
 *
 */

public class ArrayTools {

	/**
	 * 基于toString方法的数组转string
	 * @description
	 * @param ts
	 * @param split
	 * @return 
	 * String 
	 * @time 2017上午11:11:49
	 */
	public static<T> String arrayToString(T[] ts,String split){
		if (ts == null || ts.length ==0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(T t:ts){
			if (t != null) {
				sb.append(t.toString());
			}else{
				sb.append("null");
			}
			sb.append(split);
		}
		return sb.toString();
	}
}


