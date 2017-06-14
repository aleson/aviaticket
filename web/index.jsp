
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Вход в сервис по заказу билетов</title>
  <link rel="stylesheet" type="text/css" href="styles/indexStyle.css">

</head>

<body>
<div  border="1">
  <form name="form" action="/index"  method="POST">
    <span><h3><p align="center" class="text">Вход в личный кабинет</p></h3></span>
    <span><font class="textName">Логин:   </font></span><span> <input type="text" id="username" name="username" class="user" value="" size="20" /></span>
    <span><font class="textPass">Пароль:</font></span><span> <input type="password" id="password" name="password" class="pas" value="" size="20" /></span>
    </br>
    <span><input type="submit" class="entery" value="Войти"/> <span><a href="registration.jsp">Регистрация</a></span>
    </span>
      <span><h3><p align="center" class="status"><%=(request.getAttribute("isError")==null ? "": (request.getAttribute("isError").equals(true) ? "Неверный логин или пароль": ""))%></p></h3></span>
    <span><h3> <p align="center" class="status"><%=(request.getAttribute("isError2")==null ? "": (request.getAttribute("isError2").equals(true) ? "": "Регистрация успешно завершена"))%></p></h3></span>
  </form>
</div>
<div class="Bottom"><p align="center">Site was created in 2017. Leonov A.S.</p></div>
</body>
</html>

