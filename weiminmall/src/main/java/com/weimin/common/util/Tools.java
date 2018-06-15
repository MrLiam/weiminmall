package com.weimin.common.util;

import java.io.File;
//import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
//import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.weimin.common.exception.MsgException;
//import com.weimin.common.db.dto.CustomPinYin;

/**
 * 综合工具类
 * <pre>
 * 本类提供一些综合的静态方法，主要方法有
 * 1、生成 bit 位 随即数 random()
 * 2、统计字符串所占的字节数 getBytesFromAString()
 * 3、统计字符串的字符数 getWordsFromAString()
 * 4、生成HTML原码：用于 Bean:Write 显示 信息 createAreaHTML()
 * 5、生成段落数组 createAreaArray()
 * 6、生成WORD回车符的字符串 createAreaTEXTForWord()
 * 7、切字符串，把源字符串切为指定长度的字符串 sliceString()
 * 8、判断是否为允许注册的名称 allowRegisterName()
 * 9、判断是否为数字 allowRegisterNumber()
 * 10、纠正domainName（显示） correctDomainNameForShow()
 * 11、纠正domainName（储存）correctDomainName()
 * 12、从Map中取得指定键的值 getObjFromMap()
 * </pre>
 * @author AllenZhang
 * @version 0.1 (2008.11.06)
 * @modify AllenZhang (2008.11.06)
 */

public class Tools {
	
	private static final Log log = LogFactory.getLog(Tools.class);
	
	/**
	 * 生成 bit 位 随即数
	 * @return  bit 位 随即数
	 * @author Allen Zhang
	 */
	public static synchronized String random(int bit) {
		String myRandom = String.valueOf(Math.random());
		myRandom = myRandom.replaceAll("\\.", "").replaceAll("0", "");
		if (myRandom.length() > bit) {
			myRandom = myRandom.substring(0, bit);
		}		
		while (myRandom.length() < bit) {
			myRandom += "0";
		}
		return myRandom;
	}	

	/**
	 * 统计字符串所占的字节数
	 * @param srcStr 待统计的字符串
	 * @return 字符串所占的字节数
	 * @author Allen Zhang
	 */
	public static synchronized int getBytesFromAString(String srcStr) {
		int count = 0;
		if (srcStr != null) {
			byte[] bytes = srcStr.getBytes();
			count = bytes.length;
		}
		return count;
	} 	

	/**
	 * 统计字符串的字符数
	 * @param srcStr 待统计的字符串
	 * @return 字符串的字符数
	 * @author Allen Zhang
	 */
	public static synchronized int getWordsFromAString(String srcStr) {
		int count = 0;
		if (srcStr != null) {
			count = srcStr.length();
		}
		return count;
	} 	
	
	/**
	 * 生成HTML原码：用于 Bean:Write 显示 信息 <BR>
	 * 注意 Bean:Write的 属性filter应为false，即：filter="false"
	 * @param textStr 待转换的源串
	 * @return 对应的HTML原码 
	 * @author Allen Zhang
	 */
	public static synchronized String createAreaHTML(String textStr) {
		StringBuffer selectBuffer = new StringBuffer();
		int charCount = 0;
		if (textStr != null) {
			charCount = textStr.length ();
		}
		if (charCount > 0) {
			for (int charNum = 0; charNum < charCount; charNum++) {
				char character = textStr.charAt(charNum);
				switch (character) {
					/**
					 * ASCII space (&#x0020;) 
					 * ASCII tab (&#x0009;) 
					 * ASCII form feed (&#x000C;) 
					 * Zero-width space (&#x200B;)
					 */ 
					case '\u0020': {
						selectBuffer.append("&nbsp;");
						break;
					}
					case '\u0009': {
						selectBuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;");
						break;
					}
					case '\u000C':  {
						break;
					}
					case '\u200B':  {
						selectBuffer.append("&nbsp;");
						break;
					}
					case '\u2028': {
						break;
					}
					case '\u2029': {
						break;
					}
					case '\r': {
						break;
					}
					case '\n': {
						if (selectBuffer.length() > 4) {
							String endStr = selectBuffer.toString().substring(selectBuffer.length() - 4, selectBuffer.length());
							if (!"<BR>".equals(endStr)) {
								selectBuffer.append("<BR>");
							}
						}
						break;
					}
					default:  {
						selectBuffer.append(character);
						break;
					}
				}
			}
		}
		else {
			selectBuffer.append("");
		}
		return selectBuffer.toString();
	}
	
	
	/**
	 * 生成段落数组
	 * @param textStr 待转换的源串
	 * @return 段落数组
	 * @author Allen Zhang
	 */
	public static synchronized String[] createAreaArray(String textStr) {
		String[] htmlArray = createAreaHTML(textStr).split("<BR>");
		for (int i = 0 ; i < htmlArray.length ; i++) {
			htmlArray[i] = htmlArray[i].trim();
		}
		return htmlArray;
	}
	
