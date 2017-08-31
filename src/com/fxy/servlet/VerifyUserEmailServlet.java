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
import com.fxy.util.mail.MailUtil;
import com.fxy.util.mail.SendMailData;
import com.fxy.util.path.PathUtil;

@WebServlet(name = "VerifyUserEmailServlet", urlPatterns = { "/servlet/VerifyUserEmailServlet.action" })
public class VerifyUserEmailServlet extends HttpServlet {
	int timeGap = 2;
	Long currentTime = new Date().getTime();
	UsersServices usersServices = new UsersServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uesrVerification = req.getParameter("verification");
		String messStr="";
		try {
			Users user = usersServices.findByVerification(uesrVerification);
			if(user!=null){
				if(uesrVerification==null||!uesrVerification.equals(user.getVerification())){
					messStr="该页面被篡改或者链接已失效，请重新验证";
				}else{
					messStr="验证通过";
				}
			
			}else{
				messStr="该账号未激活，该页面被篡改或者链接已失效，请及时联系客服";
			}
		} catch (Exception e) {
			e.printStackTrace();
			messStr="服务器的问题，账号未激活，请及时联系客服";
		}
		if(messStr.equals("验证通过")){
			resp.sendRedirect(PathUtil.getBasePath(req, "ReSetUserPassWord.jsp"));
		}else{
			req.getSession().setAttribute("mess", messStr);
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String userEmail = req.getParameter("userEmail");
		
		String messStr = "";
		Users temp = null;
		try {

			if (usersServices.findByUserName(username) != null) {
				temp = usersServices.findByUserName(username);
			} else if (usersServices.findByEmail(userEmail) != null) {
				temp = usersServices.findByEmail(userEmail);
				if (temp.getState() != 0) {
					messStr = "找回";
				} else {
					Long st = Long.parseLong(temp.getSt());
					if (currentTime - st > (1000 * 60 * 60 * timeGap)) {
						usersServices.remove(temp.getId());
						messStr = "该账户长时间未激活，请重新注册";
					} else {
						messStr = "该账户未激活，请迅速激活";
					}
				}
			} else {
				messStr = "用户名或者邮箱不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
			messStr = "服务器原因，请及时联系客服";
		}

/*****************************************************************************
* 								上面初步判断，下面真正逻辑							 *
******************************************************************************/

		if (messStr != "找回") {
			req.getSession().setAttribute("mess", messStr);
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
		} else {
			SendMailData sendMailData = new SendMailData();
			sendMailData.setAddress(userEmail);
			sendMailData.setTitle("密码找回");
			sendMailData.setInfo("<p><h1>FXY新闻信息系统——用户密码找回验证</h1></p>" + "<p>点击下面“立即验证”按钮验证您的账号</p>"
					+ "<a href='http://localhost:8080/Profxy2017/servlet/VerifyUserEmailServlet.action?verification="
					+ temp.getVerification() + "'>立即验证</a>");
			try {
				MailUtil.sendMail(sendMailData);
				messStr = "验证邮件已发送到注册邮箱，请注意查收";
			} catch (EmailException e) {
				e.printStackTrace();
				messStr = "邮件发送失败，请核对邮箱是否正确";
			}

			req.getSession().setAttribute("mess", messStr);
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
		}
	}

}
