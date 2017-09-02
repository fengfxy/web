package com.fxy.services;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.mail.EmailException;
import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.Users;
import com.fxy.dao.UsersMapper;
import com.fxy.util.encode.EncodeUtil;
import com.fxy.util.mail.MailUtil;
import com.fxy.util.mail.SendMailData;
import com.fxy.util.mybatis.MyBatisUtil;
import com.fxy.util.path.PathUtil;

public class UsersServices {
	public ArrayList<Users> findByAll() throws IOException{
		ArrayList<Users> result=null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		result=(ArrayList<Users>) usersMapper.selectByExample();
		
		return result;
		
	}
	
	public Users findByEmail(String email) throws IOException{
		Users result=null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		result=usersMapper.selectByEmail(email);
		sqlSession.close();
		
		return result;
		
	}
	
	public Users findByVerification(String verification) throws IOException{
		Users result=null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		result=usersMapper.selectByVerification(verification);
		sqlSession.close();
		
		return result;
		
	}
	
	public Users findByUserName(String userName) throws IOException{
		Users result=null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		result=usersMapper.selectByUserName(userName);
		sqlSession.close();
		
		return result;
		
	}
	
	public String add(Users user) throws IOException{
		String result="";
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		try {
			if(usersMapper.insert(user)>0){
				SendMailData sendMailData =new SendMailData();
	    		sendMailData.setAddress(user.getEmail());
	    		sendMailData.setTitle("注册激活");
	    		sendMailData.setInfo("<p><h1>欢迎注册FXY新闻信息系统</h1></p>"
	    				+ "<p>点击下面“立即激活”按钮激活您的账号</p>"
	    				+ "<p><h2 style='color: red;'>链接仅一次有效！</h2></p>"
	    				+ "<a href='http://localhost:8080/Profxy2017/servlet/UsersActivateServlet.action?verification="
	    				+user.getVerification()+"'>立即激活</a>");
	    		try {
	    			MailUtil.sendMail(sendMailData);
	    			sqlSession.commit();
	    			result="注册成功，请注意查收激活邮件";
	    		} catch (EmailException e) {
	    			e.printStackTrace();
	    			result="邮件发送失败，请核对邮箱是否正确";
	    		}
			}else{
				result="插入数据失败，数据有误";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result="注册失败，数据库异常，请稍后重试";
		}
		sqlSession.close();
		return result;
		
	}
	
	public boolean edit(Users user) throws IOException{
		boolean result=false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		if(usersMapper.updateByPrimaryKey(user)>0){
			result=true;
		}
		sqlSession.commit();
		sqlSession.close();
		return result;
		
	}
	
	public boolean remove(Integer userId) throws IOException{
		boolean result=false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		if(usersMapper.deleteByPrimaryKey(userId)>0){
			result=true;
		}
		sqlSession.commit();
		sqlSession.close();
		return result;
		
	}
}

	

