package com.weimin.common.exception;

/**
 * 自定义异常基类
 * <pre>
 * 本类建立用户自定义异常，以方便动作处理类报错。
 * </pre>
 * @author AllenZhang
 * @version 0.1 (2008.11.05)
 * @modify AllenZhang (2008.11.05)
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -71709585767787602L;
	
	/**
	 * 构造详细消息为 指定消息 的新异常
	 * @author AllenZhang
	 */
	public BaseException() {
		super("温馨提示：目前无法访问当前页面，请稍候再试！");
	}

	/**
	 * 构造详细消息为 message 的新异常
	 * @author AllenZhang
	 */
	public BaseException(String message) {
		super(message);
	}
	
	/**
	 * 构造详细消息为 message 原因为 cause 的新异常
	 * @author AllenZhang
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造原因为 cause 的新异常
	 * @author AllenZhang
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}
}