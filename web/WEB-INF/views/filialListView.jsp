<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Филиалы</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp" />
    <section class="container">
        <h2>Список филиалов</h2>
        <p style="color: red;">${errorString}</p>
        <table class="table table-striped table-bordered table-hover" border="1" cellpadding="5" cellspacing="1">
            <tr class="success">
                <th class="text-center">Код</th>
                <th class="text-center">Сокращение</th>
                <th class="text-center">Наименование</th>
                <th class="text-center">Редактировать</th>
                <th class="text-center">Удалить</th>
            </tr>
            <c:forEach items="${filialList}" var="filial">
                <tr>
                    <td>${filial.id}</td>
                    <td>${filial.sh_name}</td>
                    <td>${filial.name}</td>
                    <td class="text-center">
                        <a class="btn btn-info btn-xs" href="editFilial?id=${filial.id}">Редактировать</a>
                    </td>
                    <td class="text-center">
                        <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#myModal">
                            Удалить
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabel">Удаление филиала</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h3>Вы подтверждаете удаление данного филиала?</h3>
                                    </div>
                                    <div class="modal-footer">
                                        <a type="button" class="btn btn-info btn-lg" data-dismiss="modal">Нет</a>
                                        <a href="deleteFilial?code=${filial.id}" type="button" class="btn btn-danger btn-lg">Да</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a class="btn btn-primary pull-right" href="createFilial">Создать филиал</a>
    </section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>