package com.fxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.kaptcha.KaptchaUtil;

@WebServlet(
		name="UsersRegisterServlet",
		urlPatterns={"/servlet/UsersRegisterServlet"}
		)

public class UsersRegisterServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kaptcha = req.getParameter("Kaptcha");
		String str = KaptchaUtil.kaptcha(req);
		PrintWriter out = null;
        //响应数据
        String resultData;
        if(kaptcha=="") {
            resultData = "N";
        }else {
            //比较输入的验证码和实际生成的验证码是否相同
            if(str == null || str == ""||!str.equalsIgnoreCase(kaptcha)) {
                resultData = "N";
            }else {
                resultData = "Y";
            }
        }
        out = resp.getWriter();
        out.write(resultData);
        out.flush();
        
        
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices usersServices=new UsersServices();
		
		String userName = req.getParameter("userName");
        String userPassWord = req.getParameter("userPassWord");
        String userEmail = req.getParameter("userEmail");
        
        
        
	}
	
}
