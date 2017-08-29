package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.AdminMess;
import com.fxy.beans.NewsType;
import com.fxy.services.NewsTypeServices;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="NewsTypeEditServlet",
		urlPatterns={"/admin/NewsTypeEditServlet.action"}
		)

public class NewsTypeEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsTypeServices newsTypeServices =new NewsTypeServices();
		
		try {
			int newsTypeId = Integer.parseInt(req.getParameter("newsTypeId"));
			String newsTypeName = req.getParameter("newsTypeName");
			NewsType newsType =new NewsType();
			newsType.setId(newsTypeId);
			newsType.setName(newsTypeName);
			
			if(newsTypeServices.edit(newsType)){
				req.getSession().setAttribute("mess", new AdminMess("编辑类型成功", "修改新闻类型", 1));
				
			}else{
				req.getSession().setAttribute("mess", new AdminMess("编辑类型失败", "修改新闻类型", 2));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("编辑类型失败", "修改新闻类型", 2));
			
		}
		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
