<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="loginForm" action="<c:url value="/homepage" />" method="post">
    Email: <input type="text" placeholder="email" name="email" requird/>
    <br/>
    Password: <input type="password" placeholder="password" name="password" required/>
    <br/>
    Confirm Password: <input type="password" placeholder="password" name="password" required/>
    <input type="submit"  value="log inz">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p><a href="register">Sign up</a></p>
</form>
</body>
</html>
