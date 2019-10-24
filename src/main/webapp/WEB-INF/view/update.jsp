<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Текущее Имя: <c:out value="${requestScope.user.name}"/><br/>
    Текущий Пароль: <c:out value="${requestScope.user.password}"/><br/>
    Текущий Возраст: <c:out value="${requestScope.user.age}"/><br/>
    <br/>
<p>Обязательно нужно редактировать все поля</p><br/>
    <form method="post" action="<c:url value='/admin/update'/>">
        <label>Новое имя: <input type="text" name="name2" placeholder="<c:out value="${requestScope.user.name}"/>" /></label><br>
        <label>Новый пароль: <input type="password" name="password2" placeholder="<c:out value="${requestScope.user.password}"/>" /></label><br>
        <label>Новый возраст: <input type="number" name="age2" placeholder="<c:out value="${requestScope.user.age}"/>" /></label><br>

        <input type="number" hidden name="id" value="${user.id}"/>

        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</body>
</html>
