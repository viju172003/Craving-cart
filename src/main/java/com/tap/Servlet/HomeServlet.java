package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.DAO.CartDAO;
import com.tap.DAO.CartItemDAO;
import com.tap.DAOImpl.CartDAOImpl;
import com.tap.DAOImpl.CartItemDAOImpl;
import com.tap.Service.RestaurantService;
import com.tap.model.Cart;
import com.tap.model.Restaurant;
import com.tap.model.User;
import com.tap.serviceimpl.RestaurantServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RestaurantService restaurantService;

    @Override
    public void init() {

        restaurantService = new RestaurantServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect("userlogin.jsp");
            return;
        }

        User user =
        (User) session.getAttribute("loggedInUser");
        
        CartDAO cartDAO =
        		new CartDAOImpl();

        		CartItemDAO cartItemDAO =
        		new CartItemDAOImpl();

        		Cart cart =
        		cartDAO.getCartByUserId(
        		user.getUserID());
        		System.out.println("User ID : " + user.getUserID());

        		if(cart != null){
        		    System.out.println("Cart ID : " + cart.getCartId());
        		}else{
        		    System.out.println("Cart is NULL");
        		}

        		int cartCount = 0;
        		System.out.println("Cart Count : " + cartCount);

        		if(cart != null){

        		    cartCount =
        		    cartItemDAO.getCartItemCount(
        		    cart.getCartId());

        		}

        		request.setAttribute(
        		"cartCount",
        		cartCount);

        List<Restaurant> restaurants =
                restaurantService.getAllRestaurants();

        request.setAttribute("user", user);

        request.setAttribute(
                "restaurants",
                restaurants);
        System.out.println("User ID = " + user.getUserID());

        System.out.println("Cart = " + cart);

        System.out.println("Cart ID = " +
                (cart == null ? "null" : cart.getCartId()));

        System.out.println("Cart Count = " + cartCount);

        request.getRequestDispatcher(
                "home.jsp")
                .forward(request,response);

    }

}