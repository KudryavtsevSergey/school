<%@include file="/WEB-INF/views/header.jsp" %>

<ul>
    <a href="<c:url value='/api/docs/getFullListPupils/pdf'/>" class="button">
        Generate pupils list pdf
    </a>

    <a href="<c:url value='/api/docs/getFullListPupil/csv'/>" class="button">
        Generate pupils list csv
    </a>

    <a href="<c:url value='/api/docs/getFullListPupil/xls'/>" class="button">
        Generate pupils list xls
    </a>
    <br/>
    <br/>
    <h2>Our sections</h2>
    <c:if test="${userAuthInfo.level>=0 or empty userAuthInfo}">
        <li>
            <div>
                <a href="<c:url value='/clazzes'/>">
                    <h3>clazzes</h3>
                </a>
            </div>
        </li>
        <li>

            <div>
                <a href="<c:url value='/lessonTimes'/>">
                    <h3>lesson Times</h3>
                </a>
            </div>
        </li>
    </c:if>
    <%--<li>--%>
        <%--<div>--%>
            <%--<a href="<c:url value='/marks'/>">--%>
                <%--<h3>marks</h3>--%>
            <%--</a>--%>
        <%--</div>--%>
    <%--</li>--%>
    <c:if test="${userAuthInfo.level>=6 and not empty userAuthInfo}">
    <li>
        <div>
            <a href="<c:url value='/pupils'/>">
                <h3>pupils</h3>
            </a>
        </div>
    </li>
    <li>
        </c:if>
<c:if test="${userAuthInfo.level>=12 and not empty userAuthInfo}">
        <div>
            <a href="<c:url value='/roles'/>">
                <h3>roles</h3>
            </a>
        </div>
    </li>
    </c:if>
    <li>
    <div>
    <a href="<c:url value='/subjectInSchedules'/>">
    <h3>subject In Schedules</h3>
    </a>
    </div>
    </li>
<c:if test="${userAuthInfo.level>=0 or empty userAuthInfo}">
    <li>
        <div>
            <a href="<c:url value='/subjects'/>">
                <h3>subjects</h3>
            </a>
        </div>
    </li>
</c:if>
<c:if test="${userAuthInfo.level>=0 or empty userAuthInfo}">
    <li>
        <div>
            <a href="<c:url value='/teachers'/>">
                <h3>teachers</h3>
            </a>
        </div>
    </li>
</c:if>
<c:if test="${userAuthInfo.level>=0 or empty userAuthInfo}">
    <li>
        <div>
            <a href="<c:url value='/terms'/>">
                <h3>terms</h3>
            </a>
        </div>
    </li>
</c:if>
    <c:if test="${userAuthInfo.level>=0 or empty userAuthInfo}">
        <li>
            <div>
                <a href="<c:url value='/marks'/>">
                    <h3>marks</h3>
                </a>
            </div>
        </li>
    </c:if>
<%--<c:if test="${userAuthInfo.level>=8 and not empty userAuthInfo}">--%>
<li>
        <div>
            <a href="<c:url value='/users'/>">
                <h3>users</h3>
                <%--<small>Men - Women</small>--%>
            </a>
        </div>
    </li>
<%--</c:if>--%>
</ul>

<%@include file="/WEB-INF/views/footer.jsp" %>