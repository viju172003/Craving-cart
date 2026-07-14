package com.tap.DAO;

import java.util.List;

import com.tap.model.Menu;

public interface MenuDAO {
	
	    void addMenu(Menu menu);

	    Menu getMenu(int menuId);

	    List<Menu> getAllMenus();

	    List<Menu> getMenusByRestaurant(int restaurantId);

	    void updateMenu(Menu menu);
	    boolean updateAvailability(int menuId,boolean available);

	    void deleteMenu(int menuId);
	    
	    int getTotalMenuItems(int restaurantId);

	    int getAvailableMenuItems(int restaurantId);

	    int getUnavailableMenuItems(int restaurantId);
	    List<Menu> searchMenuItems(int restaurantId,String keyword);
	    
	}


