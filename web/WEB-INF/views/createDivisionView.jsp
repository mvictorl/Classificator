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
    <h2>Создать подразделение</h2>
    <p style="color: red;">${errorString}</p>

    <form class="form-horizontal" method="POST" action="doCreateDivision">
        <div class="form-group">
            <label for="id_name" class="col-sm-2 control-label">Наименование:</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="id_name" placeholder="Наименоване" name="name" />
            </div>
        </div>
        <div>
            <input type="hidden" name="active_filial" value="${active_filial}">
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4">
                <button type="submit" class="btn btn-info">Создать</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/divisionList?active_filial=${active_filial}">Отмена</a>
            </div>
        </div>
    </form>
</section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>