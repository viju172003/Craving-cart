package com.tap.Servlet;


import java.io.IOException;
import java.util.List;

import com.tap.DAO.UserDAO;
import com.tap.DAOImpl.UserDAOImpl;
import com.tap.Service.CartService;
import com.tap.model.Cart;
import com.tap.model.CartItemView;
import com.tap.model.User;
import com.tap.serviceimpl.CartServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

    private CartService cartService;
    private UserDAO userDAO;

    @Override
    public void init() {

        cartService = new CartServiceImpl();
        userDAO = new UserDAOImpl();

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

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        User user =
                userDAO.getUserById(loggedInUser.getUserID());

        Cart cart =
                cartService.getCartByUserId(user.getUserID());

        if(cart == null){

            response.sendRedirect("CartServlet");
            return;
        }

        List<CartItemView> cartItems =
                cartService.getCartItems(cart.getCartId());

        //----------------------------------------

        double foodTotal =
                cart.getTotalAmount();

        double distance = 5;

        double deliveryFee =
                distance * 1.5;

        double platformFee = 0;

        double packingFee = 0;

        double grandTotal =
                foodTotal
                + deliveryFee
                + platformFee
                + packingFee;

        //----------------------------------------

        request.setAttribute("user", user);

        request.setAttribute("cartItems", cartItems);

        request.setAttribute("foodTotal", foodTotal);

        request.setAttribute("distance", distance);

        request.setAttribute("deliveryFee", deliveryFee);

        request.setAttribute("platformFee", platformFee);

        request.setAttribute("packingFee", packingFee);

        request.setAttribute("grandTotal", grandTotal);

        request.setAttribute("protein",
                cart.getTotalProtein());

        request.setAttribute("carbs",
                cart.getTotalCarbs());

        request.setAttribute("fats",
                cart.getTotalFats());

        request.setAttribute("calories",
                cart.getTotalCalories());

        request.getRequestDispatcher("checkout.jsp")
               .forward(request, response);
    }
}