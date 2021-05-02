package com.model2.mvc.service.user.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;

public class UserServiceImpl implements UserService{
	//Field
	private UserDAO userDAO;
	
	//Constructor
	public UserServiceImpl() {
		userDAO = new UserDAO();
	}

	//Method
	public void addUser(UserVO userVO) throws Exception {
		System.out.println("<<<<< UserServiceImpl : addUser() ���� >>>>>");
		userDAO.insertUser(userVO);
	}

	public UserVO loginUser(UserVO userVO) throws Exception {
		System.out.println("<<<<< UserServiceImpl : loginUser() ���� >>>>>");
		
		UserVO dbUser = userDAO.findUser(userVO.getUserId());
		
		if(! dbUser.getPassword().equals(userVO.getPassword()))
			throw new Exception("�α��ο� �����߽��ϴ�.");
			
		return dbUser;
	}

	public UserVO getUser(String userId) throws Exception {
		System.out.println("<<<<< UserServiceImpl : getUser() ���� >>>>>");
		return userDAO.findUser(userId);
	}

	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
		System.out.println("<<<<< UserServiceImpl : getUserList() ���� >>>>>");
		return userDAO.getUserList(searchVO);
	}

	public void updateUser(UserVO userVO) throws Exception {
		System.out.println("<<<<< UserServiceImpl : updateUser() ���� >>>>>");
		userDAO.updateUser(userVO);
	}

	public boolean checkDuplication(String userId) throws Exception {
		System.out.println("<<<<< UserServiceImpl : checkDuplication() ���� >>>>>");
		
		boolean result = true;
		UserVO userVO = userDAO.findUser(userId);
		if(userVO != null) {
			result = false;
		}
		return result;
	}
}//end of class