	/**
	 * 生成WORD回车符的字符串
	 * @param textStr 待转换的源串
	 * @return 去掉回车符的字符串  
	 * @author Allen Zhang
	 */
	public static synchronized String createAreaTEXTForWord(String textStr) {
		StringBuffer selectBuffer = new StringBuffer();
		int charCount = 0;
		if (textStr != null) {
			charCount = textStr.length ();
		}
		if (charCount > 0) {
			for (int charNum = 0; charNum < charCount; charNum++) {
				char character = textStr.charAt(charNum);
				switch (character) {
					/**
					 * ASCII space (&#x0020;) 
					 * ASCII tab (&#x0009;) 
					 * ASCII form feed (&#x000C;) 
					 * Zero-width space (&#x200B;)
					 */ 
					case '\u0020': {
						selectBuffer.append("");
						break;
					}
					case '\u0009': {
						selectBuffer.append("");
						break;
					}
					case '\u000C':  {
						break;
					}
					case '\u200B':  {
						selectBuffer.append("");
						break;
					}
					case '\u2028': {
						selectBuffer.append("");
						break;
					}
					case '\u2029': {
						selectBuffer.append("");
						break;
					}
					case '\r': {
						selectBuffer.append("");
						break;
					}
					case '\n': {
						selectBuffer.append("\\n");
						break;
					}
					default:  {
						selectBuffer.append(character);
						break;
					}
				}
			}
		}
		else {
			selectBuffer.append("");
		}
		return selectBuffer.toString();
	}
	
	/**
	 * 切字符串，把源字符串切为指定长度的字符串
	 * @param sourceStr 源字符串
	 * @param strLength 指定长度
	 * @return 切后字符串
	 * @author AllenZhang
	 */
	public static synchronized String sliceString(String sourceStr, int strLength){
		if (sourceStr != null) {
			if (strLength > 0 && sourceStr.length() > strLength) {
				return sourceStr.substring(0, strLength) + "...";
			}
			else {
				return sourceStr;
			}
		}
		else {
			return "";
		}
	}	
	
	/**
	 * 判断是否为允许注册的名称
	 * @param matchString 待测试字符串
	 * @param fieldName 字段名称
	 * @throws MsgException 不为允许注册的名称抛出此异常
	 * @author AllenZhang
	 */
	public static synchronized void allowRegisterName(String matchString, String fieldName) throws MsgException {
		if (!Validate.isAllowRegisterName(matchString)) {
			throw new MsgException(fieldName + "&nbsp;&nbsp;必须为汉字、数字、字母、下划线或其组合，请重试！");
		}
	}
	
	/**
	 * 判断是否为数字
	 * @param matchString 待测试字符串
	 * @param fieldName 字段名称
	 * @throws MsgException 不为允许注册的名称抛出此异常
	 * @author AllenZhang
	 */
	public static synchronized void allowRegisterNumber(String matchString, String fieldName) throws MsgException {
		if (!Validate.isNumeric(matchString)) {
			throw new MsgException(fieldName + "&nbsp;&nbsp;必须为数字，请重试！");
		}
	}	
	
