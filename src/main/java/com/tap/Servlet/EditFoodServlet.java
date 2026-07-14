package com.tap.Servlet;

import java.io.IOException;

import com.tap.Service.MenuService;
import com.tap.model.Menu;
import com.tap.serviceimpl.MenuServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditFoodServlet")
	public class EditFoodServlet extends HttpServlet {

	    private static final long serialVersionUID = 1L;

	    private MenuService menuService;

	    @Override
	    public void init() {

	        menuService =
	                new MenuServiceImpl();
	    }

	    @Override
	    protected void doGet(
	            HttpServletRequest request,
	            HttpServletResponse response)
	            throws ServletException, IOException {

	        int menuId =
	                Integer.parseInt(
	                        request.getParameter(
	                                "menuId"));

	        Menu menu =
	                menuService.getMenu(
	                        menuId);

	        request.setAttribute(
	                "menu",
	                menu);

	        request.getRequestDispatcher(
	                "edit-food.jsp")
	                .forward(request, response);
	    }
	}


