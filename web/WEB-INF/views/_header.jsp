<%@ page import="com.mvictorl.beans.User" %>
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
                                <li class="page_home"><a href="${pageContext.request.contextPath}/">home <span
                                        class="sr-only">(current)</span></a></li>
                                <li class="page_first"><a href="${pageContext.request.contextPath}/productList">product
                                    list</a></li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-haspopup="true" aria-expanded="false">данные</a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/subdivisionList">Подразделения</a>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/employeeList">Сотрудники</a>
                                        </li>
                                        <li><a href="${pageContext.request.contextPath}/systemList">АИС</a></li>
                                        <li><a href="${pageContext.request.contextPath}/hardwareList">СВТ</a></li>
                                        <li><a href="${pageContext.request.contextPath}/softwareList">ПО</a></li>
                                    </ul>
                                </li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <% if (session.getAttribute("loginedUser") == null) { %>
                                <li><a href="${pageContext.request.contextPath}/login">login</a></li>
                                <% } else { %>
                                <li class="page_second"><a href="${pageContext.request.contextPath}/userInfo">
                                    <b><%= ((User) session.getAttribute("loginedUser")).getName() %>
                                    </b> account</a></li>
                                <% } %>
                            </ul>
                        </section>
                    </div>
                    <%--container-fluid--%>
                </nav>
                <%--container-fluid--%>
            </header>
        </div><!-- column -->
    </div><!-- content -->
</section>
