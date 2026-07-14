package com.tap.Service;

import java.util.List;

import com.tap.model.Restaurant;

public interface RestaurantService {
	
    List<Restaurant> getAllRestaurants();

	    boolean updateRestaurantStatus(
	            int restaurantId,
	            String status);
	    int getTotalRestaurants();
	}


