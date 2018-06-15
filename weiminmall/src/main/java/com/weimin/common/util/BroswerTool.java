package com.weimin.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取客户端浏览器
 * @author caojian
 * 
 */
public class BroswerTool {
	private static final String IE11 = "rv:11.0";
	private static final String IE10 = "MSIE 10.0";
	private static final String IE9 = "MSIE 9.0";
	private static final String IE8 = "MSIE 8.0";
	private static final String IE7 = "MSIE 7.0";
	private static final String IE6 = "MSIE 6.0";
	private static final String MAXTHON = "Maxthon";
	private static final String QQ = "QQBrowser";
	private static final String GREEN = "GreenBrowser";
	private static final String SE360 = "360SE";
	private static final String FIREFOX = "Firefox";
	private static final String OPERA = "Opera";
	private static final String CHROME = "Chrome";
	private static final String SAFARI = "Safari";
	private static final String OTHER = "其它";

	public static String checkBroswer(String userAgent) {
		if (regex(OPERA, userAgent))
			return OPERA;
		if (regex(CHROME, userAgent))
			return CHROME;
		if (regex(FIREFOX, userAgent))
			return FIREFOX;
		if (regex(SAFARI, userAgent))
			return SAFARI;
		if (regex(SE360, userAgent))
			return SE360;
		if (regex(GREEN, userAgent))
			return GREEN;
		if (regex(QQ, userAgent))
			return QQ;
		if (regex(MAXTHON, userAgent))
			return MAXTHON;
		if (regex(IE11, userAgent))
			return "MSIE 11";
		if (regex(IE10, userAgent))
			return IE10;
		if (regex(IE9, userAgent))
			return IE9;
		if (regex(IE8, userAgent))
			return IE8;
		if (regex(IE7, userAgent))
			return IE7;
		if (regex(IE6, userAgent))
			return IE6;
		return OTHER;
	}

	public static boolean regex(String regex, String str) {
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher m = p.matcher(str);
		return m.find();
	}

}
