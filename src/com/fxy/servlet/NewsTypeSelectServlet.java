package com.fxy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.NewsType;
import com.fxy.services.NewsTypeServices;

@WebServlet(
		name="NewsTypeSelectServlet",
		urlPatterns={"/admin/newsTypeSelectServlet.action"}
		
		)
public class NewsTypeSelectServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsTypeServices newsTypeServices=new NewsTypeServices();
		try {
			ArrayList<NewsType> NewsTypes = newsTypeServices.findAll();
			req.setAttribute("NewsTypes", NewsTypes);
			req.getRequestDispatcher("NewsTypeSelect.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
