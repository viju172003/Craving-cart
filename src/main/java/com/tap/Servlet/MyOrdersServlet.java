package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.Service.OrderService;
import com.tap.model.Order;
import com.tap.model.OrderHistoryView;
import com.tap.model.User;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/MyOrdersServlet")
public class MyOrdersServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() {

        orderService = new OrderServiceImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("loggedInUser") == null){

            response.sendRedirect("userlogin.jsp");
            return;

        }

        User user =
                (User)session.getAttribute("loggedInUser");

        List<OrderHistoryView> orders =
                orderService.getOrderHistoryByUser(
                        user.getUserID());
        request.setAttribute("orders", orders);

        request.getRequestDispatcher(
                "order-history.jsp")
                .forward(request,response);

    }

}
