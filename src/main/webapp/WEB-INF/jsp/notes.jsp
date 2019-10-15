<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<form action="/list/seeNote" method="POST">
    <c:choose>
        <c:when test="${emptyNote == true}">
            <spring:message code="file.note" text="default"/> <input type="text" name="message">
            <input type="hidden" name="nextFolder" value="<%=request.getParameter("nextFolder")%>">
            <input type="submit" value="<spring:message code="file.add" text="default"/>">
        </c:when>
        <c:otherwise>
            Note <input type="text" name="message" value="${note.message}">
            <input type="hidden" name="nextFolder" value="${note.path}">
            <input type="submit" value="<spring:message code="file.edit" text="default"/>">
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
