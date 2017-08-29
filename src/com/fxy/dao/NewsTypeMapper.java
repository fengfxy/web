package com.fxy.dao;

import com.fxy.beans.NewsType;
import com.fxy.beans.NewsTypeExample;
import java.util.List;

public interface NewsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsType record);

    int insertSelective(NewsType record);

    List<NewsType> selectByExample(NewsTypeExample example);

    NewsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsType record);

    int updateByPrimaryKey(NewsType record);
}