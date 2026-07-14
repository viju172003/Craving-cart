package com.tap.Test;

import com.tap.Service.UserService;
import com.tap.model.User;
import com.tap.serviceimpl.UserServiceImpl;

public class UserSerivceTest {
	

	    public static void main(String[] args) {

	        UserService userService = new UserServiceImpl();

	        User user =
	                userService.login(
	                		"admin@hungryhop.com",
	                        "adminpass"
	                );

	        if(user != null) {

	            System.out.println(
	                    "Login Successful");

	            System.out.println(
	                    user.getUsername());

	            System.out.println(
	                    user.getRole());
	        }
	        else {

	            System.out.println(
	                    "Invalid Credentials");
	        }
	    }
	}


