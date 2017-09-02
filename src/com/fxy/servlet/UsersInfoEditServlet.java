package com.fxy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fxy.beans.AdminMess;
import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.app.AppUtil;
import com.fxy.util.path.PathUtil;
import com.fxy.util.upload.UploadFileUtil;

@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
@WebServlet(name = "UsersInfoEditServlet", urlPatterns = { "/serlvet/UsersInfoEditServlet.action" })
public class UsersInfoEditServlet extends HttpServlet {
	String savePath = AppUtil.SC.getRealPath("/admin/upload");
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices usersServices = new UsersServices();
		Users users = new Users();
		Part part;
		String id = req.getParameter("id");
		String photo = req.getParameter("photo");
		String nick = req.getParameter("nick");
		String password = req.getParameter("password");
		
		if(photo!=null||!photo.equals("")){
			
		}
		
		// 收集上传对象Part
		part = req.getPart("photo");

		// 如果没有上传图片直接退回
		if (part.getSize() == 0) {
			req.getSession().setAttribute("mess", "没有上传图片，请选择图片");
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
			return;
		}
		
		try {
			String photoANewFileName = UploadFileUtil.upload(savePath, part);
			users.setPhoto("N" + photoANewFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
