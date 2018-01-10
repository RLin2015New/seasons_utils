package com.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @projectName qipai_niuyouba_game
 * @fileName ListOrder.java
 * @description
 * @author lifl
 * @time 2017下午4:39:20
 *
 */

public class ListTools {

	public static void ascList(List<Integer> list) {
		Collections.sort(list);
	}

	public static void descList(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}

		});
	}

	public static <T> String listToString(List<T> list, String f) {
		if (list == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (T l : list) {
			if (sb.length() > 0) {
				sb.append(f);
			}
			sb.append(l.toString());
		}
		return sb.toString();
	}

	public static List<Integer> stringToIntegerList(String str, String f) {
		if (str.length() == 0) {
			return new ArrayList<Integer>();
		}
		List<Integer> list = new ArrayList<Integer>();
		String[] ss = str.split(f);
		for (String s : ss) {
			list.add(Integer.parseInt(s));
		}
		return list;
	}

	public static List<Long> stringToLongList(String str, String f) {
		if (str.length() == 0) {
			return new ArrayList<Long>();
		}
		List<Long> list = new ArrayList<Long>();
		String[] ss = str.split(f);
		for (String s : ss) {
			list.add(Long.parseLong(s));
		}
		return list;
	}

	/**
	 * 
	 * @description
	 * @param pais
	 * @return Integer
	 * @time 2017上午10:16:14
	 */
	public static Integer getMaxInteger(List<Integer> pais) {
		if (pais == null) {
			return null;
		}
		Integer max = null;
		for (Integer one : pais) {
			if (max == null) {
				max = one;
				continue;
			}
			if (one != null && one > max) {
				max = one;
			}
		}
		return max;
	}

	/**
	 * 
	 * @description
	 * @param pais
	 * @return Integer
	 * @time 2017上午10:16:20
	 */
	public static Integer getMaxPai(List<Integer> pais) {
		if (pais == null) {
			return null;
		}
		Integer max = null;
		for (Integer one : pais) {
			if (max == null) {
				max = one;
				continue;
			}
			if (one != null) {
				if (one % 100 > max % 100) {
					max = one;
				} else if (one % 100 == max % 100 && one > max) {
					max = one;
				}
			}
		}
		return max;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List arrayToList(Integer[] t) {
		List list = new ArrayList();
		for (Integer one : t) {
			list.add(one);
		}
		return list;
	}

	// public static void main(String[] args) {
	// String s = new String("1");
	// String s2 = "1";
	// s.intern();
	// System.out.println(s == s2);
	// String s3 = new String("1") + new String("1");
	// String s4 = "11";
	// s3.intern();
	// System.out.println(s3 == s4);
	// }
	//
	// public static void main(String[] args) {
	// String s = new String("1");
	// s.intern();
	// String s2 = "1";
	// System.out.println(s == s2);
	// String s3 = new String("1") + new String("1");
	// s3.intern();
	// String s4 = "11";
	// System.out.println(s3 == s4);
	// }

//	public static void main(String[] args) {
//		// String s1 = xxx1.toString().intern();
//		// String s2 = xxx2.toString().intern();
//		String s2 = "a";
//		String s1 = "b";
//		assert (1 == 1);
//	}
}
