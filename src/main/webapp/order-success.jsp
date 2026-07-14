<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="com.tap.model.Order"%>
<%@page import="com.tap.model.Restaurant"%>

<%
Order order =
(Order)request.getAttribute("order");

Restaurant restaurant =
(Restaurant)request.getAttribute("restaurant");
%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>

Order Placed | CRAVING CART

</title>

<link rel="stylesheet"
href="css/order-success.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>

<!-- ================= NAVBAR ================= -->

<nav class="navbar">

<h2>

Craving cart

</h2>

</nav>

<!-- ================= SUCCESS CARD ================= -->

<div class="success-container">

<div class="success-card">

<div class="success-icon">

<i class="fa-solid fa-circle-check"></i>

</div>

<h1>

Order Placed Successfully

</h1>

<p class="subtitle">

Your delicious food is now being prepared.

</p>

<!-- ================= RESTAURANT ================= -->

<div class="restaurant-box">

<i class="fa-solid fa-store"></i>

<div>

<h3>

<%=restaurant.getName()%>

</h3>

<p>

<%=restaurant.getCuisineType()%>

</p>

</div>

</div>

<!-- ================= DETAILS ================= -->

<div class="details">

<div class="detail-row">

<span>

Order ID

</span>

<span>

#<%=order.getOrderId()%>

</span>

</div>

<div class="detail-row">

<span>

Payment

</span>

<span>

<%=order.getPaymentMode()%>

</span>

</div>

<div class="detail-row">

<span>

Status

</span>

<span class="status">

<%=order.getOrderStatus()%>

</span>

</div>

<div class="detail-row">

<span>

Grand Total

</span>

<span>

&#8377;

<%=String.format("%.2f",
order.getTotalAmount())%>

</span>

</div>

<div class="detail-row">

<span>

Estimated Delivery

</span>

<span>

<%=restaurant.getDeliveryTime()%>

</span>

</div>

</div>

<!-- ================= MESSAGE ================= -->

<div class="message">

<i class="fa-solid fa-bell"></i>

You'll receive live updates as your order progresses.

</div>

<!-- ================= BUTTONS ================= -->

<div class="buttons">

<a
href="TrackOrderServlet?orderId=<%=order.getOrderId()%>"
class="track-btn">

Track Order

</a>

<a
href="HomeServlet"
class="home-btn">

Continue Shopping

</a>

</div>

</div>

</div>

</body>

</html>