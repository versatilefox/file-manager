<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<form action="/list/seeNote" method="POST">
    <c:choose>
        <c:when test="${emptyNote == true}">
            Message <input type="text" name="message">
            <input type="hidden" name="nextFolder" value="<%=request.getParameter("nextFolder")%>">
            <input type="submit" value="Add">
        </c:when>
        <c:otherwise>
            Message <input type="text" name="message" value="${note.message}">
            <input type="hidden" name="nextFolder" value="${note.path}">
            <input type="submit" value="edit">
        </c:otherwise>
    </c:choose>
</form>
</body>
</html>
