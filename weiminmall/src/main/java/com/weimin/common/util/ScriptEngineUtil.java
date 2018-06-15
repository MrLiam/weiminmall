package com.weimin.common.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * JS 动态代码执行工具类
 * @author caojian
 *
 */
public class ScriptEngineUtil {

	/**
	 * 初始化脚本引擎
	 */
	private static ScriptEngineManager mgr = new ScriptEngineManager();    
	private static ScriptEngine engine = mgr.getEngineByMimeType("application/javascript");

	/**
	 * java 执行JS表达式
	 * @param exp 表达式
	 * @return 计算结果 Object
	 * @throws Exception
	 */
	public static Object executeExpToObject(String exp) throws Exception {
		return engine.eval(exp);
	}

	/**
	 * java 执行JS表达式
	 * @param exp 表达式
	 * @return 计算结果 String
	 * @throws Exception
	 */
	public static String executeExpToStr(String exp) throws Exception {
		return String.valueOf(executeExpToObject(exp));
	}

	/**
	 * java 执行JS表达式
	 * @param exp 表达式
	 * @return 计算结果 double
	 * @throws Exception
	 */
	public static double executeExpToDouble(String exp) throws Exception {
		return Double.parseDouble(executeExpToStr(exp));
	}

	/**
	 * java 执行JS表达式
	 * @param exp 表达式
	 * @return 计算结果 int
	 * @throws Exception
	 */
	public static int executeExpToInt(String exp) throws Exception {
		return (int)executeExpToDouble(exp);
	}

	/**
	 * java 执行JS表达式
	 * @param exp 表达式
	 * @return 计算结果 boolean
	 * @throws Exception
	 */
	public static boolean executeExpToBoolean(String exp) throws Exception {
		return Boolean.parseBoolean(executeExpToStr(exp));
	}

}
