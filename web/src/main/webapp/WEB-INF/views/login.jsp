<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/catalogue.css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

</head>
<body>
<%

    String errorString = (String)request.getAttribute("error");
    if(errorString != null && errorString.trim().equals("true")){
        out.println("<span class=\"dark\">Incorrect login name or password. Please try again");
    }
%>
<section>
    <div class="container" style="padding-top:10px">
        <div class="col-md-6 col-xs-12">
            <form name="loginForm" action="<c:url value="/login" />" method="post">
                Email: <input type="text" placeholder="email" name="user" requird/>
                <br/>
                Password: <input type="password" placeholder="password" name="password" required/>
                <br/>
                <input type="submit"  value="log in">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <div class="col-md-6 col-xs-12">
            <form name="signUpForm" action="<c:url value="/signup" />" method="post">
                Email: <input type="text" placeholder="email" name="email" requird/>
                <br/>
                Password: <input type="password" placeholder="password" name="password" required/>
                <br/>
                Title: <input type="password" placeholder="password" name="title" required/>
                <br/>
                FirstName: <input type="password" placeholder="password" name="firstName" required/>
                <br/>
                Surname: <input type="password" placeholder="password" name="surname" required/>
                <br/>
                <input type="submit"  value="SignUp">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</section>
<footer class="footer-middle">
    <p>2017 - &copy;Entelect - Team Two Face</p>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="<c:url value="/assets/js/global.js"/>"></script>
<script src="<c:url value="/assets/js/product.js"/>"></script>
</body>
</html>
