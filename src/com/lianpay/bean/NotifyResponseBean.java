package com.lianpay.bean;

import java.io.Serializable;

/**
 * 
 * 异步回调reponse
 * 
 * @author lihp
 * @date 2017-3-17 上午10:04:52
 */
public class NotifyResponseBean implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1243721631785017233L;

	/** 交易结果code . */
	public String ret_code;

	/** 交易结果描述 . */
	public String ret_msg;

	public String getRet_code() {
		return ret_code;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_msg() {
		return ret_msg;
	}

	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}

}
