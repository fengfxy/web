package com.fxy.util.kaptcha;

import javax.servlet.http.HttpServletRequest;

public class KaptchaUtil {
	
	public static String kaptcha(HttpServletRequest request){
		Object kaptcha =request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if(kaptcha==null){
			return null;
		}else{
			return (String) kaptcha;
		}
		
		
		
	}
	
}
