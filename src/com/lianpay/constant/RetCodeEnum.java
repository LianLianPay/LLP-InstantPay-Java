package com.lianpay.constant;


/**
 * 实时付款返回码
 * 
 */
public enum RetCodeEnum {

	SUCC("0000", "交易成功", "请求成功"), 
	SIGN_ERROR("1001", "商户请求签名未通过", "请求直接拦截，连连数据未落地,订单失败"),
	ORDER_REPEAT_ERROR("1002","商户单号重复", "已有相同订单号,需查询订单状态"), 
	PARAM_ERROR("1004", "商户请求参数校验错误", "请求拦截,连连数据未落地,订单失败"),
	BANK_UNSUPPORT_ERROR("1005", "不支持该银行账户类型", "请求拦截,连连数据未落地,订单失败"),
	IP_ERROR("1008", "商户请求IP错误", "请求拦截,连连数据未落地,订单失败"),
	ORDER_REPEAT_SUBMIT("2005", "原交易已在进行处理，请勿重复提交", "已有相同订单号,需查询订单状态"),
	TRADER_INVALID("3001", "非法的商户", "请求拦截,连连数据未落地,订单失败"),
	ORDER_CONFIRM_ERROR("4001", "该订单不存在或非复核状态", "调用确认付款接口返回,原先复核订单关闭了，或者不存在该订单"),
	ORDER_SUSPECT("4002", "疑似重复提交订单","当天不同订单号,付款相同金额到相同卡号，第二笔起当作疑似订单，这是连连支付单未创建，不调用确认付款接口的话可置为失败"),
	BANK_SUSPECT("4003", "收款银行卡和姓名不一致", "连连校验已在连连有用户信息的卡号和姓名，最终提交到银行处理时，银行会再校验卡号信息"), 
	BOTH_SUSPECT("4004", "疑似重复提交订单且收款银行卡和姓名不一致", "结合4002和4003的解释描述"), 
	PAY_AUTH_ERROR("4005", "商户未开通权限", "请求直接拦截，订单失败，连连数据未落地"),
	ENCRYPT_ERROR("4006", "敏感信息加密异常", "异常码,需查询订单状态"),
	DESCRYPT_ERROR("4007", "敏感信息解密异常","异常码,需查询订单状态"), 
	CODE_BEYOND_ERROR("4008", "验证码次数超限","验证码错误次数超6次"), 
	CONFIRM_CODE_ERROR("4009", "验证码异常", "异常码,需查询订单状态"), 
	CODE_EXPIRE_ERROR("4010", "验证码失效", "订单失败,订单将关闭"), 
	CODE_NOTMATCH_ERROR("4011", "验证码不匹配", "传入不匹配的验证码"), 
	BANK_QUERY_ERROR("4012", "银行卡查询异常", "一般是卡号信息不存在,请求拦截,连连数据未落地,订单失败"),
	ACCOUNT_ERROR("4103", "账户异常", "订单失败,可用原订单号重新提交，请求参数需一样"),
	ORDER_CREATE_ERROR("4104", "付款订单创建失败", "订单失败,可用原订单号重新提交，请求参数需一样"),
	PRCPTCD_QUERY_ERROR("4105", "大额行号查询失败", "请求拦截,连连数据未落地,订单失败"),
    NO_RECORD("8901", "没有记录", "订单不存在,查询接口返回"),
    ACCOUNT_MENOY_LACK("9104", "账户余额不足", "订单失败,可用原订单号重新提交，请求参数需一样"), 
    RISK_ERROR(	"9910", "风险等级过高", "请求拦截,连连数据未落地,订单失败"),
    BEYOND_SINGLE_LIMIT("9911", "超过单笔限额", "请求拦截,连连数据未落地,订单失败"),
    BEYOND_DAY_LIMIT("9912", "超过单日限额", "请求拦截,连连数据未落地,订单失败"),
    BEYOND_MONDY_LIMIT("9913", "超过单月限额", "请求拦截,连连数据未落地,订单失败"), 
    SYSTEM_ERROR("9999", "系统错误","未知异常，异常码,需查询订单状态");


	public final String code;

	public final String msg;

	public final String desc;

	RetCodeEnum(String code, String msg, String desc) {
		this.code = code;
		this.msg = msg;
		this.desc = desc;
	}

	public static RetCodeEnum getRetCodeEnumByValue(String value) {
		for (RetCodeEnum retCodeEnum : RetCodeEnum.values()) {
			if (retCodeEnum.getCode().equals(value)) {
				return retCodeEnum;
			}
		}
		return null;
	}

	/**
	 * 判断返回code是否需要调用付款查询结果
	 * 
	 * @param trade_code
	 * @return
	 */
	public static boolean isNeedQuery(String trade_code) {
		if (trade_code.equals(ORDER_REPEAT_ERROR.code) || trade_code.equals(ORDER_REPEAT_SUBMIT.code)
				|| trade_code.equals(ENCRYPT_ERROR.code) || trade_code.equals(DESCRYPT_ERROR.code)
				|| trade_code.equals(CONFIRM_CODE_ERROR.code)||trade_code.equals(SYSTEM_ERROR.code)) {
			return true;
		}
		return false;

	}
	
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
