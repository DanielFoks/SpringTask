<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <title>OrderAdmin</title>
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>

<h1>Good List</h1>
<c:if test="${!empty listOrders}">
  <table class="tg">
    <tr>
      <th width="80">ID</th>
      <th width="120">CUSTOMER</th>
      <th width="120">GOODS</th>
      <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listOrders}" var="order">
      <tr>
        <td>${order.id}</td>
        <td>${order.customer.fio}</td>
        <td>
        <table>
          <c:if test="${!empty order.goods}">
          <c:forEach items="${order.goods}" var="good">
            <tr>${good.title} ( ${good.price} $ )</tr>
          </c:forEach>
          </c:if>
          </table>
        </td>
        <td><a href="<c:url value='/goodAdmin/remove/${customer.id}'/>">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>