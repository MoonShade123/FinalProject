<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.01.2019
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<!--
<%@ page language="java" contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
<div align="center">
<div>
<form action="FrontController" id="form-page-registration">
<input type="hidden" name="command" value="registration">
<div class="table">
<div class="tr">
<div class="login">
<input type="text" id="Login" name="login" size="20" maxlength="25" placeholder="Login">
</div>
</div>
<div class="tr">
<div class="password">
<input type="password" id="Password" name="password" size = "20" maxlength="25" placeholder="Password">
</div>
</div>
<div class="tr">
<div class="name">
<input type="name" id="Name" name="name" SIZE="20" maxlength="25" placeholder="Name">
</div>
</div>
<div class="tr">
<div class="name">
<input type="surname" id="Surname" name="surname" SIZE="20" maxlength="25" placeholder="Surname">
</div>
</div>
</div>
<div class="tr">
<div class="name">
<input type="mobilePhone" id="MobilePhone" name="mobilePhone" SIZE="20" maxlength="25" placeholder="Mobile Phone">
</div>
</div>
<div class="tr">
<div class="name">
<input type="email" id="Email" name="email" SIZE="20" maxlength="25" placeholder="Email">
</div>
</div>

<input type="submit" id="finish_button" value="Reg me!">
</form>
</div>
</div>
</body>
-->


<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body>
<div align="center">
    <div>
        <form action="FrontController" id="form-page-registration">
            <input type="hidden" name="command" value="registration">
            <input type="text" id="Login" name="login" size="20" maxlength="25" placeholder="Login">
            <input type="password" id="Password" name="password" size="20" maxlength="25" placeholder="Password">
            <input type="submit" id="finish_button" value="Reg me!">
        </form>
    </div>
</div>
<a class="btn btn-primary" role="button" href="FrontController?command=test_command">View List</a>
<table class="table table-hover">
    <thead class="bg-primary">
    <tr>
        <th scope="col">Login</th>
        <th scope="col">Password</th>
        <th scope="col">Username</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${testList}" var="User">
    <tr>
        <td>
            <p>${User.login}</p>
        </td>
        <td>
            <p>${User.password}</p>
        </td>
        <td>
            <p>${User.userName}</p>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>

