package com.tap.serviceimpl;

import java.util.List;

import com.tap.DAO.CartDAO;
import com.tap.DAO.CartItemDAO;
import com.tap.DAOImpl.CartDAOImpl;
import com.tap.DAOImpl.CartItemDAOImpl;
import com.tap.Service.CartService;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.CartItemView;


public class CartServiceImpl
implements CartService{

    private CartDAO cartDAO =
            new CartDAOImpl();

    private CartItemDAO cartItemDAO =
            new CartItemDAOImpl();

    @Override
    public Cart getCartByUserId(int userId){

        return cartDAO.getCartByUserId(userId);

    }

  

    @Override
    public void removeItem(int cartItemId){

    }

    @Override
    public void increaseQuantity(int cartItemId){

    }

    @Override
    public void decreaseQuantity(int cartItemId){

    }

    @Override
    public List<CartItemView> getCartItems(int cartId){

        return cartItemDAO.getCartItemDetails(cartId);

    }

}