	/**
	 * 纠正domainName（显示）
	 * @param website 待纠正的domainName
	 * @return 待纠正后的domainName
	 * @author AllenZhang
	 */
	public static synchronized String correctDomainNameForShow(String website) {
		String domainName = "";
		if (website != null && !"".equals(website.trim())) {
			website = website.toLowerCase();
			if (website.indexOf("http")>-1 || website.indexOf("www")>-1) {
				if (website.startsWith("www")) {
					website = "http://" + website;
					domainName = "<a href='"+website+"' target='_bank'>"+website+"</a>";
				}
				else if (website.startsWith("http")) {
					domainName = "<a href='"+website+"' target='_bank'>"+website+"</a>";
				}
				else if (website.startsWith("<a")) {
					domainName = website;
				}
				else {
					website = "http://www." + website;
					domainName = "<a href='"+website+"' target='_bank'>"+website+"</a>";
				}
			}
			else {
				domainName = website;
			}
		}
		else {
			domainName = website;
		}		
		return domainName;
	}	
	
	/**
	 * 纠正domainName（储存）
	 * @param website 待纠正的domainName
	 * @return 待纠正后的domainName
	 * @author AllenZhang
	 */
	public static synchronized String correctDomainName(String website) {
		String domainName = "";
		if (website != null && !"".equals(website.trim())) {
			website = website.toLowerCase();
			if (website.indexOf("http")>-1 || website.indexOf("www")>-1) {
				if (website.startsWith("www")) {
					website = "http://" + website;
					domainName = website;
				}
				else if (website.startsWith("http")) {
					domainName = website;
				}
				else {
					website = "http://www." + website;
					domainName = website;
				}
			}
			else {
				domainName = website;
			}
		}
		else {
			domainName = website;
		}		
		return domainName;
	}	
	
	/**
	 * 从Map中取得指定键的值
	 * @param map
	 * @param key 指定键
	 * @return 从Map中取得指定键的值
	 * @throws Exception
	 * @author AllenZhang
	 */
	public static synchronized Object getObjFromMap(Map<String, Object> map, String key) throws Exception {
		if (map != null && key != null && !"".equals(key.trim())) {
			return map.get(key.toUpperCase());
		}
		else {
			return null;
		}
	}
	
	/**
	 * 从Map中取得指定键的值
	 * @param map
	 * @param key 指定键
	 * @return 从Map中取得指定键的值
	 * @throws Exception
	 * @author AllenZhang
	 */
	public static synchronized String getStringFromMap(Map<String, Object> map, String key) throws Exception {
		Object obj = getObjFromMap(map, key);
		if (obj != null) {
			String result = String.valueOf(obj);
			if ("null".equalsIgnoreCase(result.trim())) {
				return "";
			} else {
				return result;
			}
		}
		else {
			return ""; 
		}
	}
	
	/**
	 * 从Map中取得指定键的值
	 * @param map
	 * @param key 指定键
	 * @return 从Map中取得指定键的值
	 * @throws Exception
	 * @author AllenZhang
	 */
	public static synchronized int getNumberFromMap(Map<String, Object> map, String key) throws Exception {
		Object obj = getObjFromMap(map, key);
		if (obj != null) {
			try {
				return ((BigDecimal)obj).intValue();
			} catch (Exception e) {
				try {
					return ((Integer)obj).intValue();
				} catch (Exception e2) {
					return 0;
				}
			}
		}
		else {
			return 0; 
		}
	}
	
	
	/**
	 * 从Map中取得指定键的值
	 * @param map
	 * @param key 指定键
	 * @return 从Map中取得指定键的值
	 * @throws Exception
	 * @author AllenZhang
	 */
	public static synchronized double getDoubleFromMap(Map<String, Object> map, String key) throws Exception {
		Object obj = getObjFromMap(map, key);
		if (obj != null) {
			try {
				return ((BigDecimal)obj).doubleValue();
			} catch (Exception e) {
				try {
					return ((Integer)obj).doubleValue();
				} catch (Exception e2) {
					return 0.0;
				}
			}
		}
		else {
			return 0.0; 
		}
	}	
	
