package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="UsersInfoEditBeforeServlet",
		urlPatterns={"/UsersInfoEditBeforeServlet.action"}
		)
public class UsersInfoEditBeforeServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices usersServices = new UsersServices();
		Users users = null;
		String messStr = "";
		String reqType = req.getParameter("reqType");
		int id = Integer.parseInt(req.getParameter("id"));
		
		users = usersServices.findById(id);
		if(users==null){
			req.getSession().setAttribute("mess", "抱歉，无此用户");
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
			return;
		}
		
		if (req.getParameter("reqType") != null || !reqType.equals("")) {
			switch (reqType) {
			case "q":
				req.getSession().setAttribute("menu", 11);
				req.getSession().setAttribute("Users", users);
				resp.sendRedirect(PathUtil.getBasePath(req, "user.jsp"));
				break;
			case "w":
				req.getSession().setAttribute("menu", 12);
				req.getSession().setAttribute("Users", users);
				resp.sendRedirect(PathUtil.getBasePath(req, "userPhoto.jsp"));
				break;
			case "r":
				req.getSession().setAttribute("menu", 13);
				req.getSession().setAttribute("Users", users);
				resp.sendRedirect(PathUtil.getBasePath(req, "userPassWord.jsp"));
				break;
			}
		} else {
			messStr = "没有任何请求";
			req.getSession().setAttribute("mess", messStr);
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
