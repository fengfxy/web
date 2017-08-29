package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Admin;
import com.fxy.services.AdminLoginServices;
import com.fxy.util.log.LogUtil;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="adminLogin",
		urlPatterns={"/adminLoginServlet.action"}
		
		)
public class AdminLoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminLoginServices adminLoginServices=new AdminLoginServices();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(username!=null||password!=null){
			Admin admin=new Admin();
			admin.setUsername(username);
			admin.setPassword(password);
			try {
				admin = adminLoginServices.adminLogin(admin);
				if(admin!=null){
					req.getSession().setAttribute("Admin", admin);
					resp.sendRedirect(PathUtil.getBasePath(req, "admin/newsTypeSelectServlet.action"));
				}else{
					req.getSession().setAttribute("mess", "用户名或者密码错误");
					resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
				}
			} catch (Exception e) {
				
				req.getSession().setAttribute("mess", "服务器内部错误，mybatis错误");
				resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
				e.printStackTrace();
			}
			
		}else{
			req.getSession().setAttribute("mess", "用户名或者密码错误");
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
			
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
