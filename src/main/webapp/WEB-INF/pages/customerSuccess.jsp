<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<c:url var="addAction" value="/customerSuccess/newOrder"/>

<form:form action="${addAction}" commandName="customerSuccess" modelAttribute="order">
    <table class="tg">
        <tr>
            <td>Goods for order :</td>
            <td><form:checkboxes items="${listGoods}" path="goodsString" itemValue="id"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Make an order"/></td>
        </tr>
    </table>
    </form:form>

</body>
</html>
