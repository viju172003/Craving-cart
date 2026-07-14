package com.tap.Servlet;

import java.io.IOException;
import java.util.List;
import com.tap.Service.CartService;
import com.tap.Service.OrderService;
import com.tap.model.Cart;
import com.tap.model.CartItemView;
import com.tap.model.User;
import com.tap.serviceimpl.CartServiceImpl;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

    private CartService cartService;
    private OrderService orderService;

    @Override
    public void init() {

        cartService = new CartServiceImpl();
        orderService = new OrderServiceImpl();

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser")==null){

            response.sendRedirect("userlogin.jsp");
            return;

        }

        User user =
                (User)session.getAttribute(
                        "loggedInUser");

        Cart cart =
                cartService.getCartByUserId(
                        user.getUserID());

        if(cart == null){

            response.sendRedirect(
                    "CartServlet");

            return;

        }

        List<CartItemView> items =
                cartService.getCartItems(
                        cart.getCartId());

        if(items == null ||
           items.isEmpty()){

            response.sendRedirect(
                    "CartServlet");

            return;

        }

        int restaurantId =
                items.get(0)
                     .getRestaurantId();

        String paymentMode =
                request.getParameter(
                        "paymentMode");

        if(paymentMode == null ||
                paymentMode.isBlank()){

            paymentMode = "COD";

        }

        int orderId =
                orderService.placeOrder(
                        user.getUserID(),
                        restaurantId,
                        paymentMode);
        session.setAttribute(
                "lastRestaurantId",
                restaurantId);

        response.sendRedirect(
                "OrderSuccessServlet?orderId="
                        + orderId);

    }

}