<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Смена пароля</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<section class="container">
    <h2>Смена пароля <b>${user.name}</b></h2>
    <p style="color: red;">${errorString}</p>

    <form class="form-horizontal" method="POST" action="doChangeUserPass">
        <div class="form-group">
            <label for="pass" class="col-sm-2 control-label">Текущий пароль</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="pass" placeholder="Текущий пароль"
                       name="pass" autofocus />
            </div>
        </div>
        <div class="form-group">
            <label for="id_new_pass_1" class="col-sm-2 control-label">Новый пароль</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="id_new_pass_1" placeholder="Новый пароль"
                       name="new_pass_1" />
            </div>
        </div>
        <div class="form-group">
            <label for="id_new_pass_2" class="col-sm-2 control-label">Подтверждение</label>
            <div class="col-sm-6">
                <input type="password" class="form-control" id="id_new_pass_2" placeholder="Новый пароль"
                       name="new_pass_2" />
            </div>
        </div>
        <div class="form-group">
            <input type="hidden" name="user_name" value="${user.name}" />
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Сменить пароль</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/userInfo">Отмена</a>
            </div>
        </div>
    </form>
</section>
<jsp:include page="_footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>