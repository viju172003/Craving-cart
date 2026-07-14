package com.tap.Service;

import java.util.List;

import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.CartItemView;

public interface CartService {

	    Cart getCartByUserId(int userId);

	    List<CartItemView> getCartItems(int cartId);

	    void removeItem(int cartItemId);

	    void increaseQuantity(int cartItemId);

	    void decreaseQuantity(int cartItemId);
	    
	    

	}


