package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.Service.CartService;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.User;
import com.tap.serviceimpl.CartServiceImpl;
import com.tap.model.CartItemView;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet{

    private CartService cartService;

    @Override
    public void init(){

        cartService =
                new CartServiceImpl();

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

        Cart cart =
        cartService.getCartByUserId(user.getUserID());

        if(cart == null){

            response.sendRedirect("HomeServlet");
            return;
        }

        List<CartItemView> items =
        cartService.getCartItems(cart.getCartId());

        //-------------------------------------

        double foodTotal = cart.getTotalAmount();

        double distance = 5;        // Temporary

        double deliveryFee =
                distance * 1.5;

        double platformFee = 0;

        double packingFee = 0;

        double grandTotal =
                foodTotal
                + deliveryFee
                + platformFee
                + packingFee;

        //-------------------------------------

        request.setAttribute("cartItems", items);

        request.setAttribute("foodTotal",
                foodTotal);

        request.setAttribute("deliveryFee",
                deliveryFee);

        request.setAttribute("distance",
                distance);

        request.setAttribute("platformFee",
                platformFee);

        request.setAttribute("packingFee",
                packingFee);

        request.setAttribute("grandTotal",
                grandTotal);

        request.setAttribute("protein",
                cart.getTotalProtein());

        request.setAttribute("carbs",
                cart.getTotalCarbs());

        request.setAttribute("fats",
                cart.getTotalFats());

        request.setAttribute("calories",
                cart.getTotalCalories());

        request.getRequestDispatcher(
                "cart.jsp")
                .forward(request,response);

    }
}