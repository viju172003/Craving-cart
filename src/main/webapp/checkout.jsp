<%@page import="java.util.List"%>
<%@page import="com.tap.model.CartItemView"%>
<%@page import="com.tap.model.User"%>

<%
User user =
(User)request.getAttribute("user");

List<CartItemView> cartItems =
(List<CartItemView>)request.getAttribute("cartItems");

double foodTotal =
(Double)request.getAttribute("foodTotal");

double deliveryFee =
(Double)request.getAttribute("deliveryFee");

double platformFee =
(Double)request.getAttribute("platformFee");

double packingFee =
(Double)request.getAttribute("packingFee");

double grandTotal =
(Double)request.getAttribute("grandTotal");

double protein =
(Double)request.getAttribute("protein");

double carbs =
(Double)request.getAttribute("carbs");

double fats =
(Double)request.getAttribute("fats");

int calories =
(Integer)request.getAttribute("calories");
%>
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>

Checkout | CRAVINGCART

</title>

<link rel="stylesheet"
href="css/checkout.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css">

</head>

<body>
<nav class="navbar">

<h2>

Cravingcart

</h2>

<a href="CartServlet">

<i class="fa-solid fa-arrow-left"></i>

Back To Cart

</a>

</nav>
<div class="checkout-container">

<h1>

Checkout

</h1>
<div class="card">

<h2>

Delivery Address

</h2>

<p>

<i class="fa-solid fa-user"></i>

<%=user.getUsername()%>

</p>

<p>

<i class="fa-solid fa-envelope"></i>

<%=user.getEmail()%>

</p>

<p>

<i class="fa-solid fa-location-dot"></i>

<%=user.getAddress()%>

</p>

</div>
<div class="card">

<h2>

Your Order

</h2>

<%
for(CartItemView item : cartItems){
%>

<div class="checkout-item">

<img
src="<%=request.getContextPath()%>/<%=item.getImagePath()%>">

<div>

<h3>

<%=item.getItemName()%>

</h3>

<p>

<%=item.getRestaurantName()%>

</p>

<p>

Qty :

<%=item.getQuantity()%>

</p>

</div>

<div>

&#8377;

<%=String.format("%.2f",
item.getSubtotal())%>

</div>

</div>

<%
}
%>
</div>
<div class="card">

<h2>

Nutrition Summary

</h2>

<div class="nutrition-grid">

<div>

Protein

<h3>

<%=protein%> g

</h3>

</div>

<div>

Carbs

<h3>

<%=carbs%> g

</h3>

</div>

<div>

Fats

<h3>

<%=fats%> g

</h3>

</div>

<div>

Calories

<h3>

<%=calories%>

</h3>

</div>

</div>

</div>
<form
action="PlaceOrderServlet"
method="post">

<div class="card">

<h2>

Payment Method

</h2>

<label>

<input
type="radio"
name="paymentMode"
value="COD"
checked>

Cash On Delivery

</label>

<label>

<input
type="radio"
name="paymentMode"
value="UPI">

UPI

</label>

<label>

<input
type="radio"
name="paymentMode"
value="CARD">

Card

</label>

<label>

<input
type="radio"
name="paymentMode"
value="NET_BANKING">

Net Banking

</label>

</div>
<div class="card">

<h2>

Bill Summary

</h2>

<div class="row">

<span>

Food Total

</span>

<span>

&#8377;
<%=String.format("%.2f",
foodTotal)%>

</span>

</div>

<div class="row">

<span>

Delivery

</span>

<span>

&#8377;
<%=String.format("%.2f",
deliveryFee)%>

</span>

</div>

<div class="row">

<span>

Platform Fee

</span>

<span>

FREE

</span>

</div>

<div class="row">

<span>

Packaging

</span>

<span>

FREE

</span>

</div>

<hr>

<div class="grand-total">

<span>

Grand Total

</span>

<span>

&#8377;
<%=String.format("%.2f",
grandTotal)%>

</span>

</div>

<button
class="place-btn">

Place Order

</button>

</div>

</form>

</div>

</body>

</html>