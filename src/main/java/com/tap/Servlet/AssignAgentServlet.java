package com.tap.Servlet;


import java.io.IOException;

import com.tap.Service.OrderService;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AssignAgentServlet")
public class AssignAgentServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        int agentId =
                Integer.parseInt(
                        request.getParameter(
                                "agentId"));

        OrderService orderService =
                new OrderServiceImpl();

        orderService.assignDeliveryAgent(
                orderId,
                agentId);

        response.sendRedirect(
                "AdminOrdersServlet");
    }
}