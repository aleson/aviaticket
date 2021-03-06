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
                    <span><a href="/airadmin"><input type = "button" class="menubut" value = "Перейти"/></a> </span>
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
                      <input type = "button" class="menubut" value = "Обновить"/></a>
                  </form>
                </td></tr>
                <tr><td height="80"></td></tr>
              </table>

            </td>

            <td><font color="black">


              <table width="150" border="0">
                <tr>
                  <td height="360">
                    <table border="0"><tr ><td width="150">
                      <table><td>
                      <%
                        List list4=(List)request.getAttribute("arrayorders");
                        int cx4=0;//counter
                        int c4=0;
                      %>
                      <c:set var="array4" value="<%=list4%>"/>
                      <c:set var="cx4" value="<%=cx4%>"/>
                      <select multiple id="list4" size="10">
                        <c:forEach var="clip" items="${array4}" varStatus="сounter" >
                          <option id="${cx4}"><font color="BLACK">${clip}</font></option>
                        </c:forEach>
                      </select>
                      </td><td>
                      <table border="0" class="block" width="300"><tr><td>
                        <font color="black">
                          Обозначения:<br>
                          <br>
                          1 - номер заказа<br>
                          2 - номер клиента<br>
                          3 - номер рейса<br>
                          (для полей ниже)<br>
                        </font>
                      </td></tr>
                        <tr><td height="77">
                        </td></tr>
                      </table>
                      </td></table>

                    </td>
                    </tr>
                      <tr><td>
                        <font color="black"> Введите номер города:   </font>
                      </td><tr><td>
                        <form action="/adminorder" method="post">
                          <span><input type="text" id="x4" name="x4" class="client" value="" size="10" /></span>
                          <span><input id="edorder" name="edorder" type="submit" class="redact" value="Выбрать"/></span>
                          <span><input id="delorder" name="delorder" type="submit" class="delete" value="Удалить" /></span>
                        </form>

                      </td></tr>
                      <td><font color="black"> Количество заказов: <%=request.getAttribute("list_size4")%> </font>
                      </td></tr>
                      <td>
                        <form action="/adminorder" method="post">
                            <%
                              int z;
                              if(request.getAttribute("ordernum")==null){
                                z=0;
                              }else {
                                z=Integer.parseInt(request.getAttribute("ordernum").toString())-1;
                              }
                            %>

                            <c:set var="c4" value="<%=c4%>"/>
                            <table>
                            <c:forEach var="clip" items="${array4}" begin="<%=z%>" end="<%=z%>" >
                              <c:forEach var="iter" items="${clip}" varStatus="counter">
                                <tr><td><c:set  var="c4" value="${c4+1}"/>
                                <font color="black"><c:out value="${c4}"/></font>
                                  <input type="text" name="=${c4}" value="${iter}" size="20"/>
                              </td></tr>
                              </c:forEach>
                            </c:forEach>
                            </table>

                            <span><input id="order_re" name="order_re" type="submit" class="redact" value="Обновить"/></span>
                            <span><input id="addorder" name="addorder" type="submit" class="delete" value="Добавить" /></span>

                          </form>
                      </td></tr><tr><td height="30"></td></tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td height="100">
                    <font color="black">
                      <p> id Сессии: <%=session.getId()%></p>
                      <p>Время действия сессии: <%= session.getMaxInactiveInterval()/60 %> мин.</p>
                    </font>
                  </td></tr>
              </table>

            </font></td>
            </tr>
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