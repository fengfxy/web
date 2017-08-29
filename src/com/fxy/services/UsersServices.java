package com.fxy.services;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.Users;
import com.fxy.dao.UsersMapper;
import com.fxy.util.mybatis.MyBatisUtil;

public class UsersServices {
	public ArrayList<Users> findAll(){
		ArrayList<Users> result=null;
		
		
		
		
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
}

	

