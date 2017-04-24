<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <title>GoodAdmin</title>
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>

<h1>Good List</h1>
<c:if test="${!empty listGoods}">
  <table class="tg">
    <tr>
      <th width="80">ID</th>
      <th width="120">TITLE</th>
      <th width="120">PRICE</th>
      <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listGoods}" var="good">
      <tr>
        <td>${good.id}</td>
        <td>${good.title}</td>
        <td>${good.price}</td>
        <td><a href="<c:url value='/goodAdmin/remove/${customer.id}'/>">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<c:url var="addAction" value="/goodAdmin/add"/>

<form:form action="${addAction}" modelAttribute="good">
  <table>
    <tr>
      <td>
        <form:label path="title">
          <spring:message text="TITLE"/>
        </form:label>
      </td>
      <td>
        <form:input path="title"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="price">
          <spring:message text="PRICE"/>
        </form:label>
      </td>
      <td>
        <form:input path="price"/>
      </td>
    <tr>
      <td colspan="2">
        <input type="submit"
               value="<spring:message text="Add Good"/>"/>
      </td>
    </tr>
  </table>
</form:form>

</body>
</html>
