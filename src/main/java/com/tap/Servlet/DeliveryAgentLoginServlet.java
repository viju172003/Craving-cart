package com.tap.Servlet;



import java.io.IOException;

import com.tap.DAO.DeliveryAgentDAO;
import com.tap.DAOImpl.DeliveryAgentDAOImpl;
import com.tap.model.DeliveryAgent;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DeliveryAgentLoginServlet")
public class DeliveryAgentLoginServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(
    		
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        DeliveryAgentDAO dao =
                new DeliveryAgentDAOImpl();

        DeliveryAgent agent =
                dao.validateAgent(
                        email,
                        password);
        if (agent != null) {
            System.out.println("Agent ID: " + agent.getAgentId());
            System.out.println("Agent Name: " + agent.getAgentName());
            System.out.println("Email: " + agent.getEmail());
        }

        if(agent != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute(
                    "agentId",
                    agent.getAgentId());

            session.setAttribute(
                    "agentName",
                    agent.getAgentName());

            response.sendRedirect(
                    "DeliveryDashboardServlet");
        }
        else {

            response.sendRedirect(
                    "delivery-login.jsp");
        }
    }
}