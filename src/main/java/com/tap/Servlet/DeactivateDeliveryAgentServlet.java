package com.tap.Servlet;


import java.io.IOException;

import com.tap.Service.DeliveryAgentService;
import com.tap.serviceimpl.DeliveryAgentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deactivateAgent")
public class DeactivateDeliveryAgentServlet
        extends HttpServlet {

    private DeliveryAgentService deliveryAgentService;

    @Override
    public void init() {

        deliveryAgentService =
                new DeliveryAgentServiceImpl();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	int agentId =
    	        Integer.parseInt(
    	                request.getParameter("agentId"));
       
        deliveryAgentService
                .deactivateAgent(agentId);

        response.sendRedirect(
                "manageDeliveryAgents");
    }
}