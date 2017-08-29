package com.fxy.services;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.News;
import com.fxy.beans.NewsType;
import com.fxy.dao.NewsMapper;
import com.fxy.dao.NewsTypeMapper;
import com.fxy.util.mybatis.MyBatisUtil;

public class NewsTypeServices {

	public ArrayList<NewsType> findAll() throws IOException{
		ArrayList<NewsType> result=null;
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsTypeMapper newsTypeMapper=sqlSession.getMapper(NewsTypeMapper.class);
		result=(ArrayList<NewsType>) newsTypeMapper.selectByExample(null);
		sqlSession.close();
		return result;
		
	}
	
	public NewsType findById(Integer newsTypeId) throws IOException{
		NewsType result=null;
		
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsTypeMapper newsTypeMapper=sqlSession.getMapper(NewsTypeMapper.class);
		result=newsTypeMapper.selectByPrimaryKey(newsTypeId);
		sqlSession.close();
		return result;
		
		
	}
	
	public boolean insert(NewsType newsType) throws IOException{
		boolean result=false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		NewsTypeMapper newsTypeMapper=sqlSession.getMapper(NewsTypeMapper.class);
		
		if(newsTypeMapper.insert(newsType)>0){
			result= true;
		}else{
			result= false;
		}
		
		sqlSession.commit();
		sqlSession.close();
		return result;
	}
	
	public boolean edit(NewsType newsType) throws Exception{
		boolean result=false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		NewsTypeMapper newsTypeMapper=sqlSession.getMapper(NewsTypeMapper.class);
		
		if(newsTypeMapper.updateByPrimaryKey(newsType)>0){
			result=true;
			sqlSession.commit();
		}else{
			throw new Exception(){

				@Override
				public String getMessage() {
					
					return "修改类型出错，数据库没变化";
				}
				
			};
		}
		
		sqlSession.close();
		return result;
	}
	
	public boolean delete(int newsTypeId) throws Exception{
		boolean result=false;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		NewsTypeMapper newsTypeMapper=sqlSession.getMapper(NewsTypeMapper.class);
		NewsMapper newsMapper=sqlSession.getMapper(NewsMapper.class);
		NewsType newsType=new NewsType();
		newsType.setId(newsTypeId);
		
		if(newsMapper.selectByTypeCount(newsType)>0){
			throw new Exception(){

				@Override
				public String getMessage() {
					
					return "不能删除该类型，该类型下有新闻";
				}
				
			};
		}else{
			if(newsTypeMapper.deleteByPrimaryKey(newsTypeId)>0){
				
				result=true;
				sqlSession.commit();
			}else{
				throw new Exception(){

					@Override
					public String getMessage() {
						
						return "删除类型出错，数据库没变化";
					}
					
				};
				
			}
			
		}
		
		
		sqlSession.close();
		return result;
	}
	
}
