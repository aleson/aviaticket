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
  <link rel="stylesheet" type="text/css" href="../styles/adminStyle.css">
</head>
<body>

<table align="center" border="0">
  <tr>
    <td>
      <table border="0">
        <td>
          <div name="moduleheader" class="head">
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
                            <h1><%=((session.getAttribute("role")==null)?"": session.getAttribute("role").equals(2) ? "<h1>Admin   </h1>" : "<h1></h1>" )%></h1>
                          </td>
                        </table>

                    </span>
                    <span>
                     </span>

          </div>
        </td><td>
        <div name="moduleex" class="formex">

                    <span>
                        <a href="logout.jsp"><input type="submit" class="exit" value="Выйти"/></a>
                    </span>

        </div>
      </td>
      </table>
    </td>
  </tr>
  <tr>
    <td>


      <div name="modulbody" class="body">
        <form id="modbod" method="POST">

          <table border="0" width="960" height="520">
            <td width="160">
              <table border="0"><tr><td>
                <p align="center"><h3><font color="black">Клиенты:</font></h3></p>
              </td></tr>
                <tr><td>
                  <form id="form" action="/admin" method="POST">
                    <a href="/admin" methods="POST"><input type = "button" class="menubut" value = "Перейти"/></a>
                  </form>
                </td></tr>
                <tr><td>
                  <p align="center"><h3><font color="black">Города:</font></h3></p>
                </td></tr>
                <tr><td>
                  <form  action="/airadmin" method="POST">
                    <span><input type="text" id="1111" class="butafor" value="33"></span>
                    <span><a href="/airadmin"><input type = "button" class="menubut" value = "Обновить"/></a> </span>
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
                  <form  action="/adminorders" method="POST">
                    <a href="/adminorders" methods="POST">
                      <input type = "button" class="menubut" value = "Перейти"/></a>
                  </form>
                </td></tr>
                <tr><td height="120"></td></tr>
              </table>

            </td>

            <td><font color="black">


              <table width="200" border="0">
                <tr>
                  <td height="360">
                    <table border="0"><tr ><td width="200">
                      <%
                        List list2=(List)request.getAttribute("arraycities");
                        int cx2=0;//counter
                        int cl2=0;
                      %>
                      <c:set var="array2" value="<%=list2%>"/><!--Variable for arr<User>-->
                      <c:set var="cx2" value="<%=cx2%>"/>
                      <select multiple id="list2" size="10">
                        <c:forEach var="clip" items="${array2}" varStatus="сounter" >
                          <option id="${cx2}"><font color="BLACK">${clip}</font></option>
                        </c:forEach>
                      </select>
                    </td>
                    </tr>
                      <tr><td>
                        <font color="black"> Введите номер города:   </font>
                      </td><tr><td>
                        <form action="/airadmin" method="post">
                          <span><input type="text" id="x2" name="x2" class="client" value="" size="10" /></span>
                          <span><input id="edсity" name="edсity" type="submit" class="redact" value="Выбрать"/></span>
                          <span><input id="delcity" name="delcity" type="submit" class="delete" value="Удалить" /></span>
                        </form>



                      </td></tr>

                      <td><font color="black"> Количество Городов: <%=request.getAttribute("list_size2")%> </font>

                      </td></tr>
                      <td>
                        <form action="/airadmin" method="post">
                          <table>
                            <%
                              int j;
                              if(request.getAttribute("citynum")==null){
                                j=0;
                              }else {
                                j=Integer.parseInt(request.getAttribute("citynum").toString())-1;
                              }
                            %>
                            <c:set var="cl2" value="<%=cl2%>"/>
                            <c:forEach var="clip" items="${array2}" begin="<%=j%>" end="<%=j%>" >
                              <c:forEach var="iter" items="${clip}" begin="0" end="5">
                                <c:set  var="cl2" value="${cl2+1}"/>
                                <input type="text" name="_${cl2}" value="${iter}" size="20"/>
                              </c:forEach>
                            </c:forEach>
                            </td><td>

                            <span><input id="city_re" name="re" type="submit" class="redact" value="Изменить"/></span>
                            <span><input id="addcity" name="addcity" type="submit" class="delete" value="Добавить" /></span>

                          </td></table> </form>
                      </td></tr><tr><td height="30"></td></tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td height="180">
                    <font color="black">
                      <p> id Сессии: <%=session.getId()%></p>
                      <p>Время действия сессии: <%= session.getMaxInactiveInterval()/60 %> мин.</p>
                    </font>
                </td></tr>
              </table>

            </font></td>
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
