package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.AdminMess;
import com.fxy.services.UsersServices;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="ClearUsersServlet",
		urlPatterns={"/admin/ClearUsersServlet.action"}
		
		)
public class ClearUsersServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices usersServices =new UsersServices();
		int result=0;
		try {
			result = usersServices.rmoveDate();
			req.getSession().setAttribute("mess", new AdminMess("清理成功,共清理了"+result, "垃圾清理", 1));
			
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("清理失败,鸟的问题", "垃圾清理", 2));
		}
		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
