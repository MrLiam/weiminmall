package com.weimin.common.exception;

/**
 * 从自定义异常派生的消息异常
 * @author AllenZhang
 * @version 0.1 (2008.11.05)
 * @modify AllenZhang (2008.11.05)
 */
public class MsgException extends BaseException {

	private static final long serialVersionUID = -1530680152575807626L;
	
	/**
	 * 构造详细消息为 指定消息 的新异常
	 * @author AllenZhang
	 */
	public MsgException() {
		super();
	}

	/**
	 * 构造详细消息为 message 的新异常
	 * @author AllenZhang
	 */
	public MsgException(String message) {
		super(message);
	}
	
	/**
	 * 构造详细消息为 message 原因为 cause 的新异常
	 * @author AllenZhang
	 */
	public MsgException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造原因为 cause 的新异常
	 * @author AllenZhang
	 */
	public MsgException(Throwable cause) {
		super(cause);
	}		
}