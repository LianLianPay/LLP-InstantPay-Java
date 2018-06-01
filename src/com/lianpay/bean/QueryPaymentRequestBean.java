package com.lianpay.bean;

/**
 * 实时付款交易结果查询request bean
 * 
 */
public class QueryPaymentRequestBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5466252690879093697L;

	/** 商户付款流水号 . */
	private String no_order;

	/** 连连银通付款流水号 . */
	public String oid_paybill;

	/** 商户编号 . */
	private String oid_partner;

	/** 用户来源 . */
	private String platform;

	/** api当前版本号 . */
	private String api_version;

	/** 签名方式 . */
	private String sign_type;

	/** 签名方 . */
	private String sign;

	public String getNo_order() {
		return no_order;
	}

	public void setNo_order(String no_order) {
		this.no_order = no_order;
	}

	public String getOid_paybill() {
		return oid_paybill;
	}

	public void setOid_paybill(String oid_paybill) {
		this.oid_paybill = oid_paybill;
	}

	public String getOid_partner() {
		return oid_partner;
	}

	public void setOid_partner(String oid_partner) {
		this.oid_partner = oid_partner;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getApi_version() {
		return api_version;
	}

	public void setApi_version(String api_version) {
		this.api_version = api_version;
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

}
