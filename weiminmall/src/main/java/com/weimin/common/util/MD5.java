package com.weimin.common.util;

import java.security.MessageDigest;

/**
 * MD5加密字符串
 * @author CaoJian
 * @date Oct 19, 2009
 * @modify Oct 19, 2009
 * @version 1.0
 */
public class MD5 {
    
    /*十六进制下数字到字符的映射数组*/
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    /**
     * MD5加密字符串（大写）
     * @param inputString    输入的字符串
     * @return    返回加密后的字符串（大写）
     */
    public static String createMD5_upperCase(String inputString){
        String result = encodeByMD5(inputString);
        return null == result ? "" : result;
    }
    
    /**
     * MD5加密字符串（小写）
     * @param inputString    输入的字符串
     * @return    返回加密后的字符串（小写）
     */
    public static String createMD5_lowerCase(String inputString){
        String result = encodeByMD5(inputString);
        return null == result ? "" : result.toLowerCase();
    }
    
    /**
     * MD5验证，验证输入的字符串是否与加密字符串相同
     * @param md5String    MD5加密的字符串
     * @param inputString    输入的字符串
     * @return    验证结果，boolean类型 true 相同；false 不同
     */
    public static boolean validateMD5(String md5String, String inputString) {
    	if (null == md5String || null == inputString) {
    		return false;
    	}
        if(md5String.equalsIgnoreCase(encodeByMD5(inputString))) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 对字符串进行MD5加密(默认返回大写)
     * @author CaoJian
     * @date Oct 19, 2009
     * @modify Oct 19, 2009
     * @param originString
     * @return
     */
    private static String encodeByMD5(String originString) {
        if (originString != null) {
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    /** 
     * 转换字节数组为十六进制字符串
     * @param b    字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    /**
     * 将一个字节转化成十六进制形式的字符串
     * @author CaoJian
     * @date Oct 19, 2009
     * @modify Oct 19, 2009
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
        	n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    
}