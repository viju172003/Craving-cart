package com.tap.Test;

import com.tap.Service.OrderService;
import com.tap.serviceimpl.OrderServiceImpl;

public class CancelOrderTest {
	
	    public static void main(String[] args) {

	        OrderService orderService =
	                new OrderServiceImpl();

	        try {

	            orderService.cancelOrder(3);

	            System.out.println(
	                    "Order Cancelled Successfully");

	        }
	        catch(Exception e) {

	            System.out.println(
	                    "Order Cancellation Failed");

	            e.printStackTrace();
	        }
	    }
	}


