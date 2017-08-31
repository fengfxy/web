package com.fxy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.News;
import com.fxy.beans.NewsType;
import com.fxy.services.NewsServices;
import com.fxy.services.NewsTypeServices;

@WebServlet(name = "NewsDelServlet", urlPatterns = { "/admin/NewsDelServlet.action" }

)
public class NewsDelServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		NewsServices newsServices = new NewsServices();

		try {
			
			
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
