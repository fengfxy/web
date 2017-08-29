package com.fxy.dao;

import com.fxy.beans.News;
import com.fxy.beans.NewsType;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);
    
    List<News> selectByExample();
    
    List<News> selectByType(NewsType newsType);
    
    int selectByTypeCount(NewsType newsType);
    
    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(News record);
}