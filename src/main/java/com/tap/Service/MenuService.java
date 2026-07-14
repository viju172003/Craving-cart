package com.tap.Service;

import java.util.List;

import com.tap.model.Menu;

public interface MenuService {

    List<Menu> getAllMenuItems();
    List<Menu> getMenusByRestaurant(int restaurantId);
    void addMenu(Menu menu);
    Menu getMenu(int menuId);
    void updateMenu(Menu menu);

    List<Menu> searchMenuItems(
            int restaurantId,
            String keyword);
    boolean updateAvailability(
            int menuId,
            boolean available);
}
