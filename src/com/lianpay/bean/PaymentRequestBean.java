package com.lianpay.bean;

public class PaymentRequestBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7682226991855956344L;

	/** 商户付款流水号 . */
	private String no_order;

	/** 商户付款时间 . */
	private String dt_order;

	/** 付款金额 . */
	private String money_order;

	/** 银行账号 . */
	private String card_no;

	/** 收款人姓名 . */
	private String acct_name;

	/** 收款人银行名称 . */
	private String bank_name;

	/** 付款用途. */
	private String info_order;

	/** 收款备注 用于给用户显示 . */
	private String memo;

	/** 对公对私标志 0-对私 1-对公 . */
	private String flag_card;

	/** 服务器异步通知地址 . */
	private String notify_url;

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

	public String getDt_order() {
		return dt_order;
	}

	public void setDt_order(String dt_order) {
		this.dt_order = dt_order;
	}

	public String getMoney_order() {
		return money_order;
	}

	public void setMoney_order(String money_order) {
		this.money_order = money_order;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getAcct_name() {
		return acct_name;
	}

	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getInfo_order() {
		return info_order;
	}

	public void setInfo_order(String info_order) {
		this.info_order = info_order;
	}

	public String getFlag_card() {
		return flag_card;
	}

	public void setFlag_card(String flag_card) {
		this.flag_card = flag_card;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
