package com.fxy.util.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.fxy.util.app.AppUtil;

public class MailUtil {

	public static void sendMail(SendMailData sendMailData) throws EmailException {
		HtmlEmail htmlEmail = new HtmlEmail();

		htmlEmail.setCharset("utf-8");
		htmlEmail.setHostName(AppUtil.SC.getInitParameter("host").toString());
		htmlEmail.addTo(sendMailData.getAddress());
		htmlEmail.setFrom(AppUtil.SC.getInitParameter("from").toString(),
				AppUtil.SC.getInitParameter("from").toString());
		htmlEmail.setSSLOnConnect(Boolean.parseBoolean(AppUtil.SC.getInitParameter("ssl").toString()));
		htmlEmail.setSslSmtpPort(AppUtil.SC.getInitParameter("sslSmtpPort").toString());
		htmlEmail.setAuthentication(AppUtil.SC.getInitParameter("from").toString(),
				AppUtil.SC.getInitParameter("pwd").toString());
		htmlEmail.setSubject(sendMailData.getTitle());
		htmlEmail.setMsg(sendMailData.getInfo());
		htmlEmail.send();
	}
}
