<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<body>
<form action="/editTxt" method="post">
    <input type="text" name="name" value="${file.getName()}">
    <input type="hidden" name="path" value="${file.getAbsolutePath()}">
    <textarea name="text" rows="100" cols="100">${text}</textarea>
    <input type="submit">
</form>
</body>
</html>