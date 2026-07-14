package com.tap.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tap.DAO.CartDAO;
import com.tap.DAO.CartItemDAO;
import com.tap.DAO.RestaurantDAO;
import com.tap.DAOImpl.CartDAOImpl;
import com.tap.DAOImpl.CartItemDAOImpl;
import com.tap.DAOImpl.RestaurantDAOImpl;
import com.tap.Service.MenuService;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;
import com.tap.model.Restaurant;
import com.tap.model.User;
import com.tap.serviceimpl.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/RestaurantMenuServlet")
public class RestaurantMenuServlet extends HttpServlet {

    private MenuService menuService;
    private RestaurantDAO restaurantDAO;

    @Override
    public void init() {

        menuService = new MenuServiceImpl();
        restaurantDAO = new RestaurantDAOImpl();

    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId =
                Integer.parseInt(request.getParameter("restaurantId"));

        Restaurant restaurant =
                restaurantDAO.getRestaurant(restaurantId);

        List<Menu> menuList =
                menuService.getMenusByRestaurant(restaurantId);

        HttpSession session =
                request.getSession(false);

        Map<Integer, Integer> cartMap =
                new HashMap<>();

        int cartCount = 0;

        if (session != null &&
            session.getAttribute("loggedInUser") != null) {

            User user =
                    (User) session.getAttribute("loggedInUser");

            CartDAO cartDAO =
                    new CartDAOImpl();

            Cart cart =
                    cartDAO.getCartByUserId(user.getUserID());

            if (cart != null) {

                CartItemDAO itemDAO =
                        new CartItemDAOImpl();

                List<CartItem> items =
                        itemDAO.getCartItemsByCartId(cart.getCartId());

                for (CartItem item : items) {

                    cartMap.put(
                            item.getMenuId(),
                            item.getQuantity());

                    cartCount += item.getQuantity();
                }
            }
        }

        request.setAttribute("restaurant", restaurant);
        request.setAttribute("menuList", menuList);

        // Send BOTH to JSP
        request.setAttribute("cartMap", cartMap);
        request.setAttribute("cartCount", cartCount);

        request.getRequestDispatcher("restaurant-menu.jsp")
               .forward(request, response);
    }
}