package com.weimin.common.util;

/**
 * 替换文本内容
 * @date 2015年11月19日 14:33:01
 * @author TJ
 */
public class ReplaceUtil {

	/**
	 * 替换html标签中的 < 为 [ , > 为 ]
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHtmlTag(String str) {
		return str.replaceAll("<", "[").replaceAll(">", "]");
	}

	/**
	 * 替换html标签如：替换 &lt; 为 &amp;lt; &amp;为&amp;amp; <br>
	 *替换的内容包括： &lt;&gt;&amp;&quot;
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHtmlTag2(String str) {
		StringBuilder sb = new StringBuilder();
		char chr;
		for (int i = 0; i < str.length(); i++) {
			switch (chr = str.charAt(i)) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			default:
				sb.append(chr);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 移除 html 标签 以及标签的内容如：替换&lt;script&gt;alert('haha')&lt;/script&gt;为
	 * alert('haha')
	 * 
	 * @param str
	 * @return
	 */
	public static String removeHtmlTag(String str) {
		return str.replaceAll("<[^<>]*>", "");
	}

	/**
	 * 让数据变换为单行
	 * 
	 * @param str
	 * @return
	 */
	public static String singleLine(String str) {
		return str.replaceAll("\r", "").replaceAll("\n", "");
	}

	/**
	 * 去除所有的空白字符 包括 [ \t\n\x0B\f\r]
	 * 
	 * @param str
	 * @return
	 */
	public static String removeEmpty(String str) {
		return str.replaceAll("\\s", "");
	}


}
