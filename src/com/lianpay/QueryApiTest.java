package com.lianpay;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianpay.api.util.TraderRSAUtil;
import com.lianpay.bean.QueryPaymentRequestBean;
import com.lianpay.bean.QueryPaymentResponseBean;
import com.lianpay.constant.PaymentConstant;
import com.lianpay.constant.PaymentStatusEnum;
import com.lianpay.util.HttpUtil;
import com.lianpay.util.SignUtil;

/**
 * DEMO仅提供商户参考方便对接实时付款，具体对接看先细看实时付款API商户接口说明书
 * 
 */
public class QueryApiTest {

	String test = "{\"busi_partner\":\"101001\",\"sign\":\"Smky7Cb8xmgCb8s4DdfwBw77m+gZZ+clTwzoMWRhGUWs4xHb\\/HkUsLaWrwKB+CuKk9dky8SULxl1A6ZQlZlKpB1nhX8\\/p+7I8SLrOG\\/q8vEJGEA4jH2uM\\/Jk3Qh3ix9DEPXwUsscfSLRATcf5v6rsdlQFrIqaDXyc0Mw0dzDSzY=\",\"acct_name\":\"苏亮\",\"oid_partner\":\"201408071000001543\",\"risk_item\":\"{\\\"user_info_bind_phone\\\":\\\"15853239182\\\",\\\"user_info_identify_type\\\":\\\"4\\\",\\\"user_info_id_type\\\":\\\"0\\\",\\\"user_info_mercht_userlogin\\\":\\\"15853239182\\\",\\\"user_info_id_no\\\":\\\"370211199004212014\\\",\\\"user_info_mercht_userno\\\":\\\"70\\\"}\",\"no_order\":\"liang_t_20171228\",\"name_goods\":\"苏亮测试订单_2\",\"flag_modify\":\"1\",\"money_order\":\"0.01\",\"valid_order\":\"10080\",\"notify_url\":\"http:\\/\\/test.yintong.com.cn:80\\/apidemo\\/API_DEMO\\/notifyUrl.htm\",\"id_type\":\"0\",\"info_order\":\"test\",\"user_id\":\"\\nliang_test\",\"id_no\":\"141031199307290012\",\"sign_type\":\"RSA\",\"dt_order\":\"20171228142303\"}";
	private static final Logger logger = LoggerFactory.getLogger(QueryApiTest.class);
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// 连连内部测试环境数据
		QueryPaymentRequestBean queryRequestBean = new QueryPaymentRequestBean();
		queryRequestBean.setNo_order("20170208165923");
		queryRequestBean.setOid_partner(PaymentConstant.OID_PARTNER);
		queryRequestBean.setApi_version(PaymentConstant.API_VERSION);
		queryRequestBean.setSign_type(PaymentConstant.SIGN_TYPE);
		queryRequestBean.setSign(SignUtil.genRSASign(JSON.parseObject(JSON.toJSONString(queryRequestBean))));

		System.out.println(SignUtil.genRSASign(JSON.parseObject(JSON.toJSONString(queryRequestBean))));

		JSONObject json = JSON.parseObject(JSON.toJSONString(queryRequestBean));

		String queryResult = HttpUtil.doPost("https://instantpay.lianlianpay.com/paymentapi/queryPayment.htm", json,
				"UTF-8");
		System.out.print("实时付款查询接口返回响应报文：" + queryResult);
		logger.info("实时付款查询接口响应报文：" + queryResult);
		if (StringUtils.isEmpty(queryResult)) {
			// 可抛异常，查看原因
			logger.error("实时付款查询接口响应异常");
			return;
		}
		QueryPaymentResponseBean queryPaymentResponseBean = JSONObject.parseObject(queryResult,
				QueryPaymentResponseBean.class);

		// 先对结果验签
		boolean signCheck = TraderRSAUtil.checksign(PaymentConstant.PUBLIC_KEY_ONLINE,
				SignUtil.genSignData(JSONObject.parseObject(queryResult)), queryPaymentResponseBean.getSign());
		if (!signCheck) {
			// 传送数据被篡改，可抛出异常，再人为介入检查原因
			logger.error("返回结果验签异常,可能数据被篡改");
			return;
		}
		if (queryPaymentResponseBean.getRet_code().equals("0000")) {
			PaymentStatusEnum paymentStatusEnum = PaymentStatusEnum
					.getPaymentStatusEnumByValue(queryPaymentResponseBean.getResult_pay());
			// TODO商户根据订单状态处理自已的业务逻辑
			switch (paymentStatusEnum) {
			case PAYMENT_APPLY:
				// 付款申请，这种情况一般不会发生，如出现，请直接找连连技术处理
				break;
			case PAYMENT_CHECK:
				// 复核状态 TODO
				// 返回4002，4003，4004时，订单会处于复核状态，这时还未创建连连支付单，没提交到银行处理，如需对该订单继续处理，需商户先人工审核这笔订单是否是正常的付款请求，没问题后再调用确认付款接口
				// 如果对于复核状态的订单不做处理，可当做失败订单
				break;
			case PAYMENT_SUCCESS:
				// 成功 TODO
				break;
			case PAYMENT_FAILURE:
				// 失败 TODO
				break;
			case PAYMENT_DEALING:
				// 处理中 TODO
				break;
			case PAYMENT_RETURN:
				// 退款 TODO
				// 可当做失败（退款情况
				// 极小概率下会发生，个别银行处理机制是先扣款后打款给用户时再检验卡号信息是否正常，异常时会退款到连连商户账上）
				break;
			case PAYMENT_CLOSED:
				// 关闭 TODO 可当做失败 ，对于复核状态的订单不做处理会将订单关闭
				break;
			default:
				break;
			}
		} else if (queryPaymentResponseBean.getRet_code().equals("8901")) {
			// 订单不存在，这种情况可以用原单号付款，最好不要换单号，如换单号，在连连商户站确认下改订单是否存在，避免系统并发时返回8901，实际有一笔订单
		} else {
			// 查询异常（极端情况下才发生,对于这种情况，可人工介入查询，在连连商户站查询或者联系连连客服，查询订单状态）
			logger.error("查询异常");
		}


	}

}
