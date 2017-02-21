<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Авторизация</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<section class="container">
    <h2>Авторизация</h2>
    <p style="color: red;">${errorString}</p>

    <form class="form-horizontal" method="POST" action="doLogin">
        <div class="form-group">
            <label for="id_name" class="col-sm-2 control-label">Пользователь</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="id_name" placeholder="Имя пользователя"
                       name="userName" value="${user.name}" autofocus>
            </div>
        </div>
        <div class="form-group">
            <label for="id_password" class="col-sm-2 control-label">Пароль</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="id_password" placeholder="Пароль"
                       name="userPassword" value="${user.password}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="rememberMe" value="Y">Запомнить меня</label>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Вход</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/">Отмена</a>
            </div>
        </div>
    </form>
</section>
<jsp:include page="_footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>