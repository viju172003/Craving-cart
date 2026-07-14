package com.tap.Servlet;

import java.io.IOException;

import com.tap.DAO.RestaurantDAO;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.Service.OrderService;
import com.tap.model.Order;
import com.tap.model.Restaurant;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OrderSuccessServlet")
public class OrderSuccessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RestaurantDAO restaurantDAO;

    private OrderService orderService;

    @Override
    public void init() {

        orderService = new OrderServiceImpl();
        restaurantDAO = new RestaurantDAOImpl();

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String orderIdStr =
                request.getParameter("orderId");

        if(orderIdStr == null){

            response.sendRedirect("HomeServlet");
            return;

        }

        int orderId =
                Integer.parseInt(orderIdStr);

        Order order =
                orderService.getOrder(orderId);

        if(order == null){

            response.sendRedirect("HomeServlet");
            return;

        }
        
        Integer restaurantId =
        		(Integer)request.getSession()
        		.getAttribute("lastRestaurantId");

        		if(restaurantId != null){

        		    Restaurant restaurant =
        		            restaurantDAO.getRestaurant(
        		                    restaurantId);

        		    request.setAttribute(
        		            "restaurant",
        		            restaurant);

        		}
        request.setAttribute("order", order);

        request.getRequestDispatcher(
                "order-success.jsp")
                .forward(request, response);

    }

}