package com.tap.Servlet;


import java.io.IOException;
import com.tap.DAO.MenuDAO;
import com.tap.DAOImpl.MenuDAOImpl;
import com.tap.Service.OrderService;
import com.tap.serviceimpl.OrderServiceImpl;
import java.util.List;
import com.tap.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/RestaurantDashboardServlet")
public class RestaurantDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

   
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        if(session == null ||
           session.getAttribute("restaurantId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int restaurantId =
                (Integer) session.getAttribute("restaurantId");
        OrderService orderService =
                new OrderServiceImpl();
        System.out.println("Restaurant ID = " + restaurantId);

        int todayOrders =
                orderService
                .getTodayOrderCountByRestaurant(
                        restaurantId);

        double todayRevenue =
                orderService
                .getTodayRevenueByRestaurant(
                        restaurantId);

        double weeklyRevenue =
                orderService
                .getWeeklyRevenueByRestaurant(
                        restaurantId);

        double monthlyRevenue =
                orderService
                .getMonthlyRevenueByRestaurant(
                        restaurantId);
        List<Order> recentOrders =
                orderService
                .getRecentOrdersByRestaurant(
                        restaurantId);
        System.out.println("Recent Orders Size = "
                + recentOrders.size());

        for(Order o : recentOrders){

            System.out.println(
                    o.getOrderId() + " "
                    + o.getRestaurantId() + " "
                    + o.getOrderStatus());
        }

        String mostOrderedItem =
                orderService
                .getMostOrderedItem(
                        restaurantId);

        MenuDAO menuDAO =
                new MenuDAOImpl();

        int totalItems =
                menuDAO.getTotalMenuItems(restaurantId);

        int availableItems =
                menuDAO.getAvailableMenuItems(restaurantId);

        int unavailableItems =
                menuDAO.getUnavailableMenuItems(restaurantId);

        request.setAttribute(
                "totalItems",
                totalItems);

        request.setAttribute(
                "availableItems",
                availableItems);

        request.setAttribute(
                "unavailableItems",
                unavailableItems);
        
        request.setAttribute(
                "todayOrders",
                todayOrders);

        request.setAttribute(
                "todayRevenue",
                todayRevenue);

        request.setAttribute(
                "weeklyRevenue",
                weeklyRevenue);

        request.setAttribute(
                "monthlyRevenue",
                monthlyRevenue);
        
        request.setAttribute(
                "recentOrders",
                recentOrders);
        request.setAttribute(
                "recentOrders",
                recentOrders);
        request.setAttribute(
                "mostOrderedItem",
                mostOrderedItem);

        request.getRequestDispatcher(
                "/restaurant-dashboard.jsp")
                .forward(request, response);
    }
}
