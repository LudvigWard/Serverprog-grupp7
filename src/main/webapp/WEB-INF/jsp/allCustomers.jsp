<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Customers</title>
</head>
<body>
<h1>Customers</h1>

<table style="border: 1px solid black; border-collapse: collapse;">
    <tr>
        <th style="border: 1px solid black; padding-right: 1em;">Customer ID</th>
        <th style="border: 1px solid black; padding-right: 1em;">Name</th>
        <th style="border: 1px solid black; padding-right: 1em;">Email Address</th>
        <th style="border: 1px solid black; padding-right: 1em;">Mobile Number</th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td style="border: 1px solid black; padding-right: 1em;">${customer.customerId}</td>
            <td style="border: 1px solid black; padding-right: 1em;">${customer.customerName}</td>
            <td style="border: 1px solid black; padding-right: 1em;">${customer.email}</td>
            <td style="border: 1px solid black; padding-right: 1em;">${customer.mobileNum}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>