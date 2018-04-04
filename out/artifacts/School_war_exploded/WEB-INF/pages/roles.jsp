<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 24.02.2018
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/header.jsp"%>


<a href="<c:url value='/main'/>" class="button">
    Back
</a>
<br/>

<c:if test="${type != 'edit'}">
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>name</th>
        <th>level</th>
    <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
        <th>change</th>
        <th>delete</th>
    </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${roles}" var="role">
        <tr>
            <th>${role.roleId}</th>
            <th>${role.name}</th>
            <th>${role.level}</th>
            <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
            <th><a href="<c:url value="/roles/edit/${role.roleId}"/>">change</a></th>
            <th><a href="<c:url value="/roles/remove/${role.roleId}"/>" onclick="return confirm_delete()">delete</a></th>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</c:if>
<c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
<h1>
    <c:choose>
        <c:when test="${type == 'edit'}">
            Edit
        </c:when>
        <c:otherwise>
            Add
        </c:otherwise>
    </c:choose>
</h1>

<c:url var="addAction" value="/roles/add"/>

<form:form action="${addAction}" commandName="role">
    <c:if test="${type == 'edit'}">
        <div class="form-group">
            <form:label path="roleId">
                <spring:message text="ID"/>
            </form:label>
            <form:input class="form-control styled" path="roleId" readonly="true" size="8"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="name">
            <spring:message text="name"/>
        </form:label>
        <form:input class="form-control styled" path="name"/>
    </div>
    <div class="form-group">
        <form:label path="level">
            <spring:message text="Level"/>
        </form:label>
        <form:input class="form-control styled" path="level"/>
    </div>

    <div class="form-group">
        <c:choose>
            <c:when test="${type == 'edit'}">
                <input type="submit" class="button" value="<spring:message text="Edit"/>"/>
            </c:when>
            <c:otherwise>
                <input type="submit" class="button" value="<spring:message text="Add"/>"/>
            </c:otherwise>
        </c:choose>
    </div>
</form:form>
</c:if>
<%@include file="/WEB-INF/views/footer.jsp"%>
