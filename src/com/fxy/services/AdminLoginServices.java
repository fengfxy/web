package com.fxy.services;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.fxy.beans.Admin;
import com.fxy.beans.AdminExample;
import com.fxy.beans.AdminExample.Criteria;
import com.fxy.dao.AdminMapper;
import com.fxy.util.mybatis.MyBatisUtil;

public class AdminLoginServices {

	public Admin adminLogin(Admin admin) throws IOException{
		Admin result=null;
		
		SqlSession sqlSession=MyBatisUtil.getSqlSessionFactory().openSession();
		
		AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
		AdminExample adminExample =new AdminExample();
		Criteria createCriteria = adminExample.createCriteria();
		createCriteria = createCriteria.andUsernameEqualTo(admin.getUsername())
				.andPasswordEqualTo(admin.getPassword());
		
		ArrayList<Admin> admins = (ArrayList<Admin>) adminMapper.selectByExample(adminExample);
		if (admins!=null && admins.size()>0) {
			result=admins.get(0);
		}
		
		sqlSession.close();
		return result;
		
	}
}
