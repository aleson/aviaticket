<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("name");
    session.removeAttribute("money");
    session.removeAttribute("sessionIdx");
    session.removeAttribute("role");
    session.removeAttribute("current_count");
    session.invalidate();
%>
<jsp:forward page="index.jsp"/>
<html>
<head>
    <title>Close session</title>
</head>
<body>
 Wait
</body>
</html>