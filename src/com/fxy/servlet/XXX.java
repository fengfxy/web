package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.NewsType;
import com.fxy.services.NewsTypeServices;
import com.fxy.util.kaptcha.KaptchaUtil;
import com.fxy.util.log.LogUtil;

@WebServlet(
		name="XXX",
		urlPatterns="/servlet/xxx"
		)
public class XXX extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		NewsTypeServices newsTypeServices=new NewsTypeServices();
		NewsType newsType=new NewsType();
		newsType.setId(49);
		newsType.setName("dddddddddd");
		try {
			newsTypeServices.delete(39);
			newsTypeServices.edit(newsType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}
