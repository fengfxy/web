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
		name="NewsInfoViewServlet",
		urlPatterns={"/NewsInfoViewServlet.action"}
		)
public class NewsInfoViewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mess="";
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
				req.getRequestDispatcher("newsInfoView.jsp").forward(req, resp);

			}
			else {
				mess="新闻内容查询失败,没有该新闻";
			}

		}
		catch (Exception e) {
			mess="新闻内容查询失败,异常原因"+e.getMessage();
		}
		
		req.getSession().setAttribute("mess", mess);
		resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
