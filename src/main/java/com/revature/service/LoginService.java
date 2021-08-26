package com.revature.service;

import com.revature.dao.UserDAO;
import com.revature.dto.LoginDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.InvalidLoginException;
import com.revature.model.User;

public class LoginService {

	private UserDAO userDao;
	
	public LoginService() {
		this.userDao = new UserDAO();
	}
	
	public User login(LoginDTO loginDTO) throws BadParameterException, InvalidLoginException {
		
		if (loginDTO.getUsername().trim().equals("") && loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Username and password fields cannot be blank");
		}
		if (loginDTO.getUsername().trim().equals("")) {
			throw new BadParameterException("Username field cannot be blank");
		}
		if (loginDTO.getPassword().trim().equals("")) {
			throw new BadParameterException("Password field cannot be blank");
		}
		
		User user = userDao.getUserByUsernameAndPwd(loginDTO.getUsername(), loginDTO.getPassword());
		
		if (user == null) {
			throw new InvalidLoginException("The login credentials provided are incorrect");
		}
		
		return user;
	}
}
