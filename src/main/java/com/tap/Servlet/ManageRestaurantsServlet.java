package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.model.Restaurant;
import com.tap.Service.RestaurantService;
import com.tap.serviceimpl.RestaurantServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manageRestaurants")
public class ManageRestaurantsServlet extends HttpServlet {

    private RestaurantService restaurantService;

    @Override
    public void init() {
        restaurantService = new RestaurantServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        request.setAttribute("restaurants", restaurants);

        request.getRequestDispatcher("manageRestaurants.jsp")
               .forward(request, response);
    }
}