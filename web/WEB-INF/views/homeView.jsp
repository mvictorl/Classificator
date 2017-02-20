<%@ page import="com.mvictorl.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Стартовая страница</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<section class="container">
    <h1>Стартовая страница</h1>
    <p style="color: red;">${errorString}</p>
    <p>Данная программа предназначена для автоматизации проведения классификации объектов защиты.</p>
    <br>
    <% if (session.getAttribute("loginedUser") == null) { %>
    <p>Для начала работы необходимо получить учетные данные и <a href="${pageContext.request.contextPath}/login">
        ввести</a> их</p>
    <% } else { %>
    <p>Добро пожаловать, <a href="${pageContext.request.contextPath}/userInfo">
        <b><%= ((User) session.getAttribute("loginedUser")).getName() %></b></p></a>
    <% } %>
</section>
<jsp:include page="_footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
</body>
</html>
