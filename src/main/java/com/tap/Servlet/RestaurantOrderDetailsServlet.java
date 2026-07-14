package com.tap.Servlet;



import java.io.IOException;
import java.util.List;

import com.tap.DAO.OrderItemDAO;
import com.tap.DAO.UserDAO;
import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.DAOImpl.UserDAOImpl;
import com.tap.Service.OrderService;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RestaurantOrderDetailsServlet")
public class RestaurantOrderDetailsServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        OrderService orderService =
                new OrderServiceImpl();

        Order order =
                orderService.getOrder(
                        orderId);

        UserDAO userDAO =
                new UserDAOImpl();

        User user =
                userDAO.getUser(
                        order.getUserId());

        OrderItemDAO orderItemDAO =
                new OrderItemDAOImpl();

        List<OrderItem> orderItems =
                orderItemDAO
                .getOrderItemsByOrderId(
                        orderId);

        request.setAttribute(
                "order",
                order);

        request.setAttribute(
                "user",
                user);

        request.setAttribute(
                "orderItems",
                orderItems);

        request.getRequestDispatcher(
                "/order-details.jsp")
                .forward(request,
                        response);
    }
}
