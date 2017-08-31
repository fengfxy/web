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
import com.fxy.beans.NewsType;
import com.fxy.services.NewsServices;
import com.fxy.services.NewsTypeServices;
import com.fxy.util.path.PathUtil;

@WebServlet(name = "NewsDelServlet", urlPatterns = { "/admin/NewsDelServlet.action" }

)
public class NewsDelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		NewsServices newsServices = new NewsServices();

		try {
			int newsId = Integer.parseInt(req.getParameter("id"));
			if(newsServices.removeByNewsId(newsId)){
				req.getSession().setAttribute("mess", new AdminMess("删除成功", "新闻数据删除", 1));
			}else{
				req.getSession().setAttribute("mess", new AdminMess("删除失败", "新闻数据删除", 2));
			}
		}
		catch (Exception e) {

			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("删除失败"+e.getMessage(), "新闻数据删除", 3));
		}
		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
