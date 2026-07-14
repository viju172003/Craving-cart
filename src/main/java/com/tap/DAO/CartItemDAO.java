package com.tap.DAO;

import java.util.List;

import com.tap.model.CartItem;
import com.tap.model.CartItemView;

public interface CartItemDAO {
	
	    void addCartItem(CartItem item);

	    CartItem getCartItem(int cartItemId);

	    List<CartItem> getCartItemsByCartId(int cartId);

	    List<CartItem> getAllCartItems();

	    void updateCartItem(CartItem item);

	    void deleteCartItem(int cartItemId);
	    CartItem getCartItemByMenu(int cartId, int menuId);

	    void updateQuantity(
	            int cartItemId,
	            int quantity,
	            double subtotal,
	            double protein,
	            double carbs,
	            double fats,
	            int calories);
	    int getCartItemCount(int cartId);
	    CartItem getCartItemById(int cartItemId);

	    void deleteByCartAndMenu(int cartId,int menuId);
	    
	    List<CartItemView> getCartItemDetails(int cartId);
	}


