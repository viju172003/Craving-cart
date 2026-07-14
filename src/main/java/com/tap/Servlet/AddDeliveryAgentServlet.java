package com.tap.Servlet;

import java.io.IOException;

import com.tap.Service.DeliveryAgentService;
import com.tap.model.DeliveryAgent;
import com.tap.serviceimpl.DeliveryAgentServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addDeliveryAgent")
public class AddDeliveryAgentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DeliveryAgentService deliveryAgentService;

    @Override
    public void init() {

        deliveryAgentService = new DeliveryAgentServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("========== AddDeliveryAgentServlet Called ==========");

        DeliveryAgent agent = new DeliveryAgent();

        agent.setAgentName(request.getParameter("agentName"));
        agent.setEmail(request.getParameter("email"));
        agent.setPhone(request.getParameter("phone"));
        agent.setPassword(request.getParameter("password"));
        agent.setVehicleType(request.getParameter("vehicleType"));
        agent.setVehicleNumber(request.getParameter("vehicleNumber"));

        agent.setStatus("Available");
        agent.setEmploymentStatus("ACTIVE");

        double rating = Double.parseDouble(request.getParameter("rating"));
        agent.setRating(rating);

        System.out.println("Agent Name : " + agent.getAgentName());
        System.out.println("Email : " + agent.getEmail());
        System.out.println("Phone : " + agent.getPhone());
        System.out.println("Vehicle Type : " + agent.getVehicleType());
        System.out.println("Vehicle Number : " + agent.getVehicleNumber());
        System.out.println("Status : " + agent.getStatus());
        System.out.println("Employment Status : " + agent.getEmploymentStatus());
        System.out.println("Rating Parameter = " + request.getParameter("rating"));
        System.out.println("Agent Rating = " + agent.getRating());

        deliveryAgentService.addDeliveryAgent(agent);
        System.out.println("Rating received = " + request.getParameter("rating"));

        System.out.println("========== Agent Added Successfully ==========");

        response.sendRedirect("manageDeliveryAgents");
    }
}