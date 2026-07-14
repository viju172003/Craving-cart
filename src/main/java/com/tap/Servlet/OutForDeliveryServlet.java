package com.tap.Servlet;

import java.io.IOException;

import com.tap.Service.OrderService;
import com.tap.serviceimpl.OrderServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/OutForDeliveryServlet")
public class OutForDeliveryServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int orderId =
                Integer.parseInt(
                        request.getParameter(
                                "orderId"));

        OrderService orderService =
                new OrderServiceImpl();

        orderService.updateOrderStatus(
                orderId,
                "OUT_FOR_DELIVERY");

        response.sendRedirect(
                "PickedUpOrdersServlet");
    }
}
