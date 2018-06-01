package com.lianpay.bean;

/**
 * 实时付款交易结果查询response bean
 * 
 */
public class QueryPaymentResponseBean extends BaseResponseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1405281788989159722L;

	/** 商户付款流水号 . */
	public String no_order;

	/** 商户下单时间 . */
	public String dt_order;

	/** 付款金额 . */
	public String money_order;

	/** 付款状态 . */
	public String result_pay;

	/** 连连银通付款流水号 . */
	public String oid_paybill;

	/** 订单描述. */
	public String info_order;

	/** 清算日期 . */
	public String settle_date;

	/** 支付备注. */
	public String memo;

	public String getNo_order() {
		return no_order;
	}

	public void setNo_order(String no_order) {
		this.no_order = no_order;
	}

	public String getMoney_order() {
		return money_order;
	}

	public void setMoney_order(String money_order) {
		this.money_order = money_order;
	}

	public String getResult_pay() {
		return result_pay;
	}

	public void setResult_pay(String result_pay) {
		this.result_pay = result_pay;
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

	public String getSettle_date() {
		return settle_date;
	}

	public void setSettle_date(String settle_date) {
		this.settle_date = settle_date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDt_order() {
		return dt_order;
	}

	public void setDt_order(String dt_order) {
		this.dt_order = dt_order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryPaymentResponseBean [no_order=");
		builder.append(no_order);
		builder.append(", dt_order=");
		builder.append(dt_order);
		builder.append(", money_order=");
		builder.append(money_order);
		builder.append(", result_pay=");
		builder.append(result_pay);
		builder.append(", oid_paybill=");
		builder.append(oid_paybill);
		builder.append(", info_order=");
		builder.append(info_order);
		builder.append(", settle_date=");
		builder.append(settle_date);
		builder.append(", memo=");
		builder.append(memo);
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
