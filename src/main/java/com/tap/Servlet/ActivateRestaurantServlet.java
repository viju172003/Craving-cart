package com.tap.Servlet;

import java.io.IOException;

import com.tap.Service.RestaurantService;
import com.tap.serviceimpl.RestaurantServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/activateRestaurant")
public class ActivateRestaurantServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RestaurantService restaurantService;

    @Override
    public void init() throws ServletException {
        restaurantService = new RestaurantServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId = Integer.parseInt(
                request.getParameter("restaurantId"));

        restaurantService.updateRestaurantStatus(
                restaurantId,
                "ACTIVE");

        response.sendRedirect("manageRestaurants");
    }
}