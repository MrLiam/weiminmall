package com.weimin.junittest;

import org.junit.Test;

import com.weimin.common.util.SmsTool;

public class DemoTest {
	/**
	 * 测试发送短信
	 */
	@Test
	public void sendMsg(){
		System.out.println(SmsTool.sendMsg("17708075597","233333","3", true));
	}
}
