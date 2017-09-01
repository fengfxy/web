package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fxy.beans.AdminMess;
import com.fxy.beans.News;
import com.fxy.beans.NewsInfo;
import com.fxy.beans.NewsType;
import com.fxy.services.NewsServices;
import com.fxy.util.log.LogUtil;
import com.fxy.util.path.PathUtil;


@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
@WebServlet(name = "NewsAddServlet", urlPatterns = { "/admin/NewsAddServlet.action" })
public class NewsAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 基本流程
		// 1:收集数据、包含基本数据和上传文件对象Part
		// 2:上传文件为null直接重定向
		// 3:处理业务
		
		try {

			//初始化需要的对象，根据流程随时收集数据【学会利用对象属性处理问题】
			News news = new News();
			NewsType newsType = new NewsType();
			NewsInfo newsInfo = new NewsInfo();
			Part part;
			
			// 收集上传对象Part
			part = req.getPart("photoA");

			// 如果没有上传图片直接退回
			if (part.getSize() == 0) {
				req.getSession().setAttribute("mess", new AdminMess("添加失败,没有任何图片上传", "新闻添加", 2));
				resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
				return;
			}

			// 收集数据
			String title = req.getParameter("title");
			String author = req.getParameter("author");
			String startInfo = req.getParameter("startInfo");
			String st = req.getParameter("st");
			String info = req.getParameter("info");
			String type = req.getParameter("type");

			// 组装新闻类型数据			
			news.setTitle(title);
			news.setAuthor(author);
			news.setSt(st);
			news.setStartInfo(startInfo);

			// 组装新闻类型
			newsType.setId(Integer.parseInt(type));
			news.setNewsType(newsType);

			// 组装新闻内容数据			
			newsInfo.setInfo(info);

			NewsServices newsServices = new NewsServices();
			if (newsServices.add(news, newsInfo, part)) {
				LogUtil.log("***************新闻添加成功");
				req.getSession().setAttribute("mess", new AdminMess("添加成功", "新闻添加", 1));
			}
			else {
				LogUtil.log("***************新闻添加失败");
				req.getSession().setAttribute("mess", new AdminMess("添加失败", "新闻添加", 2));
			}

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.log("***************新闻添加失败，鸟的问题");
			req.getSession().setAttribute("mess", new AdminMess("添加失败" + e.getMessage(), "新闻添加", 3));
		}

		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
