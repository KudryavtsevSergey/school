<%--
  Created by IntelliJ IDEA.
  User: Doom
  Date: 25.02.2018
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/views/header.jsp" %>

<a href="<c:url value='/main'/>" class="button">
    Back
</a>

<c:if test="${type != 'edit'}">
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>number</th>
            <th>letter mark</th>
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
        <c:forEach items="${clazzes}" var="clazz">
            <tr>
                <th>${clazz.classId}</th>
                <th>${clazz.number}</th>
                <th>${clazz.letterMark}</th>
                <c:if test="${sessionScope.userAuthInfo.level>=8 and not empty sessionScope.userAuthInfo}">
                    <th><a href="<c:url value="/clazzes/edit/${clazz.classId}"/>">change</a></th>
                    <th><a href="<c:url value="/clazzes/remove/${clazz.classId}"/>" onclick="return confirm_delete()">delete</a>
                    </th>
                    <th><a href="<c:url value='/api/docs/getClassPupilsList/${clazz.classId}/pdf'/>">Generate pdf list
                        of pupils</a> <br>
                        <a href="<c:url value='/api/docs/getClassSchedule/${clazz.classId}/pdf'/>">Generate pdf sheldule
                            of class</a></th>
                    <th><a href="<c:url value='/api/docs/getClassPupilsList/${clazz.classId}/csv'/>">Generate csv list
                        of pupils</a><br>
                        <a href="<c:url value='/api/docs/getClassSchedule/${clazz.classId}/csv'/>">Generate csv sheldule
                            of class</a>
                    </th>
                    <th><a href="<c:url value='/api/docs/getClassPupilsList/${clazz.classId}/xls'/>">Generate xls list
                        of pupils</a>
                        <br>
                        <a href="<c:url value='/api/docs/getClassSchedule/${clazz.classId}/xls'/>">Generate xls sheldule
                            of class</a>
                    </th>
                </c:if>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</c:if>

<script>
    function accept(e) {

        var result = confirm("Are you sure?");

    }

</script>
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

    <c:url var="addAction" value="/clazzes/add"/>

    <form:form action="${addAction}" commandName="clazz">
        <c:if test="${type == 'edit'}">
            <div class="form-group">
                <form:label path="classId">
                    <spring:message text="ID"/>
                </form:label>
                <form:input path="classId" class="form-control styled" readonly="true" size="8"/>
            </div>
        </c:if>
        <div class="form-group">
            <form:label path="number">
                <spring:message text="number"/>
            </form:label>
            <form:input class="form-control styled" path="number"/>
        </div>
        <div class="form-group">
            <form:label path="letterMark">
                <spring:message text="letterMark"/>
            </form:label>
            <form:input class="form-control styled" path="letterMark"/>
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