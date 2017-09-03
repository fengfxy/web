package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.UUID.UUIDUtil;
import com.fxy.util.encode.EncodeUtil;
import com.fxy.util.path.PathUtil;

@WebServlet(name = "ResetUserPassWordServlet", urlPatterns = { "/servlet/ResetUserPassWordServlet.action" })
public class ResetUserPassWordServlet extends HttpServlet {
	UsersServices usersServices = new UsersServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userPassWord = req.getParameter("userPassWord");
        String userEmail = req.getParameter("userEmail");
        String messStr="";
        Users user=null;
        try {
			user= usersServices.findByEmail(userEmail);
			if(user!=null){
				user.setPassword(EncodeUtil.getMD5(userPassWord));
				user.setVerification(UUIDUtil.getUUID());
				try {
					if(usersServices.edit(user)){
						messStr="修改成功，请牢记该密码";
					}else{
						messStr="修改失败，数据库的问题";
					}
				} catch (Exception e) {
					e.printStackTrace();
					messStr="服务器异常，请及时联系客服";
				}
			}else{
				messStr="该页面已被篡改，请及时联系客服";
			}
		} catch (Exception e) {
			e.printStackTrace();
			messStr="服务器异常，请及时联系客服";
		}
        req.getSession().setAttribute("mess", messStr);
        resp.sendRedirect(PathUtil.getBasePath(req,"mess.jsp"));
        
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
