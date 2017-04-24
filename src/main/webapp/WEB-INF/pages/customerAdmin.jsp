<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>CustomerAdmin</title>
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>

<h1>Customer List</h1>
<c:if test="${!empty listCustomers}">
  <table class="tg">
    <tr>
      <th width="80">ID</th>
      <th width="120">FIO</th>
      <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listCustomers}" var="customer">
      <tr>
        <td>${customer.id}</td>
        <td>${customer.fio}</td>
        <td><a href="<c:url value='/customerAdmin/remove/${customer.id}'/>">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>



<c:url var="addAction" value="/customerAdmin/add"/>

<form:form action="${addAction}" commandName="customerAdmin" modelAttribute="customer">
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
               value="<spring:message text="Add Customer"/>"/>
      </td>
    </tr>
  </table>
</form:form>

</body>
</html>
