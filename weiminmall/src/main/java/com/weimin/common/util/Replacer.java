package com.weimin.common.util;

/**
 * 和 ReplaceUtil用法基本一致<br>
 * 可以使用连缀式调用法 如：<br>
 * <b style="color:#7f0055">new</b> Repalcer(str).singleLine().removeHtmlTag().removeEmpty()
 * @date 2015年11月19日 14:33:11
 * @author TJ
 */
public class Replacer {

	private String str;
	
	public Replacer() {
	}

	public Replacer(String str) {
		this.str = str;
	}
	
	public Replacer setStr(String str) {
		this.str = str;
		return this;
	}
	
	public String getStr() {
		return str;
	}
	
	/**
	 * 移除 html 标签 以及标签的内容如：替换&lt;script&gt;alert('haha')&lt;/script&gt;为
	 * alert('haha')
	 */
	public Replacer replaceHtmlTag(){
		str = ReplaceUtil.replaceHtmlTag(str);
		return this;
	}
	
	/**
	 * 替换html标签如：替换 &lt; 为 &amp;lt; &amp;为&amp;amp; <br>
	 *替换的内容包括： &lt;&gt;&amp;&quot;
	 */
	public Replacer replaceHtmlTag2(){
		str = ReplaceUtil.replaceHtmlTag2(str);
		return this;
	}
	
	/**
	 * 移除 html 标签 以及标签的内容如：替换&lt;script&gt;alert('haha')&lt;/script&gt;为
	 * alert('haha')
	 */
	public Replacer removeHtmlTag(){
		str = ReplaceUtil.removeHtmlTag(str);
		return this;
	}
	
	/**
	 * 让数据变换为单行
	 */
	public Replacer singleLine(){
		str = ReplaceUtil.singleLine(str);
		return this;
	}
	
	/**
	 * 去除所有的空白字符 包括 [ \t\n\x0B\f\r]
	 */
	public Replacer removeEmpty(){
		str = ReplaceUtil.removeEmpty(str);
		return this;
	}

	@Override
	public String toString() {
		return str;
	}
	
}
