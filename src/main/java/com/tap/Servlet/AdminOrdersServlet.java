package com.tap.Servlet;


import java.io.IOException;
import java.util.List;

import com.tap.DAO.DeliveryAgentDAO;
import com.tap.DAOImpl.DeliveryAgentDAOImpl;
import com.tap.Service.OrderService;
import com.tap.model.DeliveryAgent;
import com.tap.model.Order;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AdminOrdersServlet")
public class AdminOrdersServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        OrderService orderService =
                new OrderServiceImpl();

        DeliveryAgentDAO agentDAO =
                new DeliveryAgentDAOImpl();

        List<Order> orders =
                orderService
                .getUnassignedOrders();

        List<DeliveryAgent> agents =
                agentDAO
                .getAllDeliveryAgents();

        request.setAttribute(
                "orders",
                orders);

        request.setAttribute(
                "agents",
                agents);

        request.getRequestDispatcher(
                "/admin-orders.jsp")
                .forward(request,
                        response);
    }
}