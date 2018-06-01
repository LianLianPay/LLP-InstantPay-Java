package com.lianpay.constant;

public class PaymentConstant {

	/** 连连银通公钥（不需替换，这是连连公钥，用于报文加密和连连返回响应报文时验签，不是商户生成的公钥. */
	public static final String PUBLIC_KEY_ONLINE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";

	/** 商户私钥 商户通过openssl工具生成公私钥，公钥通过商户站上传，私钥用于加签，替换下面的值 . */
	public static final String BUSINESS_PRIVATE_KEY = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAK5oCtldgp6A0cdjiRjYTJ3fFxBZ55bmFqNxCSICEMp4loS2a0Qm42bov5V3zmmgbhnOJh13dEZU8a1SEeoiYiiKE0F5Pan+RPt329F5YXLfT0h87N3YPmzfx2xiSJa3snmLy0gQHyo/+TfrO59HnY3DuB7aX0U5MkSMQauBiORLAgMBAAECgYEArQiCgwe4eQN7nePN+D1ZPmRA4LMiBt9+5GdYVUpRWF/tjfviTnp6sPYIZgW4X6mQsr+Jp0CFtuW95WSAa5fzY3ulxchf34Hxt1j4h/9IcMAc0qPdB0HTF7M06eEpVLZ5GnbpepStB18HBaGO66kqnwUZ38Hub2EZ8vwN8O8+q1ECQQDaATZ7W9TmPovPhUlerbYEzM2v2IpkhIaziDpwV152bta215fql1iwKINibCta/PYU7spZSLcNpJumHz93OUYJAkEAzM2W2hgujCnZm8trR+c7aY6tlo/8GSRRvFVxnfUOGKYdALXGGYoWE5donH5/2Yfkzzivqar/e/kc+qbFTLCMswJBAL2WRG0vRY0eY7QLM+1UoHC4M0Bzzpbv8bz8AeZk9M+GQNAt2f23tPctpGTZsTKlvtQhfnP7GsaQmpPzpNvoQRECQQCeGmRHT323pKMiG2Jhase50HR/k/345snWi1ufpktQigQ/xRP+KVSroSoYDavjIX5o3oj1gVWjvgc6FL6hWnXzAkEAlyzQsNoFu65uN8Cdc3bHm01H6UHsCdjR5p3KGK3RbAydAbbXInE3003duU/JxWh8P7fVpJohXQc4eh4bIBEhuw==";

	/** 商户号（商户需要替换自己的商户号）. */
	public static final String OID_PARTNER = "201609130000219023";

	/** 实时付款api版本. */
	public static final String API_VERSION = "1.0";

	/** 实时付款签名方式. */
	public static final String SIGN_TYPE = "RSA";

}
