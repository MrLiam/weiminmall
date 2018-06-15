package com.weimin.common.util;

/**
 * 2015-11-19 11:43:42 判断格式的正确性
 * 
 * @author TJ
 */
public class ValidateUtil extends Validate {
	/**
	 * 包含有字母数字下划线汉字2到30个字符之间
	 */
	public static final String REGX_REAL_NAME = "[,\\.#@\\\\\\-\\(\\)\\u0020\\w\\u4e00-\\u9fa5、，。（）]{2,128}";
	public static final String REGX_PHONE_NUMBER = "1[3-9]\\d{9}";
	public static final String REGX_EMAIL = "[\\w\\-]{2,}@[\\w\\-]+\\.[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})?";

	/**
	 * 是有效的用户名
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isValidRealName(String name) {
		return name.matches(REGX_REAL_NAME);
	}

	/**
	 * 是有效的手机号
	 * 
	 * @param phoneno
	 * @return
	 */
	public static boolean isValidPhoneNumber(String phoneno) {
		return phoneno.matches(REGX_PHONE_NUMBER);
	}
	
	/**
	 * 是有效的邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		return email.matches(REGX_EMAIL);
	}
	
}
