<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/header.jsp" %>

<c:if test="${type != 'edit'}">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>username</th>
            <th>roleId</th>
            <th>email</th>
            <th>locked</th>
            <th>passHash</th>

            <th>change</th>
            <th>delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <th>${user.userId}</th>
                <th>${user.username}</th>
                <th>${user.roleId}</th>
                <th>${user.email}</th>
                <th>${user.locked}</th>
                <th>${user.passHash}</th>
                <th><a href="<c:url value="/users/edit/${user.userId}"/>">change</a></th>
                <th><a href="<c:url value="/users/remove/${user.userId}"/>">delete</a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

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

<c:url var="addAction" value="/users/add"/>

<form:form action="${addAction}" commandName="user">
    <c:if test="${type == 'edit'}">
        <div class="form-group">
            <form:label path="userId">
                <spring:message text="ID"/>
            </form:label>
            <form:input class="form-control styled" path="userId" readonly="true" size="8"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="username">
            <spring:message text="username"/>
        </form:label>
        <form:input class="form-control styled" path="username"/>
    </div>
    <div class="form-group">
        <form:label path="roleId">
            <spring:message text="roleId"/>
        </form:label>
        <form:input class="form-control styled" path="roleId"/>
    </div>
    <div class="form-group">
        <form:label path="email">
            <spring:message text="email"/>
        </form:label>
        <form:input class="form-control styled" path="email"/>
    </div>
    <div class="form-group">
        <form:label path="locked">
            <spring:message text="locked"/>
        </form:label>
        <form:input class="form-control styled" path="locked"/>
    </div>
    <div class="form-group">
        <form:label path="passHash">
            <spring:message text="passHash"/>
        </form:label>
        <form:input class="form-control styled" path="passHash"/>
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

<%@include file="/WEB-INF/views/footer.jsp" %>