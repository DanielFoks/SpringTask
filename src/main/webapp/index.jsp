<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringWeb</title>
</head>
<body>
<h3>Are you:</h3>
<br/>
<a href="<c:url value="/administrator"/>" target="_blank">Administrator</a>
<a href="<c:url value="/customer"/>" target="_blank">Customer</a>
<br/>
</body>
</html>