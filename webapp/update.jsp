<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Текущее Имя: <c:out value="${requestScope.user.name}"/><br/>
    Текущая Фамилия: <c:out value="${requestScope.user.secondName}"/><br/>
    Текущий Возраст: <c:out value="${requestScope.user.age}"/><br/>
    <br/>
<p>Обязательно нужно редактировать все поля</p><br/>
    <form method="post" action="<c:url value='/update'/>">
        <label>Новое имя: <input type="text" name="name2" placeholder="<c:out value="${requestScope.user.name}"/>" /></label><br>
        <label>Новая фамилия: <input type="text" name="second2" placeholder="<c:out value="${requestScope.user.secondName}"/>" /></label><br>
        <label>Новый возраст: <input type="number" name="age2" placeholder="<c:out value="${requestScope.user.age}"/>" /></label><br>

        <input type="text" hidden name="name" value="${user.name}"/>
        <input type="text" hidden name="second" value="${user.secondName}"/>
        <input type="number" hidden name="age" value="${user.age}"/>

        <input type="submit" value="Ok" name="Ok"><br>
    </form>
</body>
</html>
