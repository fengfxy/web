package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.AdminMess;
import com.fxy.beans.News;
import com.fxy.beans.NewsInfo;
import com.fxy.services.NewsInfoServices;
import com.fxy.services.NewsServices;
import com.fxy.util.path.PathUtil;


@WebServlet(
		name="NewsInfoSelectServlet",
		urlPatterns={"/admin/NewsInfoSelectServlet.action"}
		)
public class NewsInfoSelectServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			NewsServices newsServices = new NewsServices();
			NewsInfoServices newsInfoServices = new NewsInfoServices();
			News news = null;
			NewsInfo newsInfo = null;
			news = newsServices.findById(id);
			newsInfo = newsInfoServices.findByNews(id);

			if (news != null && newsInfo != null) {

				req.setAttribute("news", news);
				req.setAttribute("newsInfo", newsInfo);
				req.getRequestDispatcher("newsInfo.jsp").forward(req, resp);

			}
			else {
				req.getSession().setAttribute("mess", new AdminMess("新闻内容查询失败", "新闻内容查看", 2));
				resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
			}

		}
		catch (Exception e) {
			req.getSession().setAttribute("mess", new AdminMess("新闻内容查询失败", "新闻内容查看", 2));
			resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
