package com.lianpay.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lianpay.api.util.TraderRSAUtil;
import com.lianpay.constant.PaymentConstant;

public class SignUtil {

	private static Logger logger = LoggerFactory.getLogger(SignUtil.class);


	public static String genRSASign(JSONObject reqObj) {
		// 生成待签名串
		String sign_src = genSignData(reqObj);
		logger.info("商户[" + reqObj.getString("oid_partner") + "]待签名原串" + sign_src);
		return TraderRSAUtil.sign(PaymentConstant.BUSINESS_PRIVATE_KEY, sign_src);
	}

	/**
	 * 生成待签名串
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String genSignData(JSONObject jsonObject) {
		StringBuffer content = new StringBuffer();

		// 按照key做首字母升序排列
		List<String> keys = new ArrayList<String>(jsonObject.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			// sign 和ip_client 不参与签名
			if ("sign".equals(key)) {
				continue;
			}
			String value = (String) jsonObject.getString(key);
			// 空串不参与签名
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);

		}
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}

}
