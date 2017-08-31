package com.fxy.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.UUID.UUIDUtil;
import com.fxy.util.mail.MailUtil;
import com.fxy.util.mail.SendMailData;
import com.fxy.util.path.PathUtil;

@WebServlet(name = "ResetUserPassWordServlet", urlPatterns = { "/servlet/ResetUserPassWordServlet.action" })
public class ResetUserPassWordServlet extends HttpServlet {
	UsersServices usersServices = new UsersServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
