<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  if(session.getAttribute("sessionIdx")!=null){
    if(session.getAttribute("role").equals(2)) response.sendRedirect("/admin");
    else response.sendRedirect("cabinet.jsp");
  }
  else{
    response.sendRedirect("index.jsp");
  }
%>
<html>
<head>
    <title></title>
</head>
<body>
</body>
</html>
