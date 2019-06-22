package com.itheima.service;

import java.sql.SQLException;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;

public class UserService {
	
	//�û�ע��
	public boolean regist(User user) {
		
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row==0?false:true;
	}
	
	//�û�����
	public void active(String activeCode) {
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//У���û���
	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		Long isExist = 0L;
		try {
			isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isExist>0?false:true;
	}
	
	
	public User login(String username, String password) {
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
