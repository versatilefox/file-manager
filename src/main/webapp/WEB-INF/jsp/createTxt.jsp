<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<body>
<form action="/addFile" method="post">
    Filename <input type="text" name="name">
    <input type="hidden" name="path" value="<%=request.getParameter("nextFolder")%>">">
    <textarea name="text" rows="100" cols="100"></textarea>
    <input type="submit">
</form>
</body>
</html>