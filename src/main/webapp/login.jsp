<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Cravingcart Login</title>

<link rel="stylesheet"
      href="<%=request.getContextPath()%>/css/admin-style.css">

</head>

<body class="login-page">

<div class="login-container">

    <h1>Cravingcart</h1>

    <h3>Admin Login</h3>

    <form action="LoginServlet"
          method="post">

        <input type="email"
               name="email"
               placeholder="Enter Email"
               required>

        <input type="password"
               name="password"
               placeholder="Enter Password"
               required>

        <button type="submit">

            Login

        </button>

    </form>

</div>

</body>
</html>