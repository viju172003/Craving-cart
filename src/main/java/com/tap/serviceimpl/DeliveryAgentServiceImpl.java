package com.tap.serviceimpl;

import java.util.List;

import com.tap.DAO.DeliveryAgentDAO;
import com.tap.DAOImpl.DeliveryAgentDAOImpl;
import com.tap.Service.DeliveryAgentService;
import com.tap.model.DeliveryAgent;

public class DeliveryAgentServiceImpl implements DeliveryAgentService{
	

	

	    private DeliveryAgentDAO deliveryAgentDAO;

	    public DeliveryAgentServiceImpl() {

	        deliveryAgentDAO =
	                new DeliveryAgentDAOImpl();
	    }

	    @Override
	    public void addDeliveryAgent(
	            DeliveryAgent agent) {

	        deliveryAgentDAO
	                .addDeliveryAgent(agent);
	    }

	    @Override
	    public DeliveryAgent getDeliveryAgent(
	            int agentId) {

	        return deliveryAgentDAO
	                .getDeliveryAgent(agentId);
	    }

	    @Override
	    public List<DeliveryAgent>
	    getAllDeliveryAgents() {

	        return deliveryAgentDAO
	                .getAllDeliveryAgents();
	    }

	    @Override
	    public void updateDeliveryAgent(
	            DeliveryAgent agent) {

	        deliveryAgentDAO
	                .updateDeliveryAgent(agent);
	    }

	    @Override
	    public void deleteDeliveryAgent(
	            int agentId) {

	        deliveryAgentDAO
	                .deleteDeliveryAgent(agentId);
	    }
	    @Override
	    public void activateAgent(int agentId) {

	        deliveryAgentDAO
	                .activateAgent(agentId);
	    }

	    @Override
	    public void deactivateAgent(int agentId) {

	        deliveryAgentDAO
	                .deactivateAgent(agentId);
	    }
	}


