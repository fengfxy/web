package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.AdminMess;
import com.fxy.beans.News;
import com.fxy.beans.NewsInfo;
import com.fxy.beans.NewsType;
import com.fxy.services.NewsServices;
import com.fxy.util.log.LogUtil;
import com.fxy.util.path.PathUtil;
import com.fxy.util.upload.UploadFileUtil;


@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
@WebServlet(name = "NewsAddServlet", urlPatterns = { "/admin/NewsAddServlet.action" })
public class NewsAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 收集数据
		String title = req.getParameter("title");
		String author = req.getParameter("author");
		String startInfo = req.getParameter("startInfo");
		String st = req.getParameter("st");
		String info = req.getParameter("info");
		String type = req.getParameter("type");

		// String info=req.getParameter("info");

		// 上传数据

		LogUtil.log("UploadFileSrvlet");
		String savePath = "/admin/upload";
		LogUtil.log("savePath:" + savePath);

		try {
			String photoA = UploadFileUtil.upload(req, savePath, "photoA");

			LogUtil.log("OK");

			News news = new News();

			news.setTitle(title);
			news.setAuthor(author);
			news.setSt(st);
			news.setPhotoA(photoA);
			news.setStartInfo(startInfo);

			NewsType newsType = new NewsType();
			newsType.setId(Integer.parseInt(type));

			news.setNewsType(newsType);

			NewsInfo newsInfo = new NewsInfo();
			newsInfo.setInfo(info);

			NewsServices newsServices = new NewsServices();
			if (newsServices.add(news, newsInfo)) {

				LogUtil.log("OK");
				req.getSession().setAttribute("mess", new AdminMess("添加成功", "新闻添加", 1));
			}
			else {
				LogUtil.log("NO");
				req.getSession().setAttribute("mess", new AdminMess("添加失败", "新闻添加", 2));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			LogUtil.log("NO");
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
