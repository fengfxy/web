package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Users;
import com.fxy.services.LoginServices;
import com.fxy.util.encode.EncodeUtil;
import com.fxy.util.log.LogUtil;
import com.fxy.util.path.PathUtil;

@WebServlet(name = "LoginServlet", urlPatterns = { "/form/LoginServlet.action" })
public class LoginServlet extends HttpServlet {
	
	


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Users record = new Users();
		record.setUsername(username);
		record.setPassword(EncodeUtil.getMD5(password));
		LoginServices loginServices = new LoginServices();
		Users result;
		try {
			result = loginServices.Login(record);
			if (result != null) {
				LogUtil.log("登陆成功");
				String remFlag = req.getParameter("remFlag");
		         if("1".equals(remFlag)){ //"1"表示用户勾选记住密码
		             /*String cookieUserName = Utils.encrypt(name);
		             String cookiePwd = Utils.encrypt(passWord);
		             String loginInfo = cookieUserName+","+cookiePwd;*/
		             String loginInfo = username+","+password;
		             Cookie userCookie=new Cookie("loginInfo",loginInfo); 
		             userCookie.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
		             userCookie.setPath("/");
		             resp.addCookie(userCookie); 
		         }
				
				req.getSession().setAttribute("Users", result);
				resp.sendRedirect(PathUtil.getBasePath(req,"index.jsp"));
			}
			else {
				LogUtil.log("用户名或者密码错误");
				req.getSession().setAttribute("mess", "用户名或者密码错误");
				resp.sendRedirect(PathUtil.getBasePath(req,"mess.jsp"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			LogUtil.log("用户名或者密码错误");
			req.getSession().setAttribute("mess", "用户名或者密码错误");
			resp.sendRedirect(PathUtil.getBasePath(req,"mess.jsp"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
