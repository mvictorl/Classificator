<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Подразделения</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<section class="container">
    <h2>Список подразделений</h2>
    <p style="color: red;">${errorString}</p>

    <form class="form-horizontal" method="POST" action="divisionList">
        <div class="form-group">
            <label for="filial" class="col-sm-2 control-label">Филиал</label>
            <div class="col-sm-4">
                <select class="selectpicker form-control" id="filial" title="Филиал" name="active_filial"
                        data-live-search="true" data-container="body" onchange="this.form.submit();">
                    <c:forEach items="${filials}" var="fl">
                        <c:choose>
                            <c:when test="${fl.id eq active_filial}">
                                <option value="${fl.id}" selected>${fl.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${fl.id}">${fl.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <input type="hidden" name="active_filial" value="null">
    </form>
    <c:if test="${active_filial != null}">
        <form class="form-horizontal" method="POST" action="divisionList">
            <div class="form-group">
                <label for="division" class="col-sm-2 control-label">Подразделения</label>
                <div class="col-sm-4">
                    <select class="selectpicker form-control" id="division" title="Подразделение" name="active_division"
                            data-live-search="true" data-container="body" onchange="this.form.submit();">
                        <c:forEach items="${divisions}" var="dvs">
                            <c:choose>
                                <c:when test="${dvs.id eq active_division}">
                                    <option value="${dvs.id}" selected>${dvs.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${dvs.id}">${dvs.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="hidden" name="active_filial" value="${active_filial}">
        </form>
    </c:if>
    <c:if test="${active_division != null}">
        <a class="btn btn-primary pull-right" href="createDivision">Создать подразделение</a>
        <table class="table table-striped table-bordered table-hover" border="1" cellpadding="5" cellspacing="1">
            <tr class="success">
                <th class="text-center">Код</th>
                <th class="text-center">Наименование</th>
                <th class="text-center">Руководитель</th>
                <th class="text-center">Ответственный</th>
                <th class="text-center">Редактировать</th>
                <th class="text-center">Удалить</th>
            </tr>
            <c:forEach items="${divisions}" var="dvs">
                <tr>
                    <td>${dvs.id}</td>
                    <td>${dvs.name}</td>
                    <td>${dvs.chif}</td>
                    <td>${dvs.mediator}</td>
                    <td class="text-center">
                        <a class="btn btn-info btn-xs" href="editDivision?id=${dvs.id}">Редактировать</a>
                    </td>
                    <td class="text-center">
                        <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#ModalDelete"
                                data-id="${dvs.id}" data-name="${dvs.name}">Удалить</button>
                    </td>
                </tr>
            </c:forEach>
            <!-- Modal (.modal-body h4) & (.modal-footer a>href) - from filial_del.js -->
            <div class="modal fade" id="ModalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Удаление подразделения</h4>
                        </div>
                        <div class="modal-body">
                            <h3>Вы действительно хотите удалить подразделение:</h3>
                            <h4 class="alert alert-success"></h4>
                        </div>
                        <div class="modal-footer">
                            <a type="button" class="btn btn-info btn-lg" data-dismiss="modal">Нет</a>
                            <a href="#" type="button"
                               class="btn btn-danger btn-lg">Да</a>
                        </div>
                    </div>
                </div>
            </div><!-- Modal -->
        </table>
        <a class="btn btn-primary pull-right" href="createDivision">Создать подразделение</a>
    </c:if>
</section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-select.js"></script>
<script src="${pageContext.request.contextPath}/js/division_del.js"></script>
</body>
</html>