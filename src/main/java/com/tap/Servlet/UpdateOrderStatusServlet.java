package com.tap.Servlet;

import java.io.IOException;

import com.tap.Service.OrderService;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateOrderStatusServlet")
	public class UpdateOrderStatusServlet
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

	        int orderId =
	                Integer.parseInt(
	                        request.getParameter(
	                                "orderId"));

	        String status =
	                request.getParameter(
	                        "status");

	        orderService.updateOrderStatus(
	                orderId,
	                status);
	        

	        response.sendRedirect(
	                "RestaurantOrdersServlet");
	    }
	}

