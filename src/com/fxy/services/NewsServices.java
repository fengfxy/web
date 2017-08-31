package com.fxy.services;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.News;
import com.fxy.beans.NewsInfo;
import com.fxy.dao.NewsInfoMapper;
import com.fxy.dao.NewsMapper;
import com.fxy.util.log.LogUtil;
import com.fxy.util.mybatis.MyBatisUtil;

public class NewsServices {

	public ArrayList<News> findByAll() throws Exception {
		ArrayList<News> result = null;

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		result = (ArrayList<News>) newsMapper.selectByExample();
		sqlSession.close();

		return result;

	}

	public boolean add(News record, NewsInfo newsInfo) throws Exception {
		boolean result = false;

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		int temp = newsMapper.insert(record);

		sqlSession.commit();
		sqlSession.close();

		if (temp > 0) {
			NewsInfoServices newsInfoServices = new NewsInfoServices();
			newsInfo.setNewsId(record.getId());
			if (newsInfoServices.add(newsInfo)) {

				result = true;
			}
			else {
				LogUtil.log("内容添加失败!");
			}

		}
		else {
			LogUtil.log("新闻添加失败!");
		}

		return result;

	}

	public boolean removeByNewsId(Integer newsId) throws Exception {
		boolean result = false;

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession(false);
		NewsMapper newsMapper = sqlSession.getMapper(NewsMapper.class);
		NewsInfoMapper newsInfoMapper=sqlSession.getMapper(NewsInfoMapper.class);
		if(newsMapper.deleteByPrimaryKey(newsId)>0){
			if(newsInfoMapper.deleteByNewsId(newsId)>0){
				sqlSession.commit();
				result=true;
			}else{
				result = false;
			}
		}
		sqlSession.close();

		return result;

	}
}
