<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>List Users</h1>
    <c:forEach var="user" items="${requestScope.users}">
        <ul>
            <li> Имя: <c:out value="${user.name}"/></li>
            <li> Пароль: <c:out value="${user.password}"/></li>
            <li> Возраст: <c:out value="${user.age}"/></li>
            <li> Права: <c:out value="${user.role}"/></li>
            <form method="post" action="<c:url value="/delete"/>">
                <input type="number" hidden name="id" value="${user.id}"/>
                <input type="submit" name="delete" value="Удалить"/>
            </form>
            <form method="get" action="<c:url value="/update"/>">
                <input type="number" hidden name="id" value="${user.id}"/>
                <input type="submit" name="update" value="Редактировать"/>
            </form>
        </ul>
    </c:forEach>

    <h1>Add User</h1>
    <form method="post">
        Имя <input type="text" name="name"><br/>
        Пароль <input type="password" name="password"><br/>
        Возраст <input type="number" name="age"><br/>
        <button type="submit">Add</button>
    </form>

</body>
</html>
