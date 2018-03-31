<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 13:41
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
        <th>teacherId</th>
        <th>classId</th>
        <th>beginTime</th>
        <th>dayOfWeek</th>
        <th>place</th>
        <th>subectInScheduleId</th>
    <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
        <th>change</th>
        <th>delete</th>
    </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subjectInSchedules}" var="subjectInSchedule">
        <tr>
            <th>${subjectInSchedule.subjectId}</th>
            <th>${subjectInSchedule.teacherId}</th>
            <th>${subjectInSchedule.classId}</th>
            <th>${subjectInSchedule.beginTime}</th>
            <th>${subjectInSchedule.dayOfWeek}</th>
            <th>${subjectInSchedule.place}</th>
            <th>${subjectInSchedule.subectInScheduleId}</th>
            <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
            <th><a href="<c:url value="/subjectInSchedules/edit/${subjectInSchedule.subjectId}"/>">change</a></th>
            <th><a href="<c:url value="/subjectInSchedules/remove/${subjectInSchedule.subjectId}"/>" onclick="return confirm_delete()">delete</a></th>
            </c:if>
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
<c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
<c:url var="addAction" value="/subjectInSchedules/add"/>

<form:form action="${addAction}" commandName="subjectInSchedule">
    <c:if test="${type == 'edit'}">
        <div class="form-group">
            <form:label path="subjectId">
                <spring:message text="ID"/>
            </form:label>
            <form:input class="form-control styled" path="subjectId" readonly="true" size="8"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="teacherId">
            <spring:message text="teacherId"/>
        </form:label>
        <form:input class="form-control styled" path="teacherId"/>
    </div>
    <div class="form-group">
        <form:label path="classId">
            <spring:message text="classId"/>
        </form:label>
        <form:input class="form-control styled" path="classId"/>
    </div>
    <div class="form-group">
        <form:label path="beginTime">
            <spring:message text="beginTime"/>
        </form:label>
        <form:input class="form-control styled" path="beginTime"/>
    </div>
    <div class="form-group">
        <form:label path="dayOfWeek">
            <spring:message text="dayOfWeek"/>
        </form:label>
        <form:input class="form-control styled" path="dayOfWeek"/>
    </div>
    <div class="form-group">
        <form:label path="place">
            <spring:message text="place"/>
        </form:label>
        <form:input class="form-control styled" path="place"/>
    </div>
    <div class="form-group">
        <form:label path="subectInScheduleId">
            <spring:message text="subectInScheduleId"/>
        </form:label>
        <form:input class="form-control styled" path="subectInScheduleId"/>
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
