package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.Service.OrderService;
import com.tap.model.Order;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RestaurantOrdersServlet")
	public class RestaurantOrdersServlet
	        extends HttpServlet {

	    private static final long serialVersionUID = 1L;

	    private OrderService orderService;

	    @Override
	    public void init() {

	        orderService =
	                new OrderServiceImpl();
	    }

	    @Override
	    protected void doGet(
	            HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {

	        HttpSession session =
	                request.getSession(false);

	        if(session == null ||
	           session.getAttribute("restaurantId") == null) {

	            response.sendRedirect(
	                    "login.jsp");
	            return;
	        }

	        int restaurantId =
	                (Integer) session.getAttribute(
	                        "restaurantId");

	        List<Order> orders =
	                orderService.getOrdersByRestaurant(
	                        restaurantId);

	        request.setAttribute(
	                "orders",
	                orders);

	        request.getRequestDispatcher(
	                "restaurant-orders.jsp")
	                .forward(request, response);
	    }
	}

