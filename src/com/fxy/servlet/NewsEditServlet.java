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
import com.fxy.util.path.PathUtil;


@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
@WebServlet(
		name="NewsEditServlet",
		urlPatterns={"/admin/NewsEditServlet.action"}
		)
public class NewsEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			//初始化需要的对象，根据流程随时收集数据【学会利用对象属性处理问题】
			News news = new News();
			NewsType newsType = new NewsType();
			NewsInfo newsInfo = new NewsInfo();
			Part part;
			

			// 收集旧图片文件名【使用隐藏文本域方案】，因为修改图片，需要删除原有图片===============================================
			String photoAOld = req.getParameter("photoAOld");
			String photoBOld = req.getParameter("photoBOld");
			String photoCOld = req.getParameter("photoCOld");
			//收集图片==============================
			news.setPhotoA(photoAOld);
			news.setPhotoB(photoBOld);
			news.setPhotoC(photoCOld);

			// 是否处理上传的图片
			boolean isPhoto = req.getParameter("isPhoto") != null ? true : false;
			// 收集上传的图片对象
			part = req.getPart("photoA");
			// 如果处理上传图片，那么需要判断有没有图片上传
			if (isPhoto) {
				// 判断上传图片文本有没有文件
				//part.getSize()为0代表根本没有上传文件
				if (part.getSize() ==0) {
					req.getSession().setAttribute("mess", new AdminMess("修改失败,没有任何图片上传", "新闻添加", 2));
					resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
					return;
				}

			}

			// 收集数据===============================================
			int id = Integer.parseInt(req.getParameter("id"));
			String title = req.getParameter("title");
			String author = req.getParameter("author");
			String startInfo = req.getParameter("startInfo");
			String st = req.getParameter("st");
			// 新闻类型
			String type = req.getParameter("type");
			// 新闻内容
			String info = req.getParameter("info");
			// 组合数据===============================================

			news.setId(id);
			news.setTitle(title);
			news.setAuthor(author);
			news.setSt(st);
			news.setStartInfo(startInfo);	
			// 设置类型
			newsType.setId(Integer.parseInt(type));
			news.setNewsType(newsType);
			// 设置内容
			// 新闻内容会按照新闻ID修改，因此需要设置
			newsInfo.setNewsId(news.getId());
			newsInfo.setInfo(info);
			
			// 开启业务===============================================
			NewsServices newsServices = new NewsServices();
			if (newsServices.edit(news, newsInfo,isPhoto,part)) {
				req.getSession().setAttribute("mess", new AdminMess("新闻修改成功", "新闻修改", 1));
			}
			else {
				req.getSession().setAttribute("mess", new AdminMess("新闻修改失败", "新闻修改", 2));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("新闻修改失败", "新闻修改", 3));
		}

		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
	
}
