
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    System.out.println(session.getAttribute("sessionIdx"));
    if (session.getAttribute("sessionIdx")==null){
    response.sendRedirect("index.jsp");
}
%>

<html>
<head>
    <title>Private Cabinet</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../styles/cabinetStyle.css">
</head>
<body>

<table align="center" border="0">
    <tr>
        <td>
            <table border="0">
                <td>
                <form name="moduleheader" id="modhead" class="head">
                    <span>
                        <table border="0" width="900">
                            <td width="200">
                                <h3><%=session.getAttribute("name")%></h3>
                            </td>
                            <td width="200">
                                <h3>Balance: <%=session.getAttribute("money")%> RUR</h3>
                            </td>
                            <td width="300"></td>
                            <td width="60" align="center">
                                <h1></h1>
                            </td>
                        </table>

                    </span>
                    <span>
                     </span>
                </form>
                </td><td>
                <form name="moduleex" id="modex" class="formex" action="logout.jsp" method="POST">
                    <span><input type="submit" class="exit" value="Выйти" />

                    </span>
                </form>
                </td>
            </table>
        </td>
    </tr>
    <tr>
        <td>
        <form name="modulbody" id="modbod" class="body">
            <span></span>
            <table border="1" width="960" height="520">
                    <td width="160">
                       <table border="1"><tr><td>
                        <p align="center"><h3><font color="black">Меню:</font></h3></p>
                       </td></tr>
                           <tr><td>

                           </td></tr>
                           <tr><td>

                           </td></tr>
                       </table>

                    </td>
                <td width="800">
                    <table width="800" border="1">
                        <tr>
                            <td height="360">
                                <font color="black"> Функционал </font>
                            </td>
                        </tr>
                        <tr>
                            <td height="180">
                                <font color="black">
                                    <p>Сессия: '<%=session.getId()%>'</p>
                                    <p>Время действия сессии: <%= session.getMaxInactiveInterval() %> секунд</p>
                                </font>
                            </td>

                        </tr>
                    </table>
                Функционал

                </td>
            </table>
        </form>
        </td>
    </tr>
    <tr>
        <td>
        <form name="modulbot" id="modbot" class="bottom">
            <span>Footer</span>
        </form>
        </td>
    </tr>
</table>



</body>
</html>
