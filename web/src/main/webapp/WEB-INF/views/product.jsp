<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/catalogue.css"/>
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
            <a class="navbar-brand" href="/homepage">Square Eyes</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/homepage">Home</a></li>
                <li><a href="/catalogue">Catalogue</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" id="search" placeholder="Search" name="q"></input>
                <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                <sec:authorize access="hasRole('USER')">
                    <button type="button" onclick="hideOrShowShoppingCart()" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Shopping Cart</button>
                </sec:authorize>
            </form>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<section id="content" class="expand">
    <article>
        <div class="container">
            <div id="shoppingCart" class="hide" style="position: fixed; z-index: 2;">
                <div id="shoppingItems" class="well container">
                    <sec:authorize access="hasRole('USER')">
                        <c:choose>
                            <c:when test="${shoppingCart != null}">
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
                                <button type='button' class='btn btn-success' onclick='checkout()' id='checkout' style='float:right'>
                                    <span class='glyphicon glyphicon-ok' aria-hidden='true'></span> Checkout
                                </button>

                            </c:when>
                        </c:choose>
                    </sec:authorize>
                </div>
            </div>
            <div class="container" style="padding-top:10px">
                <div class="col-sm-4 col-md-4 col-xs-12">
                    <img id="imagecomic" src="https://s-media-cache-ak0.pinimg.com/originals/b8/d8/cb/b8d8cb19503b644127da29e5b287e124.jpg" alt="Loading.." class="img-responsive"/>
                </div>
                <div class="col-sm-8 col-md-8 col-xs-12" id="comicdetails">
                    <h3 id="title">${stock.issue.issueTitle}</h3>
                    <h3 id="price">${stock.price}</h3>
                    <h4 id="publicationDate">${stock.issue.publicationDate.year} / ${stock.issue.publicationDate.month} / ${stock.issue.publicationDate.day}</h4>
                    <h5 id="description">${stock.issue.description}</h5>
                    <div id="condtionDropdown" class="dropdown">
                        <button id="currentCondition" class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                            ${stock.condition} - <strong>R</strong> ${stock.price} <span class="caret"></span>
                        </button>
                        <ul id="condition" class="dropdown-menu">
                            <c:forEach items="${stockList}" var="s">
                                <li><a href='/product?stock=${s.stockReferenceID}'>
                                ${s.condition} - <strong>R</strong> ${s.price}
                                </a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <form id="addToCartForm" method="post" action="/shoppingcart">
                        <input type="hidden" name="stockID" value="${stock.stockReferenceID}"/>
                        <input type="hidden" name="quantity" value="1"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>

                    <button type="submit" form="addToCartForm" class="btn btn-success" onclick="addToCart()" id="addToCartButton">
                        <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
                         Add to Cart - ${stock.availableQty} Left
                    </button>
                </div>
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
<script src="<c:url value="/assets/js/product.js"/>"></script>
</body>
</html>
