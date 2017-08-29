package com.fxy.dao;

import java.util.ArrayList;

import com.fxy.beans.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    ArrayList<Users> selectByUsernameAndPassword(Users record);
}