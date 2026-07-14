package com.tap.Test;

import java.util.List;

import com.tap.DAOImpl.OrderItemDAOImpl;
import com.tap.model.OrderItem;

public class OrderItemTest {
	

	    public static void main(String[] args) {

	        OrderItemDAOImpl dao = new OrderItemDAOImpl();

	        System.out.println("===== INSERT =====");

	        OrderItem item = new OrderItem(
	                0,
	                2,
	                1,
	                2,
	                398.00,
	                50.00,
	                60.00,
	                20.00,
	                760
	        );

	        dao.addOrderItem(item);

	        System.out.println("Inserted Successfully");


	        System.out.println("\n===== GET BY ID =====");

	        OrderItem orderItem =
	                dao.getOrderItem(2);

	        System.out.println(orderItem);


	        System.out.println("\n===== GET ALL =====");

	        List<OrderItem> items =
	                dao.getAllOrderItems();

	        for(OrderItem i : items) {
	            System.out.println(i);
	        }


	        System.out.println("\n===== GET BY ORDER =====");

	        List<OrderItem> orderItems =
	                dao.getOrderItemsByOrderId(2);

	        for(OrderItem i : orderItems) {
	            System.out.println(i);
	        }


	        System.out.println("\n===== UPDATE =====");

	        OrderItem updateItem =
	                dao.getOrderItem(2);

	        if(updateItem != null) {

	            updateItem.setQuantity(3);

	            updateItem.setSubtotal(597.00);

	            dao.updateOrderItem(updateItem);

	            System.out.println(
	                    "Updated Successfully");
	        }


	        System.out.println("\n===== DELETE =====");

	        dao.deleteOrderItem(2);

	        System.out.println(
	                "Deleted Successfully");
	    }
	}

