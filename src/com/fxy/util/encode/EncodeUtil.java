package com.fxy.util.encode;

import org.apache.commons.codec.digest.DigestUtils;

public class EncodeUtil {
	
	public static String getMD5(String str){
		return DigestUtils.md5Hex(str);
	}
	
}
