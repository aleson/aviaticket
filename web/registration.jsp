<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Регистрация</title>
  <meta id="cod" http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="styles/registrationStyle.css">

</head>
<body>
<div class="registry" border="1">
  <form name="registry" action="/registration"  method="POST">
      <br><br>
    <table align="center" border="0">
        <tr><td>
            <h1><p align="center">Регистрация</p></h1>
        </td></tr>
        <tr><td>
    <span><!-- Name-->
         <font class="textName">Ваше имя:     </font></span><span> <input type="text" id="nameR" name="nameR" class="name" value="" size="20" /></span>
      </span>
       </td></tr>
        <tr><td>
      <span><!-- Login-->
          <font class="textName">Логин:       </font></span><span> <input type="text" id="loginR" name="loginR" class="login" value="" size="20" /></span>
      </span>
        </td></tr>
        <tr><td>
      <span><!-- Pass-->
          <font class="textPass">Пароль:       </font></span><span> <input type="password" id="passwordR" name="passwordR" class="pas" value="" size="20" /></span>
      </span>
        </td></tr>
        <tr><td>
      <span><!-- Money-->
          <font class="textName">Ваш баланс:   </font></span><span> <input type="text" id="moneyR" name="moneyR" class="user" value="" size="20" /> RUR</span>

      </span>
        </td></tr>
        <tr><td >
            <table border="0" width="300"><td>
            <span><!-- button - Registration -->
                <input align="left" type="submit" class="entery" value="Регистрация" />
            </span>
            </td><td align="right">
            <span><a href="index.jsp">Отмена</a></span>
            </td></table>
            </td>
        </tr><tr>
        <td>
            <p align="center"><%=(request.getAttribute("isError2")==null ? "": (request.getAttribute("isError2").equals(true) ? "Неверно введены данные": "Регистрация завершена"))%></p>
        </td>
    </tr>
    </table>
  </form>
</div>
</body>
</html>
