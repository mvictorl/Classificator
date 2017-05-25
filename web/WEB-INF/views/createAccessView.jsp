<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление новой страницы</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<section class="container">
    <h2>Создать новую страницу</h2>
    <p style="color: red;">${errorString}</p>

    <form class="form-horizontal" method="POST" action="doCreateAccess">
        <div class="form-group">
            <label for="url" class="col-sm-2 control-label">Код:</label>
            <div class="col-sm-2">
                <input type="text" class="form-control" id="url" placeholder="Адрес"
                       name="url" value="${url}">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">Уровень:</label>
            <fieldset class="col-sm-2 btn-group btn-group-vertical" data-toggle="buttons">
                <label class="btn btn-primary">
                    <input type="radio" name="level" value="1"> root
                </label>
                <label class="btn btn-primary">
                    <input type="radio" name="level" value="3"> Администратор
                </label>
                <label class="btn btn-primary">
                    <input type="radio" name="level" value="7"> Ответственный
                </label>
                <label class="btn btn-primary">
                    <input type="radio" name="level" value="15"> Общий
                </label>
            </fieldset>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-info">Создать</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/accessList">Отмена</a>
            </div>
        </div>
    </form>
</section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
</body>
</html>