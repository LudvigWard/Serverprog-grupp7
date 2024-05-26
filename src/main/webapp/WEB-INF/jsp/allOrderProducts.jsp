<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Order Products</title>
</head>
<body>
<h1>Order Products</h1>

<table style="border: 1px solid black; border-collapse: collapse;">
    <tr>
        <th style="border: 1px solid black; padding-right: 1em;">Order Product ID</th>
        <th style="border: 1px solid black; padding-right: 1em;">Order ID</th>
        <th style="border: 1px solid black; padding-right: 1em;">Product</th>
    </tr>
    <c:forEach items="${orderProducts}" var="orderProduct">
        <tr>
            <td style="border: 1px solid black; padding-right: 1em;">${orderProduct.orderProductId}</td>
            <td style="border: 1px solid black; padding-right: 1em;">${orderProduct.customerOrder.orderId}</td>
            <td style="border: 1px solid black; padding-right: 1em;">${orderProduct.product.productName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>