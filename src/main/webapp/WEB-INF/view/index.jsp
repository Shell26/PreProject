<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title jsp</title>
</head>
<body>
<h1>Вход в систему</h1><br>
<form method="post" action="">
    <input type="text" required placeholder="login" name="authLogin"><br>
    <input type="password" required placeholder="password" name="authPass"><br><br>
    <input class="button" type="submit" value="Войти">
</form>
</body>
</html>
