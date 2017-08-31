package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.util.log.LogUtil;

@WebServlet(name = "NewsQueryServlet", urlPatterns = { "/admin/NewsQueryServlet.action" }

)
public class NewsQueryServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String[] strings = req.getParameterValues("c1");
		String[] select = req.getParameterValues("type");

		for (String string : strings) {
			LogUtil.log(string);
		}

		for (String string : select) {
			LogUtil.log(string);
		}

		req.getRequestDispatcher("NewsSelectServlet.action").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
