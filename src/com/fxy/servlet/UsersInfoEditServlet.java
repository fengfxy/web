package com.fxy.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.app.AppUtil;
import com.fxy.util.encode.EncodeUtil;
import com.fxy.util.log.LogUtil;
import com.fxy.util.path.PathUtil;
import com.fxy.util.upload.UploadFileUtil;

@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
@WebServlet(name = "UsersInfoEditServlet", urlPatterns = { "/serlvet/UsersInfoEditServlet.action" })
public class UsersInfoEditServlet extends HttpServlet {
	String savePath = AppUtil.SC.getRealPath("/userPhoto");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices usersServices = new UsersServices();
		Users users = null;
		String messStr = "";
		String reqType = req.getParameter("reqType");
		int id = Integer.parseInt(req.getParameter("id"));
		
		users = usersServices.findById(id);
		if(users==null){
			req.getSession().setAttribute("mess", "数据库无此用户");
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
			return;
		}
		LogUtil.log(req.getParameter("reqType"));
		if (req.getParameter("reqType") != null || !reqType.equals("")) {
			switch (reqType) {
			case "修改昵称":
				String nick = req.getParameter("nick");
				if(req.getParameter("nick")!=null||!users.equals("")){
					users.setNick(nick);
					if(!usersServices.edit(users)){
						messStr = "昵称修改失败，数据库更改失败";
					}else{
						messStr="OK";
						
					}
				}else{
					messStr="请求数据有问题——昵称修改";
				}
				
				break;
			case "修改头像":
				Part part = req.getPart("photo");
				
				// 如果没有上传图片直接退回
				if (part.getSize() == 0) {
					messStr = "没有上传图片，请选择图片";
					break;
				} else {
					try {
						String photoANewFileName = UploadFileUtil.upload(savePath, part);
						LogUtil.log(users.getPhoto());
						LogUtil.log(users.getUsername());
						if(users.getPhoto()!=null){
							if(!users.getPhoto().equals("")){
								
								String photo = users.getPhoto().substring(1);
								String fA = savePath + File.separator + "A"+ photo;
								String fB = savePath + File.separator + "B"+ photo;
								String fC = savePath + File.separator + "C"+ photo;
								File fileA = new File(fA);
								File fileB = new File(fB);
								File fileC = new File(fC);
								// 删除图片成功
								if (fileA.exists()) {
									fileA.delete();
								}
								if (fileC.exists()) {
									fileC.delete();
								}
								if (fileB.exists()) {
									fileB.delete();
								}
								users.setPhoto("B" + photoANewFileName);
								if (!usersServices.edit(users)) {
									messStr = "图片上传失败，请稍后再试";
								}else{
									messStr="OK";
									
								}
							}else{
								users.setPhoto("B" + photoANewFileName);
								if (!usersServices.edit(users)) {
									messStr = "图片上传失败，请稍后再试";
								}else{
									messStr="OK";
									
								}
							}
						}else{
							users.setPhoto("B" + photoANewFileName);
							if (!usersServices.edit(users)) {
								messStr = "图片上传失败，请稍后再试";
							}else{
								messStr="OK";
								
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
						messStr = "图片上传失败，请稍后再试";
					}
				}

				break;
			case "修改密码":
				String password = req.getParameter("userPassWord");
				if(req.getParameter("userPassWord")!=null||!password.equals("")){
					users.setPassword(EncodeUtil.getMD5(password));
					if(!usersServices.edit(users)){
						messStr="密码修改失败";
					}else{
						messStr="OK";
						
					}
				}else{
					messStr="请求数据有问题，密码修改";
				}
				break;
			}
		} else {
			messStr = "没有任何请求";
		}
		
		
		if(messStr.equals("OK")){
			req.getSession().setAttribute("menu", 11);
			req.getSession().setAttribute("Users", users);
			resp.sendRedirect(PathUtil.getBasePath(req, "user.jsp"));
		}else{
			req.getSession().setAttribute("mess", messStr);
			resp.sendRedirect(PathUtil.getBasePath(req, "mess.jsp"));
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
