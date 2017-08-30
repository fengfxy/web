package com.fxy.services;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.Users;
import com.fxy.dao.UsersMapper;
import com.fxy.util.mybatis.MyBatisUtil;

public class UsersServices {
	public ArrayList<Users> findByAll(){
		ArrayList<Users> result=null;
		
		
		
		
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
	
	public boolean findByUserName(String userName) throws IOException{
		boolean result=true;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		if(usersMapper.selectByUserName(userName)==null){
			result=false;
		}
		sqlSession.close();
		
		return result;
		
	}
	
	public boolean add(Users user) throws IOException{
		boolean result=false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		UsersMapper usersMapper=sqlSession.getMapper(UsersMapper.class);
		if(usersMapper.insert(user)>0){
			result=true;
		}
		sqlSession.commit();
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

	

