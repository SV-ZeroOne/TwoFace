<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
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
				<a class="navbar-brand" href="homepage">Square Eyes</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="homepage">Home</a></li>
					<li><a href="catalogue">Catalogue</a></li>
				</ul>
				<form class="navbar-form navbar-right" action="/catalogue">
					<input type="text" class="form-control" id="search" placeholder="Search" name="search"></input>
					<button type="button" class="btn btn-primary" name="search">Search</button>
				</form>
			</div><!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container" id="checkoutPage">
		<div class="container wrapper">
			<div class="row cart-body">
				<form class="form-horizontal" method="post" action="">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 ">
						<!--REVIEW ORDER-->
						<div class="panel panel-info">
							<div class="panel-heading">
								Your Order <div class="pull-right"></div>
							</div>
							<div class="panel-cart" id="shoppingItems">
							<c:forEach items="${list}" var="issue">
								<!--Here we pull our product items into cart details-->
								<div class="form-group" id="checkout-cart-items"></div>
									<div>${issue.issueTitle} + ${issue.publisher}</div>
									<div class="col-xs-12">
										<strong>Subtotal</strong>
										<div class="pull-right" id="Subtotal"><span>$</span><span>0.00</span></div>
									</div>

							</c:forEach>
								<div class="form-group"><hr /></div>
								<div class="form-group">
									<div class="col-xs-12">
										<strong>Order Total</strong>
										<div class="pull-right" id="Order-total"><span>$</span><span>0.00</span></div>
									</div>
								</div>
							</div>
						</div>

						<!--REVIEW ORDER END-->
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 panel-group">
						<!--SHIPPING METHOD-->
						<div class="panel panel-info">
							<div class="panel-heading">Shipping Details</div>
							<div class="panel-body">
								<div class="form-group">
									<div class="col-md-12">
										<h4>Shipping Details</h4>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">Stan</div>
									<div class="col-md-12">Lee</div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>Address : </strong>Number</div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>City : </strong>Street</div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>Province : </strong>Province</div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>Zip / Postal Code : </strong>1111</div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>Phone Number : </strong>011-1234567</div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>Email Address : </strong>stan.lee@mail.com</div>
								</div>
							</div>
							<!--SHIPPING METHOD END-->
							<!--CREDIT CART PAYMENT-->
							<div class="panel-heading"><span><i class="glyphicon glyphicon-lock"></i></span> Secure Payment</div>
							<div class="panel-body">
								<div class="form-group">
									<div class="col-md-12"><strong>Card Type:</strong></div>
									<div class="col-md-12">
										<select id="CreditCardType" name="CreditCardType" class="form-control">
											<option value="5">Visa</option>
											<option value="6">MasterCard</option>
											<option value="7">American Express</option>
											<option value="8">Discover</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>Credit Card Number:</strong></div>
									<div class="col-md-12"><input type="text" class="form-control" name="car_number" value="*****************" /></div>
								</div>
								<div class="form-group">
									<div class="col-md-12"><strong>Card CVV:</strong></div>
									<div class="col-md-12"><input type="text" class="form-control" name="car_code" value="***" /></div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<strong>Expiration Date</strong>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
										<select class="form-control" name="">
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
										<select class="form-control" name="">
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
							<!--CREDIT CART PAYMENT END-->
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
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
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="removeCartAfterPurchase()">Completed</button>
                </div>
            </div>
        </div>
    </div>
    <footer class="footer-middle">
        <p>&copy; 2017 - Entelect - Team Two Face</p>
    </footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	<script type="text/javascript" src="Issues.json"></script>
	<script type="text/javascript" src="global.js"></script>
	<script type="text/javascript" src="Checkout.js"></script>
</body>
</html>
