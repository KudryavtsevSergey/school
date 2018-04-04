<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/header.jsp" %>


<a href="<c:url value='/main'/>" class="button">
    Back
</a>
<br/>

<c:if test="${type != 'edit'}">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>number</th>
            <th>startTime</th>
            <th>endTime</th>
            <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
                <th>change</th>
                <th>delete</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lessonTimes}" var="lessonTime">
            <tr>
                <th>${lessonTime.lessonId}</th>
                <th>${lessonTime.number}</th>
                <th>${lessonTime.startTime}</th>
                <th>${lessonTime.endTime}</th>
                <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
                    <th><a href="<c:url value="/lessonTimes/edit/${lessonTime.lessonId}"/>">change</a></th>
                    <th><a href="<c:url value="/lessonTimes/remove/${lessonTime.lessonId}"/>" onclick="return confirm_delete()">delete</a></th>
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

<c:url var="addAction" value="/lessonTimes/add"/>

<form:form action="${addAction}" commandName="lessonTime">
    <c:if test="${type == 'edit'}">
        <div class="form-group">
            <form:label path="lessonId">
                <spring:message text="ID"/>
            </form:label>
            <form:input class="form-control styled" path="lessonId" readonly="true" size="8"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="number">
            <spring:message text="number"/>
        </form:label>
        <form:input class="form-control styled" path="number"/>
    </div>
    <div class="form-group">
        <form:label path="startTime">
            <spring:message text="startTime"/>
        </form:label>
        <form:input class="form-control styled" path="startTime"/>
    </div>
    <div class="form-group">
        <form:label path="endTime">
            <spring:message text="endTime"/>
        </form:label>
        <form:input class="form-control styled" path="endTime"/>
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
<%@include file="/WEB-INF/views/footer.jsp" %>
