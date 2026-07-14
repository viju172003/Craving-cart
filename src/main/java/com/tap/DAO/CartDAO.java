package com.tap.DAO;

import java.util.List;

import com.tap.model.Cart;

public interface CartDAO {
	
	    void addCart(Cart cart);

	    Cart getCart(int cartId);

	    Cart getCartByUserId(int userId);

	    List<Cart> getAllCarts();

	    void updateCart(Cart cart);

	    void deleteCart(int cartId);
	}


