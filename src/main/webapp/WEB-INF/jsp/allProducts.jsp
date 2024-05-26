<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>Products</h1>

<table style="border: 1px solid black; border-collapse: collapse;">
    <tr>
        <th style="border: 1px solid black; padding-right: 1em;">Product Name</th>
        <th style="border: 1px solid black; padding-right: 1em;">Price</th>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td style="border: 1px solid black; padding-right: 1em;">${product.productName}</td>
            <td style="border: 1px solid black; padding-right: 1em;">${product.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>