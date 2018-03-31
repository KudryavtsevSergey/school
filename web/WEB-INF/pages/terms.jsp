<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/header.jsp"%>

<c:if test="${type != 'edit'}">
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>number</th>
        <th>start</th>
        <th>end</th>

        <th>change</th>
        <th>delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${terms}" var="term">
        <tr>
            <th>${term.termId}</th>
            <th>${term.number}</th>
            <th>${term.start}</th>
            <th>${term.end}</th>

            <th><a href="<c:url value="/terms/edit/${term.termId}"/>">change</a></th>
            <th><a href="<c:url value="/terms/remove/${term.termId}"/>">delete</a></th>
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

<c:url var="addAction" value="/terms/add"/>

<form:form action="${addAction}" commandName="term">
    <c:if test="${type == 'edit'}">
        <div class="form-group">
            <form:label path="termId">
                <spring:message text="ID"/>
            </form:label>
            <form:input class="form-control styled" path="termId" readonly="true" size="8"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="number">
            <spring:message text="number"/>
        </form:label>
        <form:input class="form-control styled" path="number"/>
    </div>
    <div class="form-group">
        <form:label path="start">
            <spring:message text="start"/>
        </form:label>
        <form:input class="form-control styled" path="start"/>
    </div>
    <div class="form-group">
        <form:label path="end">
            <spring:message text="end"/>
        </form:label>
        <form:input class="form-control styled" path="end"/>
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
