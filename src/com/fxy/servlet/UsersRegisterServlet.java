package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.util.kaptcha.KaptchaUtil;
import com.fxy.util.log.LogUtil;

@WebServlet(
		name="UsersRegisterServlet",
		urlPatterns={"/servlet/UsersRegisterServlet"}
		)

public class UsersRegisterServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kaptcha = req.getParameter("Kaptcha");
		String str = KaptchaUtil.kaptcha(req);
		
		if(str!=null&&str.equalsIgnoreCase(kaptcha)){
			LogUtil.log("OK");
		}else{
			LogUtil.log("No");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
