package com.lianpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lianpay.api.util.TraderRSAUtil;
import com.lianpay.bean.BusinessNoticeBean;
import com.lianpay.bean.NotifyResponseBean;
import com.lianpay.constant.PaymentConstant;
import com.lianpay.constant.PaymentStatusEnum;
import com.lianpay.util.SignUtil;

/**
 * 这个Controller需放到服务器上运行调试 异步回调接口 
 * 用于接收连连付款状态 只有生成连连支付单的订单才会有异步回调通知，
 * 没生成连连支付单的订单连连是不会提交到银行渠道走付款流程
 * 
 * 用spring mvc获取需要配置下面 或者参考另一个文件夹的异步回调demo从http request 数据流中获取
 * <mvc:annotation-driven> <mvc:message-converters register-defaults="false">
 * <!-- 避免IE执行AJAX时,返回JSON出现下载文件 --> <bean id="fastJsonHttpMessageConverter"
 * class=
 * "org.springframework.http.converter.json.MappingJackson2HttpMessageConverter "
 * > <property name="supportedMediaTypes"> <list>
 * <value>text/html;charset=UTF-8</value>
 * <value>application/json;charset=UTF-8</value>
 * <value>text/json;charset=UTF-8</value> </list> </property> </bean>
 * </mvc:message-converters> </mvc:annotation-driven>
 * 
 * @author lihp
 * @date 2017-3-17 上午09:55:30
 */
@Controller
public class ImmediatePayNotifyController {

	private static final Logger logger = LoggerFactory.getLogger(ImmediatePayNotifyController.class);

	/**
	 * 支付平台异步通知更新   用这个demo需要xml配置json转化格式，不然回调接收异常，具体配置参考类注释
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tradepayapi/receiveNotify", method = RequestMethod.POST)
	@ResponseBody
	public NotifyResponseBean receiveNotify(@RequestBody BusinessNoticeBean businessNoticeBean) {
		logger.info("notify request:" + businessNoticeBean.toString());
		NotifyResponseBean responseBean = new NotifyResponseBean();
		boolean signCheck = TraderRSAUtil.checksign(PaymentConstant.PUBLIC_KEY_ONLINE,
				SignUtil.genSignData(JSONObject.parseObject(JSONObject.toJSONString(businessNoticeBean))),
				businessNoticeBean.getSign());
		if (!signCheck) {
			// 传送数据被篡改，可抛出异常，再人为介入检查原因
			logger.error("返回结果验签异常,可能数据被篡改");
			// 回调内容先验签，再处理相应逻辑
			responseBean.setRet_code("9999");
			responseBean.setRet_msg("未知异常");
		}
		if (businessNoticeBean.getResult_pay().equals(PaymentStatusEnum.PAYMENT_SUCCESS.getValue())) {
			// TODO 商户更新订单为成功，处理自己的业务逻辑
		} else if (businessNoticeBean.getResult_pay().equals(PaymentStatusEnum.PAYMENT_FAILURE.getValue())) {
			// TODO 商户更新订单为失败，处理自己的业务逻辑
		} else {
			// TODO 返回订单为退款状态 ，商户可以更新订单为失败或者退款
			// 退款这种情况是极小概率情况下才会发生的，个别银行处理机制是先扣款后再打款给用户时，
			// 才检验卡号姓名信息的有效性，当卡号姓名信息有误发生退款，实际上钱没打款到商户。
			// 这种情况商户代码上也可不做考虑，如发生用户投诉未收到钱，可直接联系连连客服，连连会跟银行核对
			// 退款情况，异步通知会通知两次，先通知成功，后通知退款（极小概率情况下才会发生的）
		}
		//回调内容先验签，再处理相应逻辑
		responseBean.setRet_code("0000");
		responseBean.setRet_msg("交易成功");
		return responseBean;
	}

}
