package com.tools;

import java.util.Map;
import java.util.Set;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName MapTools.java
 * @description
 * @author lifl
 * @time 2017下午3:56:18
 *
 */

public class MapTools {

	public static<K,V> String mapToString(Map<K, V> map,String split,String eql){
		if (map == null) {
			return "";
		}
		Set<K> keys = map.keySet();
		StringBuilder sb = new StringBuilder();
		for(K key:keys){
			sb.append(key.toString());
			sb.append(eql);
			sb.append(map.get(key));
			sb.append(split);
		}
		return sb.toString();
	}
}