	/**
	 * 将Raw对象转成String
	 * @param obj
	 * @return
	 * @throws Exception
	 * @author AllenZhang
	 */
	public static synchronized String rawObjToString(Object obj) throws Exception {
		String result = "";
		if (obj != null) {
			try {
				byte[] bytes = (byte[]) obj;
				for (int i = 0; i < bytes.length; i++) {
					String aChar = Integer.toHexString(bytes[i] & 0xFF);
					while (aChar.length() < 2) {
						aChar = "0" + aChar;
					}
					result += aChar;
				}
			} catch (Exception e) {
				result = "";
			}
		} 
		return result;
	}	
	
	/**
	 * 从Map中取得指定键的值
	 * @param map
	 * @param key 指定键
	 * @return 从Map中取得指定键的值
	 * @throws Exception
	 * @author AllenZhang
	 */
	public static synchronized String getRawAsStringFromMap(Map<String, Object> map, String key) throws Exception {
		return rawObjToString(getObjFromMap(map, key));
	}
	
	/**
	 * 从属性文件里载入属性
	 * @param propertyFileName 属性文件名（不需要扩展名，即：不包含 .properties 的文件） 
	 * @return ResourceBundle
	 * @throws Exception
	 * @author AllenZhang
	 */
	public static ResourceBundle loadPropertiesFromPropertyFile(String propertyFileName) throws Exception {
		return ResourceBundle.getBundle(propertyFileName, Locale.CHINA);
	}
	
	/**
	 * 判断目录结构是否存在，如过不存在则创建
	 * 
	 * version V1S1
	 * 2014年11月13日 上午9:04:47
	 * @author jh
	 */
	public static boolean validateDirAndCreate(String dir){
		boolean res=true;
		try{
			File file=new File(dir);
			if(!file.exists()){
				file.mkdirs();
			}
		}catch(Exception e){
			res=false;
			log.debug("Tools.validateDirAndCreate:"+e.toString());
		}
		return res;
	}
	
	/**
	 * 取文件后缀
	 * 
	 * version V1S1
	 * 2014年11月13日 上午10:32:59
	 * @author jh
	 */
	public static String getFileSuffix(String fileName){
		String suffix=null;
		if(StringUtils.isNotBlank(fileName)&&StringUtils.contains(fileName, ".")){
			suffix=fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		return suffix;
	}
	
	/**
	 * 读取拼音配置
	 * @Title: readCfg4PdtName 
	 * @Description: 
	 * @return    
	 * @return Map<String,String>   
	 * @author Jh 
	 * @date 2014年12月19日 下午6:43:21
	 */
	/*public static Map<String,String> readCfg4PdtName(CustomPinYin customPinYin){
		Map<String,String> cfgMap=new HashMap<String,String>();
        try {
        	
        	ArrayList<String> list=customPinYin.getListPinYin();
            for(int i=0;i<list.size();i++){
            	if(StringUtils.isNotBlank(list.get(i))){
            		String[] data=list.get(i).split(":");
            		cfgMap.put(data[0], data[1]);
            	}
            }
            
        } catch (Exception e) {   
            e.printStackTrace();   
        }
        
        return cfgMap;
	}*/
	
	/**
	 * 自动补0,或其他字符
	 * @Title: fillZero 
	 * @Description: 
	 * @param str
	 * @param size
	 * @param fillStr
	 * @return    
	 * @return String   
	 * @author Jh 
	 * @date 2014年12月1日 上午11:36:50
	 */
	public static String fillZero(String str,int size, char fillStr){
		return StringUtils.leftPad(str, size, fillStr);
	}
	
	/**
	 * 
	 * @Title: formatMoney 
	 * @Description: 格式化金额
	 * @param money
	 * @return    
	 * @return String   
	 * @author Jh 
	 * @date 2014年12月25日 下午5:46:44
	 */
	public static String formatMoney(double money){
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("##,##0.00");
		return myformat.format(money);
	}
	
}