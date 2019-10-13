<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" pageEncoding="UTF-8"%>
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
<a href="/addFile?nextFolder=${nextFolder}">Add txt</a>
<form action="/list/addFolder" method="get">
    <label for="name">Name</label>
    <input type="text" name="name" id="name">
    <label for="note">Note</label>
    <input type="text" name="note" id="note">
    <input type="hidden" name="nextFolder" value="<%=request.getParameter("nextFolder")%>">
    <input type="submit" value="Add folder">
</form>
<form method="POST" enctype="multipart/form-data"
      action="/list/upload">
    File to upload: <input type="file" name="file">
    Note: <input type="text" name="note">
    <input type="hidden" name="nextFolder" value="<%=request.getParameter("nextFolder")%>">
    <input type="submit" value="Upload">
</form>



<table>
    <tr>
        <th>File</th>
        <th>Action</th>
        <th>Note</th>
    </tr>
    <c:forEach items="${files}" var="file">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${file.isFile() == true}">
                        <img src="${document}">
                        <a href="/list/download?nextFolder=${file.getAbsolutePath()}">${file.getName()}</a>
                    </c:when>
                    <c:otherwise>
                        <img src="${folder}">
                        <a href="/list?nextFolder=${file.getAbsolutePath()}">${file.getName()}</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="/list/delete?deleted=${file.getAbsolutePath()}">Delete</a>
                <c:if test="${fn:endsWith(file, '.txt')==true}">
                    <a href="/editTxt?nextFolder=${file.getAbsolutePath()}">Edit</a>
                </c:if>
                <c:if test="${file.isDirectory() == false}">
                    <a href="/list/download?nextFolder=${file.getAbsolutePath()}">Download</a>
                </c:if>
            </td>

            <td>
                <a href="/list/seeNote?nextFolder=${file.getAbsolutePath()}">See note</a>

            </td>

        </tr>
    </c:forEach>

</table>
</body>
</html>
