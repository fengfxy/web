package com.fxy.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
		name="Kaptcha",
		urlPatterns={"/Kaptcha.jpg"},
		initParams={
				
			@WebInitParam(name="kaptcha.border",value="no"),
			@WebInitParam(name="kaptcha.textproducer.font.color",value="green"),
			@WebInitParam(name="kaptcha.image.width",value="135"),
			@WebInitParam(name="kaptcha.textproducer.char.string",value="ACDEFGHIJKLMNOPQRSTUVWXZ123456790"),
			@WebInitParam(name="kaptcha.image.height",value="40"),
			@WebInitParam(name="kaptcha.textproducer.font.size",value="35"),
			@WebInitParam(name="kaptcha.noise.color",value="red"),
			@WebInitParam(name="kaptcha.textproducer.char.length",value="4"),
			@WebInitParam(name="kaptcha.textproducer.font.names",value="Arial"),
			@WebInitParam(name="kaptcha.obscurificator.impl",value="com.google.code.kaptcha.impl.ShadowGimpy"),
		},
		//设置程序启动跟随启动
		loadOnStartup=1
		
		
		)
public class FXYKaptcha extends com.google.code.kaptcha.servlet.KaptchaServlet{

}
