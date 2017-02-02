<%--
  Created by IntelliJ IDEA.
  User: mpho.mahase
  Date: 2017/02/01
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    String errorString = (String)request.getAttribute("error");
    if(errorString != null && errorString.trim().equals("true")){
        out.println("<span class=\"dark\">Incorrect login name or password. Please try again");
    }
%>

<form name="loginForm" action="<c:url value="/login" />" method="post">
    Emailz: <input type="text" placeholder="email" name="user" requird/>
    <br/>
    Passwordz: <input type="password" placeholder="password" name="user" required/>
    <br/>
    <input type="submit"  value="log inz">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><a href="register">Sign up</a></p>
</form>
</body>
</html>
