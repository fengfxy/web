package com.fxy.dao;

import com.fxy.beans.NewsInfo;
import java.util.List;

public interface NewsInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int deleteByNewsId(Integer id);
	int insert(NewsInfo record);

	List<NewsInfo> selectByExample();

	NewsInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(NewsInfo record);
}