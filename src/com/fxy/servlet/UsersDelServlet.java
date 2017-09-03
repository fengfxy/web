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
		name="UsersDelServlet",
		urlPatterns={"/admin/UsersDelServlet.action"}
		)
public class UsersDelServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices newsTypeServices=new UsersServices();
		try {
			int usersId = Integer.parseInt(req.getParameter("usersId"));
			
			if(newsTypeServices.remove(usersId)){
				req.getSession().setAttribute("mess", new AdminMess("删除用户成功", "删除用户", 1));
			}else{
				req.getSession().setAttribute("mess", new AdminMess("删除用户失败", "删除用户", 2));
			}
		}catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("删除用户失败", "删除用户", 2));
		}
		
		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
