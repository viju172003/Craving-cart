package com.tap.serviceimpl;

import java.util.List;

import com.tap.DAOImpl.UserDAOImpl;
import com.tap.Service.UserService;
import com.tap.model.User;

public class UserServiceImpl implements UserService {

	    private UserDAOImpl userDAO;

	    public UserServiceImpl() {
	    	

	        userDAO = new UserDAOImpl();
	    }

	    @Override
	    public User login(String email,
	                      String password) {
	    	User user = userDAO.validateUser(email,password);

	    	return user;

	        
	    }
	    @Override
	    public List<User> getAllUsers() {

	        return userDAO.getAllUsers();
	    }
	    @Override
	    public boolean updateUserStatus(
	            int userId,
	            String status) {

	        return userDAO.updateUserStatus(
	                userId,
	                status);
	    }
	    @Override
	    public int getTotalUsers() {

	        return userDAO.getTotalUsers();
	    }

	    @Override
		public boolean registerUser(User user) {

		    if(userDAO.isEmailExists(user.getEmail())){

		        return false;

		    }

		    user.setRole("user");

		    userDAO.addUser(user);

		    return true;

		}

		@Override
		public boolean isEmailExists(String email) {

		    return userDAO.isEmailExists(email);

		}

		@Override
		public List<User> getAllDeliveryAgents() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean updateProfile(User user) {

		    return userDAO.updateProfile(user);

		}
	   
	}

