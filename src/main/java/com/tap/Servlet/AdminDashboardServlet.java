package com.tap.Servlet;

import java.io.IOException;

import com.tap.DAO.OrderDAO;
import com.tap.DAO.RestaurantDAO;
import com.tap.DAO.UserDAO;
import com.tap.DAOImpl.OrderDAOImpl;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.DAOImpl.UserDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDao;
    private RestaurantDAO restaurantDao;
    private OrderDAO orderDao;

    @Override
    public void init() throws ServletException {

        userDao = new UserDAOImpl();
        restaurantDao = new RestaurantDAOImpl();
        orderDao = new OrderDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int totalUsers = userDao.getTotalUsers();

        int totalRestaurants = restaurantDao.getTotalRestaurants();

        int ordersToday = orderDao.getTodayOrderCount();

        double todayRevenue = orderDao.getTodayRevenue();

        request.setAttribute("totalUsers", totalUsers);

        request.setAttribute("totalRestaurants", totalRestaurants);

        request.setAttribute("ordersToday", ordersToday);

        request.setAttribute("todayRevenue", todayRevenue);

        request.getRequestDispatcher("adminDashboard.jsp")
               .forward(request, response);
    }
}