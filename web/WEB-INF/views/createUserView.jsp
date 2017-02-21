<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление филиала</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<section class="container">
    <h2>Создать филиал</h2>
    <p style="color: red;">${errorString}</p>

    <form class="form-horizontal" method="POST" action="doCreateFilial">
        <div class="form-group">
            <label for="id_code" class="col-sm-2 control-label">Код:</label>
            <div class="col-sm-2">
                <input type="number" min="0" step="1" class="form-control" id="id_code" placeholder="Код"
                       name="id" value="${filial.id}">
            </div>
        </div>
        <div class="form-group">
            <label for="id_price" class="col-sm-2 control-label">Сокращение</label>
            <div class="col-sm-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="id_price" placeholder="Сокращение"
                           name="sh_name" value='${filial.sh_name}'>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="id_name" class="col-sm-2 control-label">Наименование:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="id_name" placeholder="Наименоване"
                       name="name" value='${filial.name}'>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-info">Создать</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/filialList">Отмена</a>
            </div>
        </div>
    </form>
</section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>