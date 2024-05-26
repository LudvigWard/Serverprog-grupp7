<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
   <title>Customer Orders</title>
</head>
<body>
<h1>Customer Orders</h1>

<table style="border: 1px solid black; border-collapse: collapse;">
   <tr>
      <th style="border: 1px solid black; padding-right: 1em;">Order ID</th>
      <th style="border: 1px solid black; padding-right: 1em;">Customer Name</th>
      <th style="border: 1px solid black; padding-right: 1em;">Customer ID</th>
   </tr>
   <c:forEach items="${customerOrders}" var="customerOrder">
      <tr>
         <td style="border: 1px solid black; padding-right: 1em;">${customerOrder.orderId}</td>
         <td style="border: 1px solid black; padding-right: 1em;">${customerOrder.customer.customerName}</td>
         <td style="border: 1px solid black; padding-right: 1em;">${customerOrder.customer.customerId}</td>
      </tr>
   </c:forEach>
</table>
</body>
</html>