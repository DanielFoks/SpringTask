<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Customer Page</title>
    <link href="../../resources/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>

    <c:url var="checkAction" value="/customerSuccess"/>

    <form:form action="${checkAction}" commandName="customer" modelAttribute="customer">
        <table>
            <tr>
                <td>
                    <form:label path="fio">
                        <spring:message text="FIO"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="fio"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message text="Password"/>
                    </form:label>
                </td>
                <td>
                    <form:password path="password"/>
                </td>
            <tr>
                <td colspan="2">
                    <input type="submit"
                           value="<spring:message text="ENTER"/>"/>
                </td>
            </tr>
        </table>
    </form:form>

</body>
</html>