package com.fxy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.News;
import com.fxy.beans.NewsType;
import com.fxy.services.NewsServices;
import com.fxy.services.NewsTypeServices;

@WebServlet(name = "NewsSelectServlet", urlPatterns = { "/admin/NewsSelectServlet.action" }

)
public class NewsSelectServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		NewsServices newsServices = new NewsServices();
		NewsTypeServices newsTypeServices=new NewsTypeServices(); 

		try {
			
			ArrayList<NewsType> newsTypes =newsTypeServices.findAll();
			ArrayList<News> news = newsServices.findByAll();
			req.setAttribute("news", news);
			req.setAttribute("newsTypes", newsTypes);
			req.getSession().setAttribute("menu", 2);
			req.getRequestDispatcher("newsSelect.jsp").forward(req, resp);
		}
		catch (Exception e) {

			e.printStackTrace();
			
			

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
