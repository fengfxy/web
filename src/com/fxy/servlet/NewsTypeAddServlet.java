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
		name="NewsTypeAddServlet",
		urlPatterns={"/admin/newsTypeAddServlet.action"}
		
		)
public class NewsTypeAddServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsTypeServices newsTypeServices=new NewsTypeServices();
		String name = req.getParameter("name");
		if(name!=null && !name.equals("")){
			NewsType record=new NewsType();
			record.setName(name);
			try {
				if(newsTypeServices.insert(record)){
					req.getSession().setAttribute("mess", new AdminMess("添加成功", "添加新闻类型", 1));
				}else{
					req.getSession().setAttribute("mess", new AdminMess("添加失败", "添加新闻类型", 3));
				}
			} catch (Exception e) {
				e.printStackTrace();
				req.getSession().setAttribute("mess", new AdminMess("添加失败", "添加新闻类型", 3));
			}
			
		}else{
			req.getSession().setAttribute("mess", new AdminMess("添加失败", "添加新闻类型", 2));
		}
		
		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
