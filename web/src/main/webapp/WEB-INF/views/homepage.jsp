<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>Index</title>
	<link href="https://fonts.googleapis.com/css?family=Kumar+One" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/catalogue.css"/>"/>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="homepage">Square Eyes</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="homepage">Home</a></li>
                    <li><a href="catalogue">Catalogue</a></li>
					<%--<li><a href="login">login</a></li>--%>

				</ul>
				<form class="navbar-form navbar-right" action="/catalogue">
					<input type="text" class="form-control" id="search" placeholder="Search" name="search"></input>
					<button type="button" class="btn btn-primary" value="search"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
					<button type="button" onclick="hideOrShowShoppingCart()" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Shopping Cart</button>
				</form>
			</div><!--/.nav-collapse -->
		</div>
	</nav>
	<aside id="sidebar" class="hide">

		<h1>Sidebar</h1>
		<ul>
			<li>Item 1</li>
			<li>Item 2</li>
			<li>Item 3</li>
		</ul>
	</aside>
	<section id="content" class="expand">
		<article>
			<div class="container">
				<div id="shoppingCart" class="hide" style="position: fixed; z-index: 2;">
					<!-- <h3 class="col-xs-12 col-md-offset-3 col-md-6">Shopping Cart:</h3> -->
					<div id="shoppingItems" class="well container">
						loading..
					</div>
				</div>
			</div>
		</article>
		<article>
			<div class="centerText">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Wrapper for slides -->
					<h1 style="padding-top: 20px;">Our daily deals!</h1>
					<div class="carousel-inner" role="listbox">
						<div class="item active">
                            <c:forEach items="${list}" var="issue">
                                <div class="item">
                                <h1 class="title":> ${issue.getIssueTitle()}</h1>
                                <p class="price":>SALE</p>
                                <h2 class="description">${issue.getDescription()}</h2>
                                <img class="item-image" src="SlideShow/c1.jpg" alt="Batman" width="100px" height="100px">

                                <p><a class="btn btn-success btn-lg" href="Catalogue" role="button">Learn more &raquo;</a></p>
                                </div>
                            </c:forEach>

						</div>
<%--
						<div class="item">
							<h3 class="title">Captain America</h3>
							<img class="item-image" src="SlideShow/c2.jpg" alt="Captain America" width="100px" height="100px">
							<p class="price"> R99.99</p>
							<p><a class="btn btn-success btn-lg" href="Catalogue" role="button">Learn more &raquo;</a></p>
						</div>

						<div class="item">
							<h3 class="title">X-Men</h3>
							<img class="item-image" src="SlideShow/c4.png" alt="X-Men" width="100px" height="100px">
							<p class="price"> R99.99</p>
							<p><a class="btn btn-success btn-lg" href="Catalogue" role="button">Learn more &raquo;</a></p>
						</div>

						<div class="item">
							<h3 class="title">Wonder Woman</h3>
							<img class="item-image" src="SlideShow/comic5.jpg" alt="Wonder Woman" width="100px" height="100px">
							<p class="price"> R99.99</p>
							<p><a class="btn btn-success btn-lg" href="Catalogue" role="button">Learn more &raquo;</a></p>
						</div>--%>
					</div>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
				<br/>

			</div>
		</article>
		<article>
			<div class="centerText">
				<form action="/logout" method="post">
					<input type="submit" value="Log out"/>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
				${message}


				<c:url value="/logout" var="logoutUrl" />
				<a href="${logoutUrl}">Log Out</a>

			</div>
		</article>

	</section>
	<footer class="footer-middle">
		<!-- <p>&copy; 2017 Team Two Face</p> -->
	</footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script type="text/javascript" src="Issues.json"></script>
	<script type="text/javascript" src="global.js"></script>
	<script type="text/javascript" src="homepage.js"></script>
</body>
</html>
