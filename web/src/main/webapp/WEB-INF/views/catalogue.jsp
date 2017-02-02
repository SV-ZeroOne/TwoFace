<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Index</title>
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
                <li><a href="/homepage">Home</a></li>
                <li class="active"><a href="/catalogue">Catalogue</a></li>
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
            <div id="catalogue" class="col-xs-12">
                <c:forEach items="${list}" var="issue">
                    <div class='col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3 col-md-3'>
                        <div class='issue' style='margin:5px; box-shadow: 10px 10px 8px #222; margin-top: 15px;'>
                            <a class='image-container' href='product?issue=${issue.issueID}'>
                                <%--<img src='http://pre10.deviantart.net/23f7/th/pre/i/2015/327/0/d/star_wars_vintage_tfa_comic_cover_issue1_by_daztibbles-d9hq35o.png' alt='Loading..' class='img-responsive'/>
                         --%>
                                <img src="${issue.imageRef}" alt='Loading..' onerror="this.src='http://pre10.deviantart.net/23f7/th/pre/i/2015/327/0/d/star_wars_vintage_tfa_comic_cover_issue1_by_daztibbles-d9hq35o.png'" class='img-responsive'/>
                            </a>
                            <div class='issueNo'><button type='button' class='btn flat-butt'>
                                <strong>#${issue.seriesNumber}</strong>
                            </button></div>
                            <div class='publisher'>
                                <button type='button' style='background-color:${issue.publisher == "Marvel" ? "#b71c1c" : "#1565C0"};color: white;' class='btn flat-butt'>
                                    <strong>${issue.publisher}</strong>
                                </button>
                            </div>
                            <div class='details' style='padding:5px;'><div class='date'>
                                <button type='button' style='background-color:#666;color: white;' class='btn flat-butt'>
                                    <strong>${issue.publicationDate.year} / ${issue.publicationDate.month} / ${issue.publicationDate.day}</strong>
                                </button>
                            </div><h4><strong>${issue.issueTitle}</strong></h4>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>

            <div id="paging" class="col-xs-offset-2 col-xs-8">
                <c:choose>
                    <c:when test="${page > 1}"><a class='btn flat-butt' style='float:left; background-color:#fff;
                        color:black; margin:5px; box-shadow: 10px 10px 8px #222;' href='/catalogue?search=${search}&page=${page - 1}'
                                                   role='button'>Page ${page - 1}&raquo;</a>
                    </c:when>
                </c:choose>
                <a class='btn flat-butt' style='float:right; background-color:#fff;
                color:black; margin:5px; box-shadow: 10px 10px 8px #222;' href='/catalogue?search=${search}&page=${page + 1}'
                   role='button'>Page ${page + 1} &raquo;</a>
            </div>

            <div id="footer" class="col-xs-12" style="margin-top: 30px;">

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
