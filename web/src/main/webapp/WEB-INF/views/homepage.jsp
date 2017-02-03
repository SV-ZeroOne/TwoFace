<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>Index</title>
	<%--<link href="https://fonts.googleapis.com/css?family=Kumar+One" rel="stylesheet">--%>
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
			<a class="navbar-brand" href="/homepage"><strong>Square Eyes</strong></a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/homepage">Home</a></li>
				<li><a href="/catalogue">Catalogue</a></li>
			</ul>
			<c:choose>
				<c:when test="${shoppingCart != null}">
					<form class="navbar-form navbar-right" action="/logout" method="post">
						<button type="submit" class="form-control btn btn-info">
							<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Log Out
						</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form>
				</c:when>
			</c:choose>
			<form class="navbar-form navbar-right" action="/catalogue">
				<input type="text" class="form-control" id="search" placeholder="Search" name="search"></input>
				<button type="submit" class="btn btn-primary form-control">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				</button>
				<c:choose>
					<c:when test="${shoppingCart != null}">
						<button type="button" onclick="hideOrShowShoppingCart()" class="btn btn-success">
							<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Shopping Cart
						</button>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/login"/>" class="btn btn-success" role="button">
							<span class="glyphicon glyphicon-lock" aria-hidden="true"></span> Log in
						</a>
					</c:otherwise>
				</c:choose>
			</form>
		</div>
	</div>
</nav>
	<section id="content" class="expand">
		<article>
			<div class="container">
				<c:choose>
					<c:when test="${shoppingCart != null}">
						<div id="shoppingCart" class="hide" style="position: fixed; z-index: 2;">
							<div id="shoppingItems" class="well container">
								<table class='table table-condensed col-xs-12'>
									<thead>
									<tr>
										<th>Title</th>
										<th>Condition</th>
										<th>Price</th>
										<th>Qty</th>
										<th>Actions</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${shoppingCart}" var="cartitem">
										<tr>
											<td>${cartitem.stock.issue.issueTitle}</td>
											<td>${cartitem.stock.condition}</td>
											<td>R${cartitem.stock.price * cartitem.quantity}</td>
											<td>
												<button type='button' class='btn btn-default'
														onclick='decreaseQty()' style=''>
													<span class='glyphicon glyphicon-minus' aria-hidden='true'></span>
												</button>
													${cartitem.quantity}
												<button type='button' class='btn btn-default'
														onclick='increaseQty()' style=''>
													<span class='glyphicon glyphicon-plus' aria-hidden='true'></span>
												</button>
											</td>
											<td>
												<button type='button' class='btn btn-warning'
														onclick='removeFromCart()' style=''>
													<span class='glyphicon glyphicon-remove' aria-hidden='true'></span>
												</button>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<button type='button' class='btn' onclick='hideOrShowShoppingCart()' id='checkout' style='float:left; margin-right:5px'>
									<span class='glyphicon glyphicon-remove' aria-hidden='true'></span> Close
								</button>
								<button type='button' class='btn' onclick='removeCart()' id='checkout' style='float:left'>
									<span class='glyphicon glyphicon-trash' aria-hidden='true'></span> Trash
								</button>
								<button type='button' class='btn btn-success' action="/checkout" id='checkout' style='float:right'>
									<span class='glyphicon glyphicon-ok' aria-hidden='true'></span> Checkout
								</button>
							</div>
						</div>
					</c:when>
				</c:choose>
			</div>
		</article>
		<article>
			<div class="centerText">
                <h1 class="greeting">Welcome to Square Eyes!</h1>
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					<!-- Wrapper for slides -->
					<%--<h1 style="padding-top: 20px;">Our daily deals!</h1>--%>
					<div class="carousel-inner" role="listbox">
						<div class="item peopleCarouselImg active">
                             <%--items="${list}" var="issue">--%>
                                <%--<div class="item">--%>
									<%--<a href="/product?issue=2"><img class="item-image" src="/assets/images/SlideShow/c1.jpg" alt="Batman" width="100px" height="100px"></a>--%>
                                <%--<h1 class="title":> ${issue.getIssueTitle()}</h1>--%>
                                <%--<p class="price":>SALE</p>--%>
                                <%--<h2 class="description">${issue.getDescription()}</h2>--%>

                            <%--</c:forEach>--%>
								 <a href="/product?issue=2"><img class="d-block img-fluid center peopleCarouselImg" src="/assets/images/SlideShow/c1.jpg" alt="First slide" width="300px" height="300px"></a>
								 <div class="carousel-caption d-none d-md-block">
									 <h3>1st Issue Special</h3>
									 <p>R 188.03</p>
								 </div>
						</div>
						<div class="item peopleCarouselImg">
							<a href="/product?issue=64"><img class="d-block img-fluid center peopleCarouselImg" src="/assets/images/SlideShow/c2.jpg" alt="Second slide" width="300px" height="300px"></a>
							<div class="carousel-caption d-none d-md-block">
								<h3>52</h3>
								<p>R 186.53</p>
							</div>
						</div>
						<div class="item peopleCarouselImg">
							<a href="product?issue=100"><img class="d-block img-fluid center peopleCarouselImg" src="/assets/images/SlideShow/comic5.jpg" alt="Third slide" width="300px" height="300px"></a>
							<div class="carousel-caption d-none d-md-block">
								<h3>Action Comics (Vol. 1)</h3>
								<p>R 100.49</p>
							</div>
						</div>
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
					<%--</div>--%>

					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
                    <br/>
                    <br/>
                    <p><a class="btn btn-success btn-lg center" href="catalogue" role="button">Learn more &raquo;</a></p>
				</div>
			</div>

		</article>


	</section>
<footer class="footer-middle">
    <p>2017 - &copy;Entelect - Team Two Face</p>
</footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script src="<c:url value="/assets/js/global.js"/>"></script>
	<script src="<c:url value="/assets/js/catalogue.js"/>"></script>
</body>
</html>
