<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <h2>Редактировать пользователя</h2>
    <c:if test="${not empty user}">
        <form class="form-horizontal" method="POST" action="doEditUser">
            <div class="form-group">
                <label for="id" class="col-sm-2 control-label">ID</label>
                <div class="col-sm-2">
                    <input type="number" min="0" step="1" class="form-control" id="id" placeholder="Код"
                           name="id" value="${user.id}" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Учетная запись</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" id="name" placeholder="Учетная запись"
                           name="name" value='${user.name}'/>
                </div>
            </div>

            <div class="form-group">
                <label for="pass_1" class="col-sm-2 control-label">Пароль</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="pass_1" placeholder="Пароль"
                           name="pass_1" value='${user.password}'/>
                </div>
            </div>
            <div class="form-group">
                <label for="pass_2" class="col-sm-2 control-label">Подтверждение</label>
                <div class="col-sm-4">
                    <input type="password" class="form-control" id="pass_2" placeholder="Пароль"
                           name="pass_2" value='${user.password}'/>
                </div>
            </div>
            <div class="form-group">
                <label for="role" class="col-sm-2 control-label">Роль</label>
                <div class="col-sm-4">
                    <select class="selectpicker form-control" id="role" title="Роль" name="role"
                            data-live-search="true">
                        <c:forEach items="${roles}" var="rl">
                            <c:choose>
                                <c:when test="${rl.id eq user.role.id}">
                                    <option value="${rl.id}" selected>${rl.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${rl.id}">${rl.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="worker" class="col-sm-2 control-label">Сотрудник</label>
                <div class="col-sm-4">
                    <select class="selectpicker form-control" id="worker" title="Сотрудники" name="worker"
                            data-live-search="true">
                        <c:forEach items="${workers}" var="wrk">
                            <c:choose>
                                <c:when test="${wrk.id eq user.worker.id}">
                                    <option selected value="${wrk.id}">${wrk.surname} ${fn:substring(wrk.name,0,1)}.${fn:substring(wrk.patronymic,0,1)}.</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${wrk.id}">${wrk.surname} ${fn:substring(wrk.name,0,1)}.${fn:substring(wrk.patronymic,0,1)}.</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-info">Подтвердить</button>
                    <a class="btn btn-default" href="${pageContext.request.contextPath}/userList">Отмена</a>
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