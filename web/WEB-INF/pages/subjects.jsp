<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 13:34
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
        <th>description</th>
    <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
        <th>change</th>
        <th>delete</th>
    </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subjects}" var="subject">
        <tr>
            <th>${subject.subjectId}</th>
            <th>${subject.name}</th>
            <th>${subject.description}</th>
            <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
            <th><a href="<c:url value="/subjects/edit/${subject.subjectId}"/>">change</a></th>
            <th><a href="<c:url value="/subjects/remove/${subject.subjectId}"/>" onclick="return confirm_delete()">delete</a></th>
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

<c:url var="addAction" value="/subjects/add"/>

<form:form action="${addAction}" commandName="subject">
    <c:if test="${type == 'edit'}">
        <div class="form-group">
            <form:label path="subjectId">
                <spring:message text="ID"/>
            </form:label>
            <form:input class="form-control styled" path="subjectId" readonly="true" size="8"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="name">
            <spring:message text="name"/>
        </form:label>
        <form:input class="form-control styled" path="name"/>
    </div>
    <div class="form-group">
        <form:label path="description">
            <spring:message text="description"/>
        </form:label>
        <form:input class="form-control styled" path="description"/>
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
