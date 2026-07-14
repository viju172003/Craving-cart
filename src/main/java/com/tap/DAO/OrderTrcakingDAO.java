package com.tap.DAO;

import java.util.List;

import com.tap.model.OrderTracking;

public interface OrderTrcakingDAO {
	
	 void addTracking(OrderTracking tracking);

	    OrderTracking getTracking(int trackingId);

	    List<OrderTracking> getAllTracking();

	    List<OrderTracking> getTrackingByOrderId(int orderId);

	    void updateTracking(OrderTracking tracking);

	    void deleteTracking(int trackingId);
	}


