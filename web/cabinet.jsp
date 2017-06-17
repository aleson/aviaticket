<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("sessionIdx")==null) {
        response.sendRedirect("index.jsp");
    }
    if(session.getAttribute("role")!=null){
        if(!session.getAttribute("role").equals(1)) response.sendRedirect("index.jsp");
    }

%>


<html>
<head>
    <title>Private Cabinet</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles/cabinetStyle.css">
</head>
<body>

<table align="center" border="0">
    <tr>
        <td>
            <table border="0">
                <td>
                <form name="moduleheader" id="modhead" class="head" action="logout.jsp" method="POST">
                    <%
                        String cuname=session.getAttribute("name").toString();//get name user
                        float cumoney=Float.parseFloat(session.getAttribute("money").toString());//get balance-money user

                    %>
                    <c:set var="cuname" value="<%=cuname%>"/>
                    <c:set var="cumoney" value="<%=cumoney%>"/>
                    <input type="text" id="cuname" name="cuname" class="butafor" value="${cuname}" size="0" />
                    <input type="text" id="cumoney" name="cumoney" class="butafor" value="${cumoney}" size="0" />

                    <span>
                        <table border="0" width="1020">
                            <td width="200">
                                <h3><%=(request.getAttribute("xcuname")!=null)?request.getAttribute("xcuname"):cuname%></h3>
                            </td>
                            <td width="200">
                                <h3>Balance: <%=(request.getAttribute("xcumoney")!=null)?request.getAttribute("xcumoney"):cumoney%> RUR</h3>
                            </td>
                            <td width="300"></td>
                            <td width="60" align="center">
                                <input type="submit" class="exit" value="Выйти" />
                            </td>
                        </table>

                    </span>
                    <span>

                    </span>
                </form>
                </td><td>
                </td>
            </table>
        </td>
    </tr>
    <tr>
        <td>
        <form name="modulbody" id="modbod" class="body">
            <span></span>
            <table border="0" width="960" height="520">
                    <td width="160">
                        <table border="0"><tr><td>
                            <p align="center"><h3><font color="black">Меню:</font></h3></p>
                        </td></tr>
                            <tr><td>
                                <form action="/cabinet" method="Post">
                                    <span><input type="text" id="ccna" name="ccna" class="butafor" value="33" size="0" /></span>
                                   <span><input type = "button" class="menubut" value = "Не работает"/></span>
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
                                <form  action="/adminorders" method="POST">
                                    <a href="/adminorders" methods="POST">
                                        <input type = "button" class="menubut" value = "Перейти"/></a>
                                </form>
                            </td></tr>
                            <tr><td height="145"></td></tr>
                        </table>

                    </td>
                <td width="800">
                    <table width="800" border="1">
                        <tr>
                            <td height="360">

                                <table border="1">
                                    <tr><td>
                                        <table><td>
                                        <font color="black">Город вылета</font>
                                    </td><td width="30">

                                    </td><td>
                                        <font color="black">Город прилёта</font>
                                    </td></table>
                                    </td></tr>
                                    <form action="/cabinet" method="post"><tr><td>
                                        <%
                                            int cuid=Integer.parseInt(session.getAttribute("id_user").toString());//get id user
                                        %>
                                        <c:set var="cuid" value="<%=cuid%>"/>
                                        <input type="text" id="cuid" name="cuid" class="butafor" value="${cuid}" size="0" />

                                        <table height="30"><td>
                                                <span><input type="text" id="depart" name="depart" class="client" value="" size="10" /></span>

                                            </td><td>
                                                <font color="black">=></font>
                                            </td><td>
                                                <span><input type="text" id="arrive" name="arrive" class="client" value="" size="10" /></span>
                                            </td></table>
                                          </td>
                                          <td>
                                            <span>
                                                <input id="create_order" name="create_order" type="submit" class="redact" value="заказать"/>
                                            </span>
                                    </td></tr></form>
                                    <tr><td>

                                        <table><td>

                                            <%

                                                List listcities =(List)request.getAttribute("cabarraycities");
                                                int cabcx=0;//counter
                                                int cabcx2=0;

                                                int cabcr=0;
                                            %>
                                             <c:set var="cabarray" value="<%=listcities%>"/>
                                             <c:set var="cabarray2" value="<%=listcities%>"/>
                                            <c:set var="cabcx" value="<%=cabcx%>"/>
                                           <select id="list1" multiple size="10">
                                                <c:forEach var="clip" items="${cabarray}" varStatus="сounter" >
                                                     <option value="${cabcx}"><font color="BLACK">${clip}</font></option>
                                                    </c:forEach>
                                            </select>
                                            </td><td width="30">
                                            </td><td>
                                            <select id="list2" multiple size="10">
                                                <c:forEach var="clip2" items="${cabarray2}" varStatus="сounter" >
                                                    <option value="${cabcx}-"><font color="BLACK">${clip2}</font></option>
                                                </c:forEach>
                                            </select>
                                            <c:set var="cabarray" value="null"/>
                                            <c:set var="cabarray2" value="null"/>
                                            <c:set var="cabx" value="0"/>

                                        </td></table>
                                    </td></tr>
                                    <tr><td><h3><font color="black">
                                        <%
                                            String string=null;
                                            if(request.getAttribute("caberror")!=null){

                                                if(request.getAttribute("caberror").equals(false)) string="Заказ оформлен";
                                                else string="Не удалось оформить заказ";
                                            }
                                        %>
                                        <%=(string!=null)?string:""%>
                                    </font></h3></td></tr>
                                </table>

                            </td>
                        </tr>
                        <tr>
                            <td height="180">
                            </td>

                        </tr>
                    </table>
                </td>
            </table>
        </form>
        </td>
    </tr>
    <tr>
        <td>
            <form name="modulbot"  class="bottom">
                <span> </span>
                <p align="center"> Aviacomapny site 2017</p>
            </form>
        </td>
    </tr>
</table>



</body>
</html>
