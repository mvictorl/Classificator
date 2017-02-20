<%@ page import="com.mvictorl.beans.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<section class="container" id="header">
    <div class="content row">
        <div class="col-lg-12">
            <header class="clearfix">
                <nav class="navbar navbar-default navbar-fixed-top">
                    <div class="container-fluid">
                        <section class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <div class="navbar-brand">
                                <img src="${pageContext.request.contextPath}/images/GTBelarus-logo.png">
                            </div>
                        </section>
                        <section class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="page_home"><a href="${pageContext.request.contextPath}/">
                                    <span class="glyphicon glyphicon-home"></span>
                                    <span class="sr-only">(current)</span></a>
                                </li>
                                <li class="page_first"><a href="${pageContext.request.contextPath}/productList">product
                                    list</a></li>
                                <c:if test="${(sessionScope.loginedUser.role.id < 2)}">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-haspopup="true" aria-expanded="false">ввод данных</a>
                                    <ul class="dropdown-menu">
                                        <c:if test="${(sessionScope.loginedUser.role.id == 0)}">
                                            <li><a href="${pageContext.request.contextPath}/filialList">Филиалы</a></li>
                                        </c:if>
                                        <c:if test="${(sessionScope.loginedUser.role.id <= 1)}">
                                            <li><a href="${pageContext.request.contextPath}/subdivisionList">Подразделения</a></li>
                                        </c:if>
                                        <li><a href="${pageContext.request.contextPath}/employeeList">Сотрудники</a>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/systemList">АИС</a></li>
                                        <li><a href="${pageContext.request.contextPath}/hardwareList">СВТ</a></li>
                                        <li><a href="${pageContext.request.contextPath}/softwareList">ПО</a></li>
                                    </ul>
                                </li>
                                </c:if>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <c:choose>
                                    <c:when test="${sessionScope.loginedUser == null}">
                                <%--<% if (session.getAttribute("loginedUser") == null) { %>--%>
                                <li><a href="${pageContext.request.contextPath}/login" data-toggle="tooltip"
                                       data-placement="bottom" data-delay="1" title="Вход">
                                    <span class="glyphicon glyphicon-log-in"></span>
                                </a></li>
                                    </c:when>
                                    <c:when test="${sessionScope.loginedUser != null}">
                                <li class="page_second"><a href="${pageContext.request.contextPath}/userInfo">
                                    <b>${sessionScope.loginedUser.name}</b></a><a href="/doLogoff" data-toggle="tooltip"
                                                                data-delay="1"  data-placement="bottom" title="Выход">
                                    <span class="glyphicon glyphicon-log-out"></span></a>
                                </li>
                                    </c:when>
                                </c:choose>
                            </ul>
                        </section>
                    </div> <%--container-fluid--%>
                </nav> <%--container-fluid--%>
            </header>
        </div> <!-- column -->
    </div> <!-- content -->
</section>
