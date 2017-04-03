<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Управление доступом</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
<jsp:include page="_header.jsp"/>
<section class="container">
    <h2>Управление доступом</h2>
    <p style="color: red;">${errorString}</p>
    <a class="btn btn-primary pull-right" style="margin-bottom: 10px" href="createAccess">Создать новый ресурс</a>
    <p>
    <table class="table table-striped table-bordered table-hover" border="1" cellpadding="5" cellspacing="1">
        <tr class="success">
            <th class="text-center">Ресурс</th>
            <th class="text-center">Суперпользователь</th>
            <th class="text-center">Администратор</th>
            <th class="text-center">Ответственный</th>
            <th class="text-center">Общий</th>
        </tr>
        <form method="POST" action="accessList">
            <c:forEach items="${access}" var="acs">
                <c:if test="${acs.editable == 1}">
                    <tr>
                        <td>${acs.url}</td>
                        <td>
                            <c:choose>
                                <c:when test="${acs.role == 1}">
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="1"
                                           checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="1" disabled />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${acs.role == 3}">
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="3"
                                           checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="3" disabled />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${acs.role == 7}">
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="7"
                                           checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="7" disabled />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${acs.role == 15}">
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="15"
                                           checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="role_${fn:substringAfter(acs.url, "/")}" value="15" disabled />
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </form>
        <%--
                <!-- Modal (.modal-body h4) & (.modal-footer a>href) - from filial_del.js -->
                <div class="modal fade" id="ModalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Удаление филиала</h4>
                            </div>
                            <div class="modal-body">
                                <h3>Вы действительно хотите удалить филила:</h3>
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
        --%>
    </table>
    <a class="btn btn-primary pull-right" href="createAccess">Создать новый ресурс</a>
</section>
<jsp:include page="_footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/filial_del.js"></script>
</body>
</html>