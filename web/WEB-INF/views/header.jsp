<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<c:url var="context" value="${pageContext.request.contextPath}"/>
<head>
    <title>School</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>
        <%@include file="/resources/css/bootstrap.min.css" %>
        <%@include file="/resources/css/style.css" %>
        <%@include file="/resources/css/menu.css" %>
    </style>

</head>
<body>
<header>
    <div class="container-fluid">
        <div class="row">
            <nav class="col-md-12">
                <ul id="primary_nav">
                    <c:if test="${empty sessionScope.userAuthInfo or sessionScope.userAuthInfo.level==0}">
                        <li id="buy">
                            <a href="<c:url value='/registration'/>">
                                Sign in
                            </a>
                        </li>

                    </c:if>

                    <c:if test="${sessionScope.userAuthInfo.level!=0 and not empty sessionScope.userAuthInfo}">
                        <li>
                            <h4 style="float: left; margin-right: 15px;" class="user-name">Hello, ${sessionScope.userAuthInfo.username}!</h4>
                            <a href="<c:url value='/logout'/>" class="button">
                                sign out
                            </a>
                        </li>
                    </c:if>
                </ul>
                <a id="mobile" class="cmn-toggle-switch cmn-toggle-switch__htx open_close" onclick="toggleClass()"
                   href="javascript:void(0);">
                    <span>Menu mobile</span>
                </a>
                <div id="main-menu" class="main-menu">
                    <div id="header_menu">
                        <div style="width: 170px; height:30px;"></div>
                        <%--<img alt="img" data-retina="true" width="170" height="30">--%>
                    </div>
                    <a href="javascript:void(0);" onclick="toggleClass()" class="open_close" id="close_in">
                        <div style="font-size: 30px" class="icon_close">X</div>
                    </a>
                    <ul>
                        <li>
                            <a href="<c:url value='/' />">Home</a>
                        </li>
                        <li>
                            <a href="<c:url value='/clazzes'/>">clazzes</a>
                        </li>
                        <li>
                            <a href="<c:url value='/lessonTimes'/>">lessonTimes</a>
                        </li>
                        <%--<li>--%>
                        <%--<a href="<c:url value='/marks'/>">marks</a>--%>
                        <%--</li>--%>
                        <c:if test="${sessionScope.userAuthInfo.level>=6 and not empty sessionScope.userAuthInfo}">
                            <li>
                                <a href="<c:url value='/pupils'/>">pupils</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.userAuthInfo.level>=12 and not empty sessionScope.userAuthInfo}">
                            <li>
                                <a href="<c:url value='/roles'/>">roles</a>
                            </li>
                        </c:if>
                        <li>
                            <a href="<c:url value='/subjectInSchedules'/>">subjectInSchedules</a>
                        </li>
                        <li>
                            <a href="<c:url value='/subjects'/>">subjects</a>
                        </li>
                        <li>
                            <a href="<c:url value='/teachers'/>">teachers</a>
                        </li>
                        <li>
                            <a href="<c:url value='/terms'/>">terms</a>
                        </li>
                        <li>
                            <a href="<c:url value='/users'/>">users</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</header>

<section class="header-video">
    <div id="hero_video">
        <div id="sub_content_in">
            <h1>Our<br>School</h1>
        </div>
    </div>
</section>
<section class="bg_white margin_60_30">
    <div class="container">
        <div class="row list_tabs">
            <div class="col-md-12 col-sm-12">