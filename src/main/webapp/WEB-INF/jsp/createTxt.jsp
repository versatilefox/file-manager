<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
<form action="/addFile" method="post">
    <spring:message code="file.name" text="default"/><input type="text" required name="name">
    <input type="hidden" name="path" value="<%=request.getParameter("nextFolder")%>">">
    <textarea name="text" rows="100" cols="100"></textarea>
    <input type="submit" value="<spring:message code="file.add" text="default"/>">
</form>
</body>
</html>