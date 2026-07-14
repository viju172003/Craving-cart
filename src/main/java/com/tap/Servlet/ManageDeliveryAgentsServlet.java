package com.tap.Servlet;

import java.io.IOException;
import java.util.List;

import com.tap.Service.DeliveryAgentService;
import com.tap.model.DeliveryAgent;

import com.tap.serviceimpl.DeliveryAgentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manageDeliveryAgents")
public class ManageDeliveryAgentsServlet
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

        List<DeliveryAgent> agents =
                deliveryAgentService
                .getAllDeliveryAgents();

        request.setAttribute(
                "agents",
                agents);

        request.getRequestDispatcher(
                "manageDeliveryAgents.jsp")
                .forward(
                        request,
                        response);
    }
}