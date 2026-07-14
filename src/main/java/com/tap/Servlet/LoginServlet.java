package com.tap.Servlet;

import java.io.IOException;

import com.tap.Service.UserService;
import com.tap.model.User;
import com.tap.serviceimpl.UserServiceImpl;
import com.tap.DAO.RestaurantDAO;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        // Forward to login page when user accesses servlet via GET
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("\n========== LOGIN ATTEMPT ==========");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        User user = userService.login(email, password);

        System.out.println("User object returned: " + user);

        if(user != null) {

            System.out.println("User is NOT null");
            System.out.println("User ID: " + user.getUserID());
            System.out.println("User Name: " + user.getUsername());
            
            String roleFromDB = user.getRole();
            System.out.println("Role from getRole(): [" + roleFromDB + "]");
            System.out.println("Role is null: " + (roleFromDB == null));

            HttpSession session = request.getSession();
            
            System.out.println("Session ID: " + session.getId());
            System.out.println("Session is new: " + session.isNew());

            session.setAttribute("loggedInUser", user);
            session.setAttribute("role", roleFromDB);

            // Verify it was set
            String roleCheck = (String) session.getAttribute("role");
            System.out.println("Role verified in session: [" + roleCheck + "]");

            String role = user.getRole();

            System.out.println("About to check role: [" + role + "]");

            if(role != null && role.equalsIgnoreCase("admin")) {
                System.out.println("✓ Role is ADMIN - Redirecting to adminDashboard");
                response.sendRedirect("adminDashboard");
            }
            else if(role.equalsIgnoreCase("restaurant_admin")) {

                RestaurantDAO restaurantDAO =
                        new RestaurantDAOImpl();

                Restaurant restaurant =
                        restaurantDAO.getRestaurantByUserId(
                                user.getUserID());

                if(restaurant != null) {

                    session.setAttribute(
                            "restaurantId",
                            restaurant.getRestaurantId());

                    session.setAttribute(
                            "restaurantName",
                            restaurant.getName());

                    response.sendRedirect(
                            "RestaurantDashboardServlet");

                } else {

                    response.sendRedirect(
                            "login.jsp");
                }
            }
            else if(role != null && role.equalsIgnoreCase("delivery agent")) {
                System.out.println("✓ Role is DELIVERY AGENT");
                response.sendRedirect("deliveryDashboard.jsp");
            }
            else {
                System.out.println("✗ Role doesn't match any condition: [" + role + "]");
                response.sendRedirect("home.jsp");
            }
        }
        else {
            System.out.println("✗ User is NULL - Login failed");
            response.sendRedirect("login.jsp");
        }
        System.out.println("===================================\n");
    }
}
