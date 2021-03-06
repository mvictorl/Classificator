<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Пользователь</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<section class="container">
    <h2>Информация о пользователе</h2>
    <div class="row">
        <div class="col-sm-3">
            <h3>Пользователь: </h3>
        </div>
        <div class="col-sm-9">
            <h3>${user.name}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <h3>ID: </h3>
        </div>
        <div class="col-sm-9">
            <h3>${user.id}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <h3>Сотрудник: </h3>
        </div>
        <div class="col-sm-9">
            <h3>${user.worker.surname} ${user.worker.name} ${user.worker.patronymic}</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <h3>Отдел: </h3>
        </div>
        <div class="col-sm-9">
            <h3>${user.worker.division.id} (${user.worker.division.name})</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <h3>Роль: </h3>
        </div>
        <div class="col-sm-9">
            <h3>${user.role.id} (${user.role.name})</h3>
        </div>
    </div>
    <a class="btn btn-success pull-left" href="${pageContext.request.contextPath}/changeUserPass">Сменить пароль</a>
    <a class="btn btn-danger pull-right" href="${pageContext.request.contextPath}/doLogoff">Выйти</a>
</section>
<jsp:include page="_footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>