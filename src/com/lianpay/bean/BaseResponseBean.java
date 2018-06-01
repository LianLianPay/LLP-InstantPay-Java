package com.lianpay.bean;

import java.io.Serializable;

/**
 * 基础reponse bean
 * 
 */
public class BaseResponseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1245259120617909449L;

	/** 交易结果code . */
	public String ret_code;

	/** 交易结果描述 . */
	public String ret_msg;

	/** 签名方式 . */
	public String sign_type;

	/** 签名 . */
	public String sign;

	/** 商户编号 . */
	public String oid_partner;

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

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getOid_partner() {
		return oid_partner;
	}

	public void setOid_partner(String oid_partner) {
		this.oid_partner = oid_partner;
	}

}
