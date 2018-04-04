<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 13:56
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
            <th>classId</th>
            <th>description</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>pathronymic</th>
            <th>phoneNumber</th>
            <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
                <th>change</th>
                <th>delete</th>
                <th>pdf</th>
                <th>csv</th>
                <th>xls</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers}" var="teacher">
            <tr>
                <th>${teacher.teacherId}</th>
                <th>${teacher.classId}</th>
                <th>${teacher.description}</th>
                <th>${teacher.firstName}</th>
                <th>${teacher.lastName}</th>
                <th>${teacher.pathronymic}</th>
                <th>${teacher.phoneNumber}</th>
                <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
                    <th><a href="<c:url value="/teachers/edit/${teacher.teacherId}"/>">change</a></th>
                    <th><a href="<c:url value="/teachers/remove/${teacher.teacherId}"/>"
                           onclick="return confirm_delete()">delete</a></th>
                    <th><a href="<c:url value='/api/docs/getTeacherSchedule/${teacher.teacherId}/pdf'/>">Generate pdf
                        sheldule</a></th>
                    <th><a href="<c:url value='/api/docs/getTeacherSchedule/${teacher.teacherId}/csv'/>">Generate csv
                        sheldule</a></th>
                    <th><a href="<c:url value='/api/docs/getTeacherSchedule/${teacher.teacherId}/xls'/>">Generate xls
                        sheldule</a></th>
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

    <c:url var="addAction" value="/teachers/add"/>

    <form:form action="${addAction}" commandName="teacher">
        <c:if test="${type == 'edit'}">
            <div class="form-group">
                <form:label path="teacherId">
                    <spring:message text="ID"/>
                </form:label>
                <form:input class="form-control styled" path="teacherId" readonly="true" size="8"/>
            </div>
        </c:if>
        <div class="form-group">
            <form:label path="classId">
                <spring:message text="classId"/>
            </form:label>
            <form:input class="form-control styled" path="classId"/>
        </div>
        <div class="form-group">
            <form:label path="description">
                <spring:message text="description"/>
            </form:label>
            <form:input class="form-control styled" path="description"/>
        </div>
        <div class="form-group">
            <form:label path="firstName">
                <spring:message text="firstName"/>
            </form:label>
            <form:input class="form-control styled" path="firstName"/>
        </div>
        <div class="form-group">
            <form:label path="lastName">
                <spring:message text="lastName"/>
            </form:label>
            <form:input class="form-control styled" path="lastName"/>
        </div>
        <div class="form-group">
            <form:label path="pathronymic">
                <spring:message text="pathronymic"/>
            </form:label>
            <form:input class="form-control styled" path="pathronymic"/>
        </div>
        <div class="form-group">
            <form:label path="phoneNumber">
                <spring:message text="phoneNumber"/>
            </form:label>
            <form:input class="form-control styled" path="phoneNumber"/>
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
