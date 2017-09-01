package com.fxy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.AdminMess;
import com.fxy.beans.News;
import com.fxy.beans.NewsInfo;
import com.fxy.beans.NewsType;
import com.fxy.services.NewsInfoServices;
import com.fxy.services.NewsServices;
import com.fxy.services.NewsTypeServices;
import com.fxy.util.path.PathUtil;


@WebServlet(
		name="NewsEditBeforServlet",
		urlPatterns={"/admin/NewsEditBeforServlet.action"}
		)
public class NewsEditBeforServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//需要新闻beans
				//需要新闻类型集合
				//需要新闻内容beans
				
				NewsInfoServices newsInfoServices=new NewsInfoServices();
				NewsServices newsServices=new NewsServices();
				NewsTypeServices newsTypeServices=new NewsTypeServices();
				
				try {
					
					int id= Integer.parseInt(req.getParameter("id")) ;
				  
					ArrayList<NewsType> newsTypes=	newsTypeServices.findAll();
				  
					News news=newsServices.findById(id);
					NewsInfo newsInfo=newsInfoServices.findByNews(id);
					
					req.setAttribute("newsTypes", newsTypes);
					req.setAttribute("news", news);
					req.setAttribute("newsInfo", newsInfo);
					req.getRequestDispatcher("newsEdit.jsp").forward(req, resp);
					
				}
				catch (Exception e) {
					e.printStackTrace();
					req.getSession().setAttribute("mess", new AdminMess("新闻编辑失败", "新闻编辑", 3));
					resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
				}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
