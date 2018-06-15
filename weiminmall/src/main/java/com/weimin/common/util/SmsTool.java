package com.weimin.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 调用 --短信宝 接口发送验证码
 *   0： 成功
 * 	30：密码错误
 *	40：账号不存在
 *	41：余额不足
 *	42：帐号过期
 *	43：IP地址限制
 *	50：内容含有敏感词
 *	51：手机号码不正确
 * 
 * @author ldl
 * @date 2017-08-31
 * 
 *
 */
public class SmsTool {
	private static Logger log = Logger.getLogger(SmsTool.class);
	private static String loginUsername;
	private static String loginPassword;
	private static String httpUrl;
	private static String httpsUrl;
	private static String msgContent;

	/**
	 * 发送短信的方法
	 * @return
	 */
	public static String sendMsg(String userPhone,String code,String time, boolean security){
//		String loginUsername = "weiminkeji123"; //在短信宝注册的用户名
//		String loginPassword = "123456798"; //在短信宝注册的密码
		//要发送短信的手机号
//		String userPhone = "13000000000";
		//短信内容
//		String msgContent = "【深圳市微苠科技有限公司】您正在登录验证，验证码是{code},请在{time}分钟内按页面提示提交验证码，若非本人操作请忽略此消息。"; // 注意测试时，也请带上公司简称或网站签名，发送正规内容短信。千万不要发送无意义的内容：例如 测一下、您好。否则可能会收不到
		//安全接口：https://api.smsbao.com/sms?u=USERNAME&p=PASSWORD&m=PHONE&c=CONTENT
//		String httpUrl = "http://api.smsbao.com/sms";//普通接口

		StringBuffer httpArg = new StringBuffer();
		httpArg.append("u=").append(getLoginUsername()).append("&");
		httpArg.append("p=").append(md5(getLoginPassword())).append("&");
		httpArg.append("m=").append(userPhone).append("&");
		httpArg.append("c=").append(encodeUrlString(getMsgContent(code, time), "UTF-8"));
		String result = null;
		if(security){
			result = request(getHttpsUrl(), httpArg.toString());
		}else{
			result = request(getHttpUrl(), httpArg.toString());
		}
		return result;
	}

	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = reader.readLine();
			if (strRead != null) {
				sbf.append(strRead);
				while ((strRead = reader.readLine()) != null) {
					sbf.append("\n");
					sbf.append(strRead);
				}
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static String md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static String encodeUrlString(String str, String charset) {
		String strret = null;
		if (str == null)
			return str;
		try {
			strret = java.net.URLEncoder.encode(str, charset);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return strret;
	}

	/*
	 * 从配置文件中读取对应key的信息
	 */
	public static String getProperties(String key) {
		Properties p = new Properties();
		String value = null;
		//防止中文乱码
		try {
			Reader in = new InputStreamReader(SmsTool.class.getResourceAsStream("/weimin_ssm.properties"),"UTF-8");
			p.load(in);
			value = p.getProperty(key);
		} catch (IOException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return value;
	}

	public static String getLoginUsername() {
		if (StringUtils.isBlank(loginUsername)) {
			loginUsername = getProperties("userName");
		}
		return loginUsername;
	}

	public static String getLoginPassword() {
		if (StringUtils.isBlank(loginPassword)) {
			loginPassword = getProperties("password");
		}
		return loginPassword;
	}

	public static String getHttpUrl() {
		if (StringUtils.isBlank(httpUrl)) {
			httpUrl = getProperties("ssmHttpUrl");
		}
		return httpUrl;
	}

	public static String getMsgContent(String code,String time) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isBlank(msgContent)) {
			sb.append(getProperties("msgContent1")).append(code).
				append(getProperties("msgContent2")).append(time)
					.append(getProperties("msgContent3"));
			msgContent = sb.toString();
		}
		return msgContent;
	}

	public static String getHttpsUrl() {
		if (StringUtils.isBlank(httpsUrl)) {
			httpsUrl = getProperties("ssmHttpsUrl");
		}
		return httpsUrl;
	}

}
