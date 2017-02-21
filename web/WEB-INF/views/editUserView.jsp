<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование филиала</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<section class="container">
    <h2>Редактировать филиал</h2>
    <c:if test="${not empty filial}">
        <form class="form-horizontal" method="POST" action="doEditFilial">
            <div class="form-group">
                <label for="code" class="col-sm-2 control-label">Код</label>
                <div class="col-sm-2">
                    <input type="number" min="0" step="1" class="form-control" id="code" placeholder="Код"
                           name="id" value="${filial.id}" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="sh_name" class="col-sm-2 control-label">Сокращение</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="sh_name" placeholder="Сокращение"
                           name="sh_name" value='${filial.sh_name}'>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Наименование</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="name" placeholder="Наименование"
                           name="name" value='${filial.name}'>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-info">Подтвердить</button>
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/filialList">Отмена</a>
                </div>
            </div>
        </form>
    </c:if>
</section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>