<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/header.jsp"%>

<a href="<c:url value='/main'/>" class="button">
    Back
</a>
<br/>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>value</th>
        <th>date</th>
        <th>pupilId</th>
        <th>subjectId</th>
        <th>teacherId</th>
        <th>termNumber</th>
        <th>type</th>

        <th>change</th>
        <th>delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${marks}" var="mark">
        <tr>
            <th>${mark.markId}</th>
            <th>${mark.value}</th>
            <th>${mark.date}</th>
            <th>${mark.pupilId}</th>
            <th>${mark.subjectId}</th>
            <th>${mark.teacherId}</th>
            <th>${mark.termNumber}</th>
            <th>${mark.type}</th>

            <th><a href="<c:url value="/marks/edit/${mark.markId}"/>">change</a></th>
            <th><a href="<c:url value="/marks/remove/${mark.markId}"/>" onclick="return confirm_delete()">delete</a></th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h1>Add a mark</h1>

<c:url var="addAction" value="/marks/add"/>

<form:form action="${addAction}" commandName="mark">
    <c:if test="${type == 'edit'}">
        <div class="form-group">
            <form:label path="markId">
                <spring:message text="ID"/>
            </form:label>
            <form:input class="form-control styled" path="markId" readonly="true" size="8"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="value">
            <spring:message text="value"/>
        </form:label>
        <form:input class="form-control styled" path="value"/>
    </div>
    <div class="form-group">
        <form:label path="date">
            <spring:message text="date"/>
        </form:label>
        <form:input class="form-control styled" path="date"/>
    </div>
    <div class="form-group">
        <form:label path="pupilId">
            <spring:message text="pupilId"/>
        </form:label>
        <form:input class="form-control styled" path="pupilId"/>
    </div>
    <div class="form-group">
        <form:label path="subjectId">
            <spring:message text="subjectId"/>
        </form:label>
        <form:input class="form-control styled" path="subjectId"/>
    </div>
    <div class="form-group">
        <form:label path="teacherId">
            <spring:message text="teacherId"/>
        </form:label>
        <form:input class="form-control styled" path="teacherId"/>
    </div>
    <div class="form-group">
        <form:label path="termNumber">
            <spring:message text="termNumber"/>
        </form:label>
        <form:input class="form-control styled" path="termNumber"/>
    </div>
    <div class="form-group">
        <form:label path="type">
            <spring:message text="type"/>
        </form:label>
        <form:input class="form-control styled" path="type"/>
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

<%@include file="/WEB-INF/views/footer.jsp"%>
