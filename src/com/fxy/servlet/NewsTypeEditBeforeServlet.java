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
		name="NewsTypeEditBeforeServlet",
		urlPatterns={"/admin/NewsTypeEditBeforeServlet.action"}
		)
public class NewsTypeEditBeforeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsTypeServices newsTypeServices=new NewsTypeServices();
		
		try {
			int newsTypeId = Integer.parseInt(req.getParameter("newsTypeId"));
			NewsType newsType = newsTypeServices.findById(newsTypeId);
			if(newsType!=null){
				req.setAttribute("newsType", newsType);
				req.getRequestDispatcher("NewsTypeEdit.jsp").forward(req, resp);
				
			}else{
				req.getSession().setAttribute("mess", new AdminMess("编辑参数错误", "新闻类型编辑", 3));
				resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("编辑参数错误", "新闻类型编辑", 3));
			resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
		
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
