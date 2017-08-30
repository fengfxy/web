package com.fxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

import com.fxy.beans.AdminMess;
import com.fxy.beans.Users;
import com.fxy.services.UsersServices;
import com.fxy.util.encode.EncodeUtil;
import com.fxy.util.kaptcha.KaptchaUtil;
import com.fxy.util.mail.MailUtil;
import com.fxy.util.mail.SendMailData;
import com.fxy.util.path.PathUtil;

@WebServlet(
		name="UsersRegisterServlet",
		urlPatterns={"/servlet/UsersRegisterServlet"}
		)

public class UsersRegisterServlet extends HttpServlet{
	int timeGap = 2;
	Long currentTime = new Date().getTime();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String kaptcha = req.getParameter("Kaptcha");
		String str = KaptchaUtil.kaptcha(req);
		PrintWriter out = null;
        //响应数据
        String resultData;
        if(kaptcha=="") {
            resultData = "N";
        }else {
            //比较输入的验证码和实际生成的验证码是否相同
            if(str == null || str == ""||!str.equalsIgnoreCase(kaptcha)) {
                resultData = "N";
            }else {
                resultData = "Y";
            }
        }
        out = resp.getWriter();
        out.write(resultData);
        out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersServices usersServices=new UsersServices();
		
		String userName = req.getParameter("userName");
        String userPassWord = req.getParameter("userPassWord");
        String userEmail = req.getParameter("userEmail");
        String messStr="";
        Users user=new Users();
        Users temp=null;
        try {
			if(usersServices.findByUserName(userName)){
				messStr="用户名已被注册";
			}else{
				temp = usersServices.findByEmail(userEmail);
				if(temp!=null){
					if(temp.getState()!=0){
						messStr="邮箱已被注册";
					}else{
						Long st = Long.parseLong(temp.getSt());
						
						if (currentTime - st > (1000 * 60 * 60 * timeGap)) {
							usersServices.remove(temp.getId());
							messStr="注册";
						}else{
							messStr="邮箱已被注册";
						}
					}
				}else{
					messStr="注册";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			messStr="服务器原因";
		}
        
        if(messStr!="注册"){
        	req.getSession().setAttribute("mess", messStr);
			resp.sendRedirect(PathUtil.getBasePath(req,"mess.jsp"));
        }else{
        	SendMailData sendMailData =new SendMailData();
    		sendMailData.setAddress(userEmail);
    		sendMailData.setTitle("注册激活");
    		sendMailData.setInfo("<p><h1>欢迎注册FXY新闻信息系统</h1></p>"
    				+ "<p>点击下面“立即激活”按钮激活您的账号</p>"
    				+ "<a type='button' href='http://www.baidu.com'>立即激活</a>");
    		
    		try {
    			MailUtil.sendMail(sendMailData);
    			user.setUsername(userName);
    			user.setPassword(EncodeUtil.getMD5(userPassWord));
    			user.setSt(String.valueOf(currentTime));
    			user.setState(0);
    			user.setEmail(userEmail);
    			if(usersServices.add(user)){
    				req.getSession().setAttribute("mess", "注册成功，请注意查收激活邮件");
        			resp.sendRedirect(PathUtil.getBasePath(req,"mess.jsp"));
    			}else{
    				req.getSession().setAttribute("mess", "注册失败，插入数据出错");
        			resp.sendRedirect(PathUtil.getBasePath(req,"mess.jsp"));
    			}
    			
    		} catch (EmailException e) {
    			e.printStackTrace();
    			req.getSession().setAttribute("mess", "邮件发送异常，请检查邮箱是否正确");
    			resp.sendRedirect(PathUtil.getBasePath(req,"mess.jsp"));
    		}
        	
        }
        
        
	}
	
}
