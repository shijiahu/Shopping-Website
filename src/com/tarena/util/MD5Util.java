package com.tarena.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String encode(String str) throws Exception{
		//MD5处理
		MessageDigest md = 
			MessageDigest.getInstance("MD5");
		byte[] dest = md.digest(str.getBytes());
		//Base64处理
		BASE64Encoder base64 = new BASE64Encoder();
		return base64.encode(dest);
	}
	
	public static void main(String[] args){
		try {
			System.out.println(encode("1004121126"));
			System.out.println(encode("11111111111111"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
