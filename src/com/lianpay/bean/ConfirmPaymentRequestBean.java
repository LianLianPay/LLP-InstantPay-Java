package com.lianpay.bean;

/**
 * 确认付款Bean
 * 
 */
public class ConfirmPaymentRequestBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5151860453021854789L;

	/** 商户付款流水号 . */
	private String no_order;

	/** 服务器异步通知地址 . */
	private String notify_url;

	/** 验证码 . */
	private String confirm_code;

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

	public String getConfirm_code() {
		return confirm_code;
	}

	public void setConfirm_code(String confirm_code) {
		this.confirm_code = confirm_code;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
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
