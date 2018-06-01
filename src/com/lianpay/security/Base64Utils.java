package com.lianpay.security;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

    private static Base64 base64 = new Base64();

    public static String getBASE64(String s)
    {
        if (s == null)
            return null;
        try
        {
            return base64.encode(s.getBytes()).toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getBASE64(byte[] b)
    {
        return base64.encode(b).toString();
    }

    // �?BASE64 编码的字符串 s 进行解码
    public static String getFromBASE64(String s)
    {
        if (s == null)
            return null;
        try
        {
            byte[] b = base64.decode(s).toString().getBytes();
            return new String(b, "UTF-8");
        } catch (Exception e)
        {
            return null;
        }
    }

    // �?BASE64 编码的字符串 s 进行解码
    public static byte[] getBytesBASE64(String s)
    {
        if (s == null)
            return null;
        try
        {
            byte[] b = base64.decode(s).toString().getBytes();
            return b;
        } catch (Exception e)
        {
            return null;
        }
    }
}
