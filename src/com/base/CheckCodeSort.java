package com.base;

import java.util.Collections;
import java.util.List;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName CheckCodeSort.java
 * @description 加密排序算法
 * @author lifl
 * @time 2017下午1:38:43
 *
 */

public class CheckCodeSort {
	
	
	public static void sort(String type,List<String> list){
		if ("1".equals(type)) {
			sort01(list);
		}else if("2".equals(type)){
			sort02(list);
		}
	}
	
	/**
	 * 
	 * @description 正序
	 * @param list 
	 * void 
	 * @time 2017下午1:41:07
	 */
	private static void sort01(List<String> list){
		Collections.sort(list);
	}
	
	/**
	 * 
	 * @description 反序
	 * @param list 
	 * void 
	 * @time 2017下午1:41:17
	 */
	private static void sort02(List<String>list){
		Collections.sort(list, Collections.reverseOrder());
	}
	
}


