package com.tap.Servlet;



import java.io.IOException;
import java.util.List;

import com.tap.Service.OrderService;
import com.tap.model.Order;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/PickedUpOrdersServlet")
public class PickedUpOrdersServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("agentId") == null){

            response.sendRedirect(
                    "delivery-login.jsp");
            return;
        }

        int agentId =
                (Integer)session.getAttribute(
                        "agentId");

        OrderService orderService =
                new OrderServiceImpl();

        List<Order> orders =
                orderService
                .getPickedUpOrdersByAgent(
                        agentId);

        request.setAttribute(
                "orders",
                orders);

        request.getRequestDispatcher(
                "/pickedup-orders.jsp")
                .forward(request,
                        response);
    }
}