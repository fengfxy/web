package com.fxy.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.UUID.UUIDUtil;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="UsersActivateServlet",
		urlPatterns={"/servlet/UsersActivateServlet.action"}
		
		)
public class UsersActivateServlet extends HttpServlet{
	int timeGap = 2;
	Long currentTime = new Date().getTime();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices usersServices=new UsersServices();
		String uesrVerification = req.getParameter("verification");
		String verification = UUIDUtil.getUUID();
		String messStr="";
		try {
			Users user = usersServices.findByVerification(uesrVerification);
			if(user!=null){
				if(user.getState()!=0){
					messStr="邮箱已被激活";
				}else{
					Long st = Long.parseLong(user.getSt());
					if (currentTime - st > (1000 * 60 * 60 * timeGap)) {
						usersServices.remove(user.getId());
						messStr="该链接已失效";
					}else{
						if(uesrVerification==null||!uesrVerification.equals(user.getVerification())){
							messStr="该链接已失效，已被点击过一次";
						}else{
							user.setState(1);
							user.setVerification(verification);
							if(usersServices.edit(user)){
								messStr="成功激活该账号，请牢记您的用户名："+user.getUsername();
							}else{
								messStr="服务器的问题，账号未激活，请及时联系客服";
							}
						}
					}
				}
			}else{
				messStr="激活失败，该页面被篡改或者链接已失效";
			}
		} catch (Exception e) {
			e.printStackTrace();
			messStr="服务器的问题，账号未激活，请及时联系客服";
		}
		req.getSession().setAttribute("mess", messStr);
		resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
