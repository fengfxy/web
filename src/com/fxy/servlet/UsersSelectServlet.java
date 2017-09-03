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
import com.fxy.beans.Users;
import com.fxy.services.NewsServices;
import com.fxy.services.NewsTypeServices;
import com.fxy.services.UsersServices;

@WebServlet(name = "UsersSelectServlet", urlPatterns = { "/admin/UsersSelectServlet.action" }

)
public class UsersSelectServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UsersServices usersServices = new UsersServices();

		try {
			
			ArrayList<Users> users = usersServices.findByAll();
			req.setAttribute("users", users);
			req.getSession().setAttribute("menu", 4);
			req.getRequestDispatcher("UsersSelect.jsp").forward(req, resp);
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
