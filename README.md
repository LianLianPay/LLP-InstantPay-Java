# LLP-InstantPay-Java

欢迎来到连连实时付款的Java仓库， 本仓库包含Java接入的示例代码及必要的说明。

## HTTP请求说明

连连统一要求在请求连连提供的接口时使用以下的格式:

```text
curl {API_ENDPOINT} \
-H "Content-type:application/json;charset=utf-8" \
-d '{YOUR_REQUEST_BODY}'
```

## 付款申请API

调用付款申请的示例代码如下， 其中， ```PaymentRequestBean```是预处理请求报文的参数Bean, 组建完成后需要进行[加密](https://zealous-kare-7abde4.netlify.com/docs/send-money/instant/api-encrypt)生成```pay_load```， 之后直接做HTTP请求即可。

> 请求参数的详细说明见[连连开放平台-付款申请API](https://zealous-kare-7abde4.netlify.com/apis/instant-apply)。

```java
public static void main(String[] args) throws Exception {
	// 连连内部测试环境数据(商户测试期间需要用正式的数据测试，测试时默认单笔单日单月额度50，等测试OK，申请走上线流程打开额度）
	PaymentRequestBean paymentRequestBean = new PaymentRequestBean();
	paymentRequestBean.setNo_order("20170413165934");
	paymentRequestBean.setDt_order("20170317165929");
	paymentRequestBean.setMoney_order("0.02");
	paymentRequestBean.setCard_no("6216261000000000000");
	paymentRequestBean.setAcct_name("张三");
	// paymentRequestBean.setBank_name("中国平安银行");
	paymentRequestBean.setInfo_order("test测试10.00?");
	paymentRequestBean.setFlag_card("0");
	paymentRequestBean.setMemo("代付");
	// 填写商户自己的接收付款结果回调异步通知
	paymentRequestBean.setNotify_url("http://10.20.41.35:8080/tradepayapi/receiveNotify.htm");
	paymentRequestBean.setOid_partner(PaymentConstant.OID_PARTNER);
	// paymentRequestBean.setPlatform("test.com");
	paymentRequestBean.setApi_version(PaymentConstant.API_VERSION);
	paymentRequestBean.setSign_type(PaymentConstant.SIGN_TYPE);
	// 用商户自己的私钥加签
	paymentRequestBean.setSign(SignUtil.genRSASign(JSON.parseObject(JSON.toJSONString(paymentRequestBean))));
	String jsonStr = JSON.toJSONString(paymentRequestBean);
	logger.info("实时付款请求报文：" + jsonStr);

	// 用银通公钥对请求参数json字符串加密
	// 报Illegal key
	// size异常时，可参考这个网页解决问题http://www.wxdl.cn/java/security-invalidkey-exception.html
	String encryptStr = LianLianPaySecurity.encrypt(jsonStr, PaymentConstant.PUBLIC_KEY_ONLINE);
	if (StringUtils.isEmpty(encryptStr)) {
		// 加密异常
		logger.error("加密异常:");
		return;
	}

	System.out.println("pay_load: " + encryptStr);

	JSONObject json = new JSONObject();
	json.put("oid_partner","201712210001304164");
	json.put("pay_load", encryptStr);

	String response = HttpUtil.doPost("https://instantpay.lianlianpay.com/paymentapi/payment.htm", json, "UTF-8");
}
```

### 付款申请API返回处理

```java
if (StringUtils.isEmpty(response)) {
	// 出现异常时调用订单查询，明确订单状态，不能私自设置订单为失败状态，以免造成这笔订单在连连付款成功了，而商户设置为失败
	queryPaymentAndDealBusiness(paymentRequestBean.getNo_order());
} else {
	PaymentResponseBean paymentResponseBean = JSONObject.parseObject(response, PaymentResponseBean.class);
	// 对返回0000时验证签名
	if (paymentResponseBean.getRet_code().equals("0000")) {
		// 先对结果验签
		boolean signCheck = TraderRSAUtil.checksign(PaymentConstant.PUBLIC_KEY_ONLINE,
				SignUtil.genSignData(JSONObject.parseObject(response)), paymentResponseBean.getSign());
		if (!signCheck) {
			// 传送数据被篡改，可抛出异常，再人为介入检查原因
			logger.error("返回结果验签异常,可能数据被篡改");
			return;
		}
		logger.info(paymentRequestBean.getNo_order() + "订单处于付款处理中");
		// 已生成连连支付单，付款处理中（交易成功，不是指付款成功，是指跟连连流程正常），商户可以在这里处理自已的业务逻辑（或者不处理，在异步回调里处理逻辑）,最终的付款状态由异步通知回调告知
	} else if (paymentResponseBean.getRet_code().equals("4002")
			|| paymentResponseBean.getRet_code().equals("4004")) {
		// 当调用付款申请接口返回4002，4003，4004,会同时返回验证码，用于确认付款接口
		// 对于疑似重复订单，需先人工审核这笔订单是否正常的付款请求，而不是系统产生的重复订单，确认后再调用确认付款接口或者在连连商户站后台操作疑似订单，api不调用确认付款接口
		// 对于疑似重复订单，也可不做处理，
		// TODO
	} else if (RetCodeEnum.isNeedQuery(paymentResponseBean.getRet_code())) {
		// 出现1002，2005，4006，4007，4009，9999这6个返回码时（或者对除了0000之后的code都查询一遍查询接口）调用付款结果查询接口，明确订单状态，不能私自设置订单为失败状态，以免造成这笔订单在连连付款成功了，而商户设置为失败
		// 第一次测试对接时，返回{"ret_code":"4007","ret_msg":"敏感信息解密异常"},可能原因报文加密用的公钥改动了,demo中的公钥是连连公钥，商户生成的公钥用于上传连连商户站用于连连验签，生成的私钥用于加签
		queryPaymentAndDealBusiness(paymentRequestBean.getNo_order());
	} else {
		// 返回其他code时，可将订单置为失败
		// TODO
		System.out.println("failure");
	}
}
```

## 确认付款API

确认付款API是供[实时付款复核](https://zealous-kare-7abde4.netlify.com/docs/send-money/instant/overview#%E5%AE%9E%E6%97%B6%E4%BB%98%E6%AC%BE%E7%9A%84%E5%A4%8D%E6%A0%B8)时使用的接口， 当付款申请API返回有```confirm_code```时需调用。

> 请求参数的详细说明见[连连开放平台-确认付款API](https://zealous-kare-7abde4.netlify.com/apis/instant-confirm)。

```java
public static void main(String[] args) throws Exception {
	// 连连内部测试环境数据(商户测试期间需要用正式的数据测试，测试时默认单笔单日单月额度50，等测试OK，和连连技术核对过业务对接逻辑后，申请走上线流程打开额度）
	ConfirmPaymentRequestBean paymentRequestBean = new ConfirmPaymentRequestBean();
	paymentRequestBean.setNo_order("20170208165924");
	// 当调用付款接口返回4002，4003，4004时，会返回验证码信息
	paymentRequestBean.setConfirm_code("137007");
	// 填写商户自己的接收付款结果回调异步通知 长度
	paymentRequestBean.setNotify_url("http://192.168.110.13:8080/test/tradepayapi/receiveNotify.htm");
	paymentRequestBean.setOid_partner(PaymentConstant.OID_PARTNER);
	// paymentRequestBean.setPlatform("test.com");
	paymentRequestBean.setApi_version(PaymentConstant.API_VERSION);
	paymentRequestBean.setSign_type(PaymentConstant.SIGN_TYPE);
	// 用商户自己的私钥加签
	paymentRequestBean.setSign(SignUtil.genRSASign(JSON.parseObject(JSON.toJSONString(paymentRequestBean))));
	String jsonStr = JSON.toJSONString(paymentRequestBean);
	// 用银通公钥对请求参数json字符串加密
	// 报Illegal key
	// size异常时，可参考这个网页解决问题http://www.wxdl.cn/java/security-invalidkey-exception.html
	String encryptStr = LianLianPaySecurity.encrypt(jsonStr, PaymentConstant.PUBLIC_KEY_ONLINE);

	if (StringUtils.isEmpty(encryptStr)) {
		// 加密异常
		logger.error("加密异常:");
		return;
	}

	JSONObject json = new JSONObject();
	json.put("oid_partner", PaymentConstant.OID_PARTNER);
	json.put("pay_load", encryptStr);

	String response = HttpUtil.doPost("https://instantpay.lianlianpay.com/paymentapi/confirmPayment.htm", json,
			"UTF-8");
	logger.info("确认付款接口返回响应报文：" + response);
}
```

### 返回处理

确认付款API的返回结果的处理与[付款申请API返回处理](#付款申请API返回处理)相同。

## 加密时提示```Illegal Key Size```的特殊说明

当使用```Java```版本为6， 7或8时， 由于[连连提供的公钥](https://zealous-kare-7abde4.netlify.com/docs/development/signature-key-generation#%E9%85%8D%E7%BD%AE%E8%BF%9E%E8%BF%9E%E5%85%AC%E9%92%A5)超出了加密过程中使用到的[AES](https://en.wikipedia.org/wiki/Advanced_Encryption_Standard)加密方法的默认密钥长度限制， 需要替换您的开发环境中的```local_policy.jar```和```US_export_policy.jar```这两个jar包以去除密钥长度限制， 否则会在加密时抛出异常 ```Illegal Key Size```。

Jar包替换地址为:

```text
${java_home}/jre/lib/security/
```

Jar包下载地址:

[Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 6](http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html)

[Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 7 Download](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)

[Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 8 Download](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)