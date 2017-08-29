package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Admin;
import com.fxy.beans.AdminMess;
import com.fxy.services.NewsTypeServices;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="NewsTypeDelServlet",
		urlPatterns={"/admin/NewsTypeDelServlet.action"}
		)
public class NewsTypeDelServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsTypeServices newsTypeServices=new NewsTypeServices();
		try {
			int newsTypeId = Integer.parseInt(req.getParameter("newsTypeId"));
			
			if(newsTypeServices.delete(newsTypeId)){
				req.getSession().setAttribute("mess", new AdminMess("删除类型成功", "删除新闻类型", 1));
		
			}else{
				req.getSession().setAttribute("mess", new AdminMess("删除类型失败", "删除新闻类型", 2));
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("删除类型失败", "删除新闻类型", 2));
			
		}
		
		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
