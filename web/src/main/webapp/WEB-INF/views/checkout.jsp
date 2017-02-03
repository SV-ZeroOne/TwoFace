<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>Checkout</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/catalogue.css"/>"/>

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
</head>
<body class="body">

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
        </div>
    </div>
</nav>
<section>
    <article>
        <div class="container" style="padding-top:10px"> <!--REVIEW ORDER-->
            <div class="well col-xs-12 col-md-5">
                <div class="panel panel-info">
                    <div class="panel-heading">Your Order</div>
                            <%-- <div class="pull-right"></div>--%>
                        <div class="panel-cart" id="shoppingItems">
                            <c:choose>
                            <c:when test="${shoppingCart != null}">
                                <c:set var="subTotal" value="${0}"/>
                                <table class='table col-xs-12'>
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Title</th>
                                            <th>Condition</th>
                                            <th>Qty</th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach items="${shoppingCart}" var="cartitem">
                                        <tr>
                                            <td><img src="${cartitem.stock.issue.imageRef}" alt="http://pre10.deviantart.net/23f7/th/pre/i/2015/327/0/d/star_wars_vintage_tfa_comic_cover_issue1_by_daztibbles-d9hq35o.png" class='img-responsive' height="50" width="50"/>
                                            </td>
                                            <td>${cartitem.stock.issue.issueTitle}</td>
                                            <td>${cartitem.stock.condition}</td>
                                            <td>${cartitem.quantity}</td>
                                            <c:set var="subTotal" value="${subTotal + cartitem.stock.price*cartitem.quantity}"></c:set>
                                            <td>R${cartitem.stock.price * cartitem.quantity}</td>
                                        </tr>
                                        </c:forEach>
                                    <tr>
                                        <td>Total:</td>
                                        <td></td>
                                        <td></td>
                                        <td>R${subTotal}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </c:when>
                            </c:choose>
                        </div>
                </div>
            </div>
            <div class="well col-xs-12 col-md-offset-2 col-md-5">
                <div class="panel panel-info">
                    <div class="panel-heading">Customer Details</div>
                    <%-- <div class="pull-right"></div>--%>
                    <div class="panel-body" id="shippingItems">
                    <c:choose>
                        <c:when test="${customer != null}">
                            <table class='table col-xs-12'>
                                <thead>
                                    <tr>
                                        <th>Details</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Name : </td>
                                        <td>${customer.firstName}</td>
                                        <%--<td>${User.name}</td>
                                        <td>Stanley Martin</td>--%>
                                    </tr>
                                    <tr>
                                        <td>Surname : </td>
                                        <td>${customer.surname}</td>
                                        <%--<td>Lieber</td>--%>
                                    </tr>
                                    <tr>
                                        <td>Address : </td>
                                        <%--<td>${User.name}</td>--%>
                                        <td>135 W. 50th Street</td>
                                    </tr>
                                    <tr>
                                        <td>City : </td>
                                        <%--<td>${User.name}</td>--%>
                                        <td>New York City</td>
                                    </tr>
                                    <tr>
                                        <td>Country : </td>
                                        <%--<td>${User.name}</td>--%>
                                        <td>United States</td>
                                    </tr>
                                    <tr>
                                        <td>Contact Details : </td>
                                        <%--<td>${User.name}</td>--%>
                                        <td>888-511-5480</td>
                                    </tr>
                                    <tr>
                                        <td>Email : </td>
                                        <td>${customer.email}</td>
                                        <%--<td>marvelsubs@midtowncomics.com </td>--%>
                                    </tr>
                                </tbody>
                            </table>
                        </c:when>
                        </c:choose>
                    </div>
                </div>
            </div> <!--REVIEW USER END-->
            <div class="well col-xs-12 col-md-offset-7 col-md-5">
                <div class="panel panel-info">
                    <div class="panel-heading"><span><i class="glyphicon glyphicon-lock"></i></span> Secure Payment</div>
                    <%-- <div class="pull-right"></div>--%>
                    <div class="panel-body" id="shippingItems">
                        <div class="col-md-12"><strong>Card Type:</strong></div>
                        <div class="col-md-12">
                            <select id="CreditCardType" name="CreditCardType" class="form-control">
                                <option value="5">Visa</option>
                                <option value="6">MasterCard</option>
                                <option value="7">American Express</option>
                                <option value="8">Discover</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <Label for="cardNumber">Credit Card Number:</Label>
                            <input class="form-control" type="text"  name="cardNumber" id="cardNumber" placeholder="1234543122345" required/>
                        </div>
                        <div class="form-group">
                            <Label for="cardCode">Card CVV:</Label>
                            <input type="text" class="form-control" name="cardCode" id="cardCode" placeholder="***" required/>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <strong>Expiration Date</strong>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="Month">
                                    <option value="">Month</option>
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09">09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                <select class="form-control" name="Year">
                                    <option value="">Year</option>
                                    <option value="2017">2017</option>
                                    <option value="2018">2018</option>
                                    <option value="2019">2019</option>
                                    <option value="2020">2020</option>
                                    <option value="2021">2021</option>
                                    <option value="2022">2022</option>
                                    <option value="2023">2023</option>
                                    <option value="2024">2024</option>
                                    <option value="2025">2025</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <button type="button" class="btn checkout-accept btn-lg" data-toggle="modal" data-target="#Confirmation">
                                    Purchase
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!--PAYMENT DETAILS END-->
        </div> <!--REVIEW USER DETAILS END-->
    </article>
</section>
    <div class="modal confirm" id="Confirmation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel">Success</h4>
                </div>
                <div class="modal-body">
                    Order Completed Successfully
                </div>
                <div class="modal-footer">
                    <button type="button"  class="btn btn-default" data-dismiss="modal" onclick="location.href='homepage'"/>Completed</button>
                </div>
            </div>
        </div>
    </div>
<footer class="footer-middle">
    <p>&copy; 2017 - Entelect - Team Two Face</p>
</footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="<c:url value="/assets/js/global.js"/>"></script>
    <script src="<c:url value="/assets/js/catalogue.js"/>"></script>
</body>
</html>
