package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.Service.MenuService;
import com.tap.model.Menu;
import com.tap.serviceimpl.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ManageMenuServlet")
public class ManageMenuServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private MenuService menuService;

    @Override
    public void init() {

        menuService = new MenuServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("restaurantId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int restaurantId = (Integer) session.getAttribute("restaurantId");

        String keyword =
                request.getParameter("keyword");

        List<Menu> menuList;

        if(keyword != null &&
           !keyword.trim().isEmpty()) {

            menuList =
                    menuService.searchMenuItems(
                            restaurantId,
                            keyword);

        } else {

            menuList =
                    menuService.getMenusByRestaurant(
                            restaurantId);
        }
        request.setAttribute("menuList", menuList);

        request.getRequestDispatcher("manage-menu.jsp")
               .forward(request, response);
    }
}