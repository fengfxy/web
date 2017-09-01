package com.fxy.dao;

import com.fxy.beans.NewsInfo;
import java.util.List;

public interface NewsInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(NewsInfo record);

	List<NewsInfo> selectByExample();

	NewsInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(NewsInfo record);
	
	int updateByNews(NewsInfo record);
	
	int deleteByNews(Integer newsId);
	NewsInfo selectByNews(Integer newsId);
}