<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование подразделения</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<section class="container">
    <h2>Редактировать подразделение</h2>
    <c:if test="${not empty division}">
        <form class="form-horizontal" method="POST" action="doEditDivision">
            <div class="form-group">
                <label for="code" class="col-sm-2 control-label">Код</label>
                <div class="col-sm-2">
                    <input type="number" min="0" step="1" class="form-control" id="code" placeholder="Код"
                           name="id" value="${division.id}" readonly>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Наименование</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="name" placeholder="Наименование"
                           name="name" value='${division.name}'>
                </div>
            </div>
            <div class="form-group">
                <label for="chif" class="col-sm-2 control-label">Руководитель</label>
                <div class="col-sm-4">
                    <select class="selectpicker form-control" id="chif" title="Руководитель" name="chif"
                            data-live-search="true" data-container="body">
                        <c:forEach items="${workers}" var="wks">
                            <c:choose>
                                <c:when test="${wks.id eq division.chif}">
                                    <option value="${wks.id}" selected>${wks.surname} ${fn:substring(wks.name,0,1)}.${fn:substring(wks.patronymic,0,1)}.</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${wks.id}">${wks.surname} ${fn:substring(wks.name,0,1)}.${fn:substring(wks.patronymic,0,1)}.</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="mediator" class="col-sm-2 control-label">Ответственный</label>
                <div class="col-sm-4">
                    <select class="selectpicker form-control" id="mediator" title="Ответственный" name="mediator"
                            data-live-search="true" data-container="body">
                        <c:forEach items="${workers}" var="wks">
                            <c:choose>
                                <c:when test="${wks.id eq division.mediator}">
                                    <option value="${wks.id}" selected>${wks.surname} ${fn:substring(wks.name,0,1)}.${fn:substring(wks.patronymic,0,1)}.</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${wks.id}">${wks.surname} ${fn:substring(wks.name,0,1)}.${fn:substring(wks.patronymic,0,1)}. (${wks.division.name})</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-info">Подтвердить</button>
                    <a class="btn btn-default"
                       href="${pageContext.request.contextPath}/divisionList?active_filial=${division.filial_id}">Отмена</a>
                </div>
            </div>
        </form>
    </c:if>
</section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
</body>
</html>