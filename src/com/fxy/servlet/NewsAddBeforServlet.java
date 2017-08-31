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
		name="NewsAddBeforServlet",
		urlPatterns={"/admin/NewsAddBeforServlet.action"}
		)
public class NewsAddBeforServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsTypeServices newsTypeServices = new NewsTypeServices();

		try {

			ArrayList<NewsType> newsTypes = newsTypeServices.findAll();

			req.setAttribute("newsTypes", newsTypes);

			req.getRequestDispatcher("newsAdd.jsp").forward(req, resp);
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
