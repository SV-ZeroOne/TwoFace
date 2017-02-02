<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Login/Signup</title>
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
    <article>
    <div class="container" style="padding-top:10px">
        <div class="well col-xs-12 col-md-5">
            <h3>Log in:</h3>
            <form name="loginForm" action="<c:url value="/login" />" method="post">
                <div class="form-group">
                    <label for="user">Email address:</label>
                    <input class="form-control" type="text" placeholder="email" id="user" name="user" requird/>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input class="form-control" type="password" placeholder="password" id="password" name="password" required/>
                </div>
                <input type="submit" class="btn btn-default" value="log in">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <div class="well col-xs-12 col-md-offset-2 col-md-5">
            <h3>Sign Up:</h3>
            <form name="signUpForm" action="<c:url value="/signup" />" method="post">
                <div class="form-group">
                    <label for="regemail">Email address:</label>
                    <input class="form-control" type="text" placeholder="email" name="email" id="regemail" requird/>
                </div>
                <div class="form-group">
                    <label for="regpassword">Password:</label>
                    <input class="form-control" type="password" placeholder="password" name="password" id="regpassword" required/>
                </div>
                <div class="form-group">
                    <label for="regtitle">Title:</label>
                    <input class="form-control" type="password" placeholder="title" name="title" id="regtitle" required/>
                </div>
                <div class="form-group">
                    <label for="regfirstName">First Name:</label>
                    <input class="form-control" type="password" placeholder="first-name" name="firstName" id="regfirstName" required/>
                </div>
                <div class="form-group">
                    <label for="regsurname">Surname:</label>
                    <input class="form-control" type="password" placeholder="surname" name="surname" id="regsurname" required/>
                </div>
                <input type="submit" class="btn btn-default" value="SignUp">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
    </article>
</section>
<footer class="footer-middle" style="">
    <p>2017 - &copy;Entelect - Team Two Face</p>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="<c:url value="/assets/js/global.js"/>"></script>
<script src="<c:url value="/assets/js/product.js"/>"></script>
</body>
</html>
