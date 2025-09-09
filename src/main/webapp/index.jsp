<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Expense Tracker</title>
</head>
<body>
    <h2>Expense Tracker</h2>

    <form action="expenses" method="post">
        <input type="text" name="expense" placeholder="Enter expense">
        <button type="submit">Add</button>
    </form>

    <h3>Expenses</h3>
    <ul>
        <c:forEach var="e" items="${expenses}">
            <li>${e}</li>
        </c:forEach>
    </ul>
</body>
</html>
