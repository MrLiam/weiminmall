package com.weimin.common.util;

import java.util.Random;

public class RandomUtil {
	
	/**
	 * 生成6位不重复的随机数
	 * @return 6位不重复的随机数
	 */
	public static int get6npRandom(){
		int[] array = {1,2,3,4,5,6,7,8,9};
		Random rand = new Random();
		for (int i = 9; i > 1; i--) {
		    int index = rand.nextInt(i);
		    int tmp = array[index];
		    array[index] = array[i - 1];
		    array[i - 1] = tmp;
		}
		int result = 0;
		for(int i = 0; i < 6; i++){
		    result = result * 10 + array[i];
		}
		return result;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(get6npRandom());
		}

	}

}
