<%@include file="/WEB-INF/views/header.jsp"%>

<c:url var="addAction" value="/login"/>

<form:form action="${addAction}" method="POST" modelAttribute="userAuthInfo">

    <c:if test="${not empty errorMessage}">
        <span class="error">${errorMessage}</span><br/>
    </c:if>
    <div class="form-group">
        <form:label path="username">
            <spring:message text="Login"/>
        </form:label>
        <form:input class="form-control styled" path="username"/>
    </div>
    <div class="form-group">
        <form:label path="password">
            <spring:message text="Password"/>
        </form:label>
        <form:input class="form-control styled" path="password"/>
    </div>

    <div class="form-group">
        <input type="submit" class="button" value="<spring:message text="sign"/>"/>
    </div>
</form:form>

<%@include file="/WEB-INF/views/footer.jsp"%>