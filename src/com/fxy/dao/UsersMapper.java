package com.fxy.dao;

import java.util.ArrayList;
import java.util.List;

import com.fxy.beans.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);
    int deleteByDate();
    int insert(Users record);

    List<Users> selectByExample();
    Users selectByPrimaryKey(Integer id);
    Users selectByEmail(String email);
    Users selectByUserName(String userName);
    Users selectByVerification(String verification);
    int updateByPrimaryKey(Users record);
    
    ArrayList<Users> selectByUsernameAndPassword(Users record);
}