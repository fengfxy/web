package com.fxy.dao;

import java.util.ArrayList;
import java.util.List;

import com.fxy.beans.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    List<Users> selectByExample();
    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Users record);
    
    ArrayList<Users> selectByUsernameAndPassword(Users record);
}