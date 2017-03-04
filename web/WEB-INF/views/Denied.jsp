<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Доступ запрещен</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<section class="container">
    <h2 style="color: red">Доступ запрещен</h2>
    <p>Ваши права доступа не позволяют просматривать данный контент</p>
    <p>${accessErrorString}</p>
    <br>
    <div class="row">
        <a class="btn btn-default" href="${pageContext.request.contextPath}/">На главную</a>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/login">Вход</a>
    </div>
    <jsp:include page="_footer.jsp"></jsp:include>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>