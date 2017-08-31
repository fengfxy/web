package com.fxy.services;

import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.NewsInfo;
import com.fxy.dao.NewsInfoMapper;
import com.fxy.util.mybatis.MyBatisUtil;


public class NewsInfoServices {

	
	
	
	public boolean add(NewsInfo record) throws Exception{
		boolean result=false;
		
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsInfoMapper newsInfoMapper = sqlSession.getMapper(NewsInfoMapper.class);
		int temp =newsInfoMapper.insert(record);
		sqlSession.commit();
		sqlSession.close();
		if(temp>0){
			result=true;
		}
		
		return result;
	}
	
	
}
