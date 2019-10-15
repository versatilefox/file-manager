<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
    <style>
        table {
            width: 100%;
            border: 0.5px double black;
            border-collapse: collapse;
        }
        th {
            text-align: left;
            background: #ccc;
            padding: 5px;
            border: 1px solid black;
        }
        td {
            padding: 5px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
<a href="/addFile?nextFolder=${nextFolder}"><spring:message code="file.addTxt"/></a>
<form action="/list/addFolder" method="get">
    <label for="name"><spring:message code="file.name" text="default"/></label>
    <input type="text" name="name" id="name">
    <input type="hidden" name="nextFolder" value="<%=request.getParameter("nextFolder")%>">
    <input type="submit" value="<spring:message code="file.addFolder" text="default"/>">
</form>
<form method="POST" enctype="multipart/form-data"
      action="/list/upload">
    <spring:message code="file.fileToUpload" text="default"/><input type="file" name="file">
    <input type="hidden" name="nextFolder" value="<%=request.getParameter("nextFolder")%>">
    <input type="submit" value="<spring:message code="file.upload" text="default"/>">
</form>



<table>
    <tr>
        <th><spring:message code="file.name" text="default"/></th>
        <th><spring:message code="file.action" text="default"/></th>
        <th><spring:message code="file.note" text="default"/></th>
    </tr>
    <c:forEach items="${files}" var="file">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${file.isFile() == true}">
                        <img src="${document}">
                        <a href="/list/seeFile?nextFolder=${file.getAbsolutePath()}">${file.getName()}</a>
                    </c:when>
                    <c:otherwise>
                        <img src="${folder}">
                        <a href="/list?nextFolder=${file.getAbsolutePath()}">${file.getName()}</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="/list/delete?deleted=${file.getAbsolutePath()}"><spring:message code="file.delete" text="default"/></a>
                <c:if test="${fn:endsWith(file, '.txt')==true}">
                    <a href="/editTxt?nextFolder=${file.getAbsolutePath()}"><spring:message code="file.edit" text="default"/></a>
                </c:if>
                <c:if test="${file.isDirectory() == false}">
                    <a href="/list/download?nextFolder=${file.getAbsolutePath()}"><spring:message code="file.download" text="default"/></a>
                </c:if>
            </td>

            <td>
                <a href="/list/seeNote?nextFolder=${file.getAbsolutePath()}"><spring:message code="file.seeNote" text="default" /></a>

            </td>

        </tr>
    </c:forEach>

</table>
</body>
</html>
