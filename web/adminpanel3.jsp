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
                    <span><input type="text" id="___" class="butafor" value="33"></span>
                    <span><a href="/airadmin"><input type = "button" class="menubut" value = "Перейти"/></a> </span>
                  </form>
                </td></tr>
                <tr><td>
                  <p align="center"><h3><font color="black">Рейсы:</font></h3></p>
                </td></tr>
                <tr><td>
                  <form  method="POST">
                    <a href="/airflights" methods="POST"><input type = "button" class="menubut" value = "Обновить"/></a>
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
                <tr><td height="105"></td></tr>
              </table>

            </td>

            <td><font color="black">


              <table width="200" border="0">
                <tr>
                  <td height="360">
                    <table border="0"><tr ><td width="200">
                      <%
                        List list3=(List)request.getAttribute("arrayflights");
                        int cx3=0;//counter
                        int c3=0;
                      %>
                      <c:set var="array3" value="<%=list3%>"/>
                      <c:set var="cx3" value="<%=cx3%>"/>
                      <select multiple id="list3" size="10">
                        <c:forEach var="clip" items="${array3}" varStatus="сounter" >
                          <option id="${cx3}"><font color="BLACK">${clip}</font></option>
                        </c:forEach>
                      </select>
                    </td>
                    </tr>
                      <tr><td>
                        <font color="black"> Введите номер рейса:   </font>
                      </td><tr><td>
                        <form action="/airflights" method="post">
                          <span><input type="text" id="x3" name="x3" class="client" value="" size="10" /></span>
                          <span><input id="edflight" name="edflight" type="submit" class="redact" value="Выбрать"/></span>
                          <span><input id="delflight" name="delflight" type="submit" class="delete" value="Удалить" /></span>
                        </form>



                      </td></tr>

                      <td><font color="black"> Количество Рейсов: <%=request.getAttribute("list_size3")%> </font>

                      </td></tr>
                      <td>
                        <form action="/airflights" method="post">
                          <table>
                            <%
                              int k;
                              if(request.getAttribute("flightnum")==null){
                                k=0;
                              }else {
                                k=Integer.parseInt(request.getAttribute("flightnum").toString())-1;
                              }
                            %>
                            <c:set var="c3" value="<%=c3%>"/>
                            <c:forEach var="clip" items="${array3}" begin="<%=k%>" end="<%=k%>" >
                              <c:forEach var="iter" items="${clip}" varStatus="counter">
                                <c:set  var="c3" value="${c3+1}"/>
                                <tr><td>
                                  <input type="text" name="+${c3}" value="${iter}" size="50"/>
                                </td></tr>
                              </c:forEach>
                            </c:forEach>
                            </td><td>

                            <span><input id="flight_re" name="flight_re" type="submit" class="redact" value="Обновить"/></span>
                            <span><input id="addflight" name="addflight" type="submit" class="delete" value="Добавить" /></span>

                          </td></table> </form>
                      </td></tr><tr></tr>
                    </table>
                  </td>
                </tr>
                <tr>

              </table>

            </font></td>
            <td>
              <table border="0">
                <tr><td>
                  <font color="black">Номера соответстуют городам:</font>
                </td></tr>
                <tr><td>
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
                </td></tr>
                <tr><td >
                  <table border="1" class="block"><tr><td>
                  <font color="black">
                    Обозначения:<br>
                    <br>
                    1 - номер рейса<br>
                    2 - название рейса<br>
                    3 - номер города отправления<br>
                    4 - номер города прибытия<br>
                    5 - стоимость рейса(RUR)<br>
                    6 - Дата вылета<br>
                        Внимание!<br>
                        При добавлении рейса дату указывать<br>
                        в формате dd-mm-yy<br>
                    7 - Час вылета (0-23)<br>
                    8 - Минуты вылета(0-59)<br>
                    9 - Длительность перелеёта(мин.)<br>
                  </font>
                  </td></tr></table>
                </td></tr>
                <tr><td >
                  <font color="black">
                    <p><h3>Время действия сессии: <%= session.getMaxInactiveInterval()/60 %> мин.</h3></p>
                  </font>
                </td></tr>
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
