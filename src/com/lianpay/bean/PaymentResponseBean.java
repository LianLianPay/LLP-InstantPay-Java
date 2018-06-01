package com.lianpay.bean;

/**
 * 付款 response bean
 * 
 */
public class PaymentResponseBean extends BaseResponseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1198276503805567647L;

	/** 连连支付支付单号 . */
	public String oid_paybill;

	/** 商户付款流水号 . */
	public String no_order;

	/** 验证码. */
	public String confirm_code;

	public String getOid_partner() {
		return oid_partner;
	}

	public void setOid_partner(String oid_partner) {
		this.oid_partner = oid_partner;
	}

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

	public String getOid_paybill() {
		return oid_paybill;
	}

	public void setOid_paybill(String oid_paybill) {
		this.oid_paybill = oid_paybill;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentResponseBean [oid_paybill=");
		builder.append(oid_paybill);
		builder.append(", no_order=");
		builder.append(no_order);
		builder.append(", confirm_code=");
		builder.append(confirm_code);
		builder.append(", ret_code=");
		builder.append(ret_code);
		builder.append(", ret_msg=");
		builder.append(ret_msg);
		builder.append(", sign_type=");
		builder.append(sign_type);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", oid_partner=");
		builder.append(oid_partner);
		builder.append("]");
		return builder.toString();
	}

}
