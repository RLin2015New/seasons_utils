package com.tools;

import java.util.List;
import java.util.Random;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName RandomTools.java
 * @description
 * @author lifl
 * @time 2017上午10:37:47
 *
 */

public class RandomTools {

	public static Integer draw(List<Integer> list) {
		int index = mt_rand(0, list.size() - 1);
		return (Integer) list.get(index);
	}
	
	public static int mt_rand(int min, int max) {
		Random r = new Random();
		return min + r.nextInt(max - min + 1);
	}

	public static void main(String[] args){
		int count = 5;
		int times = 10000;
		int[] counters = new int[count];
		for(int i = 0 ;i < times ;i++){
			int rand = mt_rand(0, count-1);
			counters[rand]++;
		}
//		System.out.println(ArrayTools.arrayToString(counters, ","));
		for(int i :counters){
			System.out.println(i);
		}
	}
	
}


