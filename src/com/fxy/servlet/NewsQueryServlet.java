package com.fxy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.News;
import com.fxy.beans.NewsQuery;
import com.fxy.beans.NewsType;
import com.fxy.services.NewsServices;
import com.fxy.services.NewsTypeServices;

@WebServlet(name = "NewsQueryServlet", urlPatterns = { "/admin/NewsQueryServlet.action" }

)
public class NewsQueryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 收集查询条件,没有打勾勾则视为null【利用null的方法视为不选择】

				NewsQuery newsQuery = new NewsQuery();

				String title = req.getParameter("title");
				if (title != null && title.length() > 0) {
					newsQuery.setTitle(title);
				}

				String stStart = req.getParameter("stStart");
				String stEnd = req.getParameter("stEnd");

				if (stStart != null && stStart.length() > 0 && stEnd != null && stEnd.length() > 0) {
					newsQuery.setStStart(stStart);
					newsQuery.setStEnd(stEnd);
				}

				String[] select = req.getParameterValues("type");
				if (select != null && select[0] != null) {

					try {

						int temp = Integer.parseInt(select[0]);
						if (temp > 0) {
							newsQuery.setNewsTypeId(temp);
						}
					}
					catch (Exception e) {

					}

				}

				try {
					NewsServices newsServices = new NewsServices();
					NewsTypeServices newsTypeServices = new NewsTypeServices();
					ArrayList<NewsType> newsTypes = newsTypeServices.findAll();
					ArrayList<News> news = newsServices.findByQuery(newsQuery);
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
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
