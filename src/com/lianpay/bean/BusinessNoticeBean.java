package com.lianpay.bean;

import java.io.Serializable;

/**
 * 异步
 * 
 * @author lihp
 * @date 2017-3-17 上午10:07:06
 */
public class BusinessNoticeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -821204118733047614L;

	/** 商户编号 . */
	private String oid_partner;

	/** 签名方式 . */
	private String sign_type;

	/** 签名串 . */
	private String sign;

	/** 商户付款流水号即订单号. */
	private String no_order;

	/** 商户付款请求时间. */
	private String dt_order;

	/** 金额. */
	private String money_order;

	/** 连连支付单. */
	private String oid_paybill;

	/** 商户付款请求时间. */
	private String info_order;

	/** 订单状态，付款结果以订单状态为判断依据. */
	private String result_pay;

	/** 清算时间. */
	private String settle_date;

	public String getOid_partner() {
		return oid_partner;
	}

	public void setOid_partner(String oid_partner) {
		this.oid_partner = oid_partner;
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

	public String getOid_paybill() {
		return oid_paybill;
	}

	public void setOid_paybill(String oid_paybill) {
		this.oid_paybill = oid_paybill;
	}

	public String getInfo_order() {
		return info_order;
	}

	public void setInfo_order(String info_order) {
		this.info_order = info_order;
	}

	public String getResult_pay() {
		return result_pay;
	}

	public void setResult_pay(String result_pay) {
		this.result_pay = result_pay;
	}

	public String getSettle_date() {
		return settle_date;
	}

	public void setSettle_date(String settle_date) {
		this.settle_date = settle_date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessNoticeBean [oid_partner=");
		builder.append(oid_partner);
		builder.append(", sign_type=");
		builder.append(sign_type);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", no_order=");
		builder.append(no_order);
		builder.append(", dt_order=");
		builder.append(dt_order);
		builder.append(", money_order=");
		builder.append(money_order);
		builder.append(", oid_paybill=");
		builder.append(oid_paybill);
		builder.append(", info_order=");
		builder.append(info_order);
		builder.append(", result_pay=");
		builder.append(result_pay);
		builder.append(", settle_date=");
		builder.append(settle_date);
		builder.append("]");
		return builder.toString();
	}

}
