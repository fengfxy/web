package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fxy.beans.Admin;
import com.fxy.beans.AdminMess;
import com.fxy.services.AdminLoginServices;
import com.fxy.util.log.LogUtil;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="AdminPassWordEditServlet",
		urlPatterns={"/admin/AdminPassWordEditServlet.action"}
		)
public class AdminPassWordEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminLoginServices adminLoginServices=new AdminLoginServices();
		
		try {
			int adminId = Integer.parseInt(req.getParameter("id"));
			Admin admin=null;
			admin=adminLoginServices.findById(adminId);
			if(admin!=null){
				String adminPassWord = req.getParameter("adminPassWord");
				if(!adminPassWord.equals("")){
					admin.setPassword(adminPassWord);
					LogUtil.log(adminPassWord);
					LogUtil.log(admin.getPassword());
					if(adminLoginServices.edit(admin)){
						req.getSession().setAttribute("Admin", admin);
						req.getSession().setAttribute("mess", new AdminMess("修改成功", "管理员密码修改", 1));
					}else{
						req.getSession().setAttribute("mess", new AdminMess("修改失败，数据库修改错误", "管理员密码修改", 2));
					}
				}else{
					req.getSession().setAttribute("mess", new AdminMess("修改失败,传参问题"+req.getParameter("adminPassWord"), "管理员密码修改", 2));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("mess", new AdminMess("修改失败"+e.getMessage(), "管理员密码修改", 3));
		}
		resp.sendRedirect(PathUtil.getBasePath(req, "admin/mess.jsp"));
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
