<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

    if (session.getAttribute("sessionIdx")==null) {
        response.sendRedirect("index.jsp");
    }
    if(session.getAttribute("role")!=null){
        if(!session.getAttribute("role").equals(2)) response.sendRedirect("index.jsp");
    }

%>


<html>
<head>
    <title>Admin panel</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../styles/adminpanelStyle.css">
</head>
<body>

<table align="center" border="0">
    <tr>
        <td>
            <table border="0">
                <td>
                    <div name="moduleheader" class="head">
                    <span>
                        <table border="0" width="1000">
                            <td width="200">
                                <h3><%=session.getAttribute("name")%></h3>
                            </td>
                            <td width="700">
                                <h3>Balance: <%=session.getAttribute("money")%> RUR</h3>
                            </td>
                            <td>
                                <h1><%=((session.getAttribute("role")==null)?"": session.getAttribute("role").equals(2) ? "<h1>Admin   </h1>" : "<h1></h1>" )%></h1>
                            </td>
                            <td width="100"></td>
                            <td width="60" align="center">
                                <a href="logout.jsp"><input type="submit" class="exit" value="Выйти" /></td></a>
                            </td>
                        </table>


                    </span>
                    <span>
                     </span>

                    </div>
                    </td><td>
            </td>
            </table>
        </td>
    </tr>
    <tr>
        <td>


            <div name="modulbody" class="body">
            <form id="modbod" action="/admin" method="POST">

               <table border="0" width="460" height="520">
                    <td width="110">
                        <table border="0"><tr><td>
                                <p align="center"><h3><font color="black">Клиенты:</font></h3></p>
                            </td></tr>
                            <tr><td>
                                <form id="form" action="/admin" method="POST">
                                    <a href="/admin" methods="POST"><input type = "button" class="menubut" value = "Обновить"/></a>
                                </form>
                            </td></tr>
                            <tr><td>
                                <p align="center"><h3><font color="black">Города:</font></h3></p>
                            </td></tr>
                            <tr><td>
                                <form  action="/airadmin" method="POST">
                                    <a href="/airadmin" methods="POST"><input type = "button" class="menubut" value = "Перейти"/></a>
                                </form>
                            </td></tr>
                            <tr><td>
                                <p align="center"><h3><font color="black">Рейсы:</font></h3></p>
                            </td></tr>
                            <tr><td>
                                <form  action="/airflights" method="POST">
                                    <a href="/airflights" methods="POST"><input type = "button" class="menubut" value = "Перейти"/></a>
                                </form>
                            </td></tr>
                            <tr><td>
                                <p align="center"><h3><font color="black">Заказы:</font></h3></p>
                            </td></tr>
                            <tr><td>
                                <form  action="/adminorder" method="POST">
                                    <a href="/adminorder" methods="POST">
                                        <input type = "button" class="menubut" value = "Перейти"/></a>
                                </form>
                            </td></tr>
                            <tr><td height="145"></td></tr>
                        </table>
                    </td>
                    <td width="300">
                        <table  border="0" width="300">
                            <tr>
                                <td height="360">
                                    <table border="0"><tr ><td width="200">
                                    <%
                                        List list=(List)request.getAttribute("array");
                                        int cx=0;//counter
                                        int cl=0;
                                    %>
                                        <c:set var="array" value="<%=list%>"/><!--Variable for arr<User>-->
                                        <c:set var="cx" value="<%=cx%>"/>
                                        <select multiple id="list" size="10">
                                            <c:forEach var="clip" items="${array}" varStatus="сounter" >
                                                <option id="${cx}"><font color="BLACK">${clip}</font></option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    </tr>
                                        <tr><td>
                                            <font color="black"> Введите номер клиента:   </font>

                                        </td><tr><td>
                                            <form action="/admin" method="post">
                                                <span><input type="text" id="x" name="x" class="client" value="" size="10" /></span>
                                                <span><input id="del" name="del" type="submit" class="delete" value="Удалить" /></span>
                                                <span><input id="edi" name="edi" type="submit" class="redact" value="Выбрать"/></span>
                                            </form>



                                        </td></tr>

                                        <td><font color="black"> Количество клиентов: <%=request.getAttribute("list_size")%> </font>

                                        </td></tr>
                                        <td>
                                            <form action="/admin" method="post">
                                            <table>
                                            <%
                                                int i;
                                            if(request.getAttribute("ednum")==null){
                                                i=0;
                                            }else {
                                                i=Integer.parseInt(request.getAttribute("ednum").toString())-1;
                                            }
                                            %>
                                            <c:set var="cl" value="<%=cl%>"/>
                                            <c:forEach var="clip" items="${array}" begin="<%=i%>" end="<%=i%>" >
                                                <c:forEach var="iter" items="${clip}" begin="0" end="5">
                                                    <c:set  var="cl" value="${cl+1}"/>
                                                        <input type="text" name="${cl}" value="${iter}" size="20"/>
                                                </c:forEach>
                                            </c:forEach>
                                            </td><td>

                                                    <span><input id="re" name="re" type="submit" class="redact" value="Изменить"/></span>

                                            </td></table> </form>
                                        </td></tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="140">
                                    <font color="black">
                                        <p> id Сессии: <%=session.getId()%></p>
                                        <p>Время действия сессии: <%= session.getMaxInactiveInterval()/60 %> мин.</p>
                                    </font>
                                </td>
                            </tr>
                            <tr>

                            </tr>
                        </table>
                    </td>
                    <td><table><tr><td>
                        <table border="1" class="block" width="300"><tr><td>
                            <font color="black">
                                Обозначения:<br>
                                <br>
                                1 - номер рейса<br>
                                2 - Имя пользователя<br>
                                3 - баланс пользователя(RUR)<br>
                                4 - логин<br>
                                5 - пароль<br>
                                6 - номер группы<br>
                            </font>
                        </td></tr>
                        </table>
                        </td></tr>
                        <tr><td height="422"></td></tr>
                    </table>
                    </td>
                </table>

            </form>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div name="modulbot"  class="bottom">
                <span><p align="center"> Aviacomapny site 2017</p></span>
            </div>
        </td>
    </tr>
</table>



</body>
</html>
