package com.tap.serviceimpl;

import java.util.List;

import com.tap.DAO.RestaurantDAO;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.Service.RestaurantService;
import com.tap.model.Restaurant;

public class RestaurantServiceImpl implements RestaurantService{
	
	
	
	        

	    private RestaurantDAO restaurantDao;

	    public RestaurantServiceImpl() {

	        restaurantDao =new RestaurantDAOImpl();
	    }

	    @Override
	    public List<Restaurant> getAllRestaurants() {

	        return restaurantDao
	                .getAllRestaurants();
	    }

	    @Override
	    public boolean updateRestaurantStatus(
	            int restaurantId,
	            String status) {

	        return restaurantDao
	                .updateRestaurantStatus(
	                        restaurantId,
	                        status);
	    }

	    @Override
	    public int getTotalRestaurants() {

	        return restaurantDao.getTotalRestaurants();
	    }
	}


