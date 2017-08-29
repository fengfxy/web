package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

import com.fxy.util.mail.MailUtil;
import com.fxy.util.mail.SendMailData;

@WebServlet(
		name="mmm",
		urlPatterns={"/mm"}
		
		)
public class MMM extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SendMailData sendMailData =new SendMailData();
		sendMailData.setAddress("1721023048@qq.com");
		sendMailData.setTitle("halou");
		sendMailData.setInfo("<a href='http://www.baidu.com'>asdf</a>");
		
		try {
			MailUtil.sendMail(sendMailData);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	
}
