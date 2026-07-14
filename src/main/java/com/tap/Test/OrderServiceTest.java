package com.tap.Test;

import com.tap.Service.OrderService;
import com.tap.serviceimpl.OrderServiceImpl;

public class OrderServiceTest {

	    public static void main(String[] args) {

	        OrderService orderService =new OrderServiceImpl();

	        try {

	            int orderId =
	                    orderService.placeOrder(
	                            3,      // userId
	                            1,      // restaurantId
	                            "UPI"   // payment mode
	                    );

	            System.out.println(
	                    "Order Created Successfully");

	            System.out.println(
	                    "Generated Order ID : "
	                    + orderId);

	        }
	        catch(Exception e) {

	            System.out.println(
	                    "Order Creation Failed");

	            e.printStackTrace();
	        }
	    }
	}


