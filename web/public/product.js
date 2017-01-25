document.getElementById('remove-me').className = "hide"

var issueId
var stockId
var issue
var stock
var shoppingCart

if(location.search.includes('&')){
	var parameters = location.search.split('&')
	issueId = parameters[0].split('issue=')[1]
	stockId = parameters[1].split('stock=')[1]
	issue = getIssue(issueId)
	stock = getStock(issue, stockId)
}
else{
	issueId = location.search.split('issue=')[1]
	issue = getIssue(issueId)
	stock = getStock(issue, 0)
	console.log("single")
}

populateIssue(issue, stock)
populateShoppingCart(shoppingCart)


function hideOrShowShoppingCart(){
	if(document.getElementById('shoppingCart').className == "")
	{
		document.getElementById('shoppingCart').className = "hide"
	}
	else {
		document.getElementById('shoppingCart').className = ""
	}
}


function populateIssue(issue, stock){
	document.getElementById('title').innerHTML = issue.Publisher + " - " + issue.Title + " - #" + issue.SeriesNumber
	document.getElementById('price').innerHTML += stock.Price
	document.getElementById('description').innerHTML = issue.Description
	document.getElementById('currentCondition').innerHTML += "Condition: " + stock.Condition
	for (var i = 0; i < issue.Stock.length; i++) {
		document.getElementById('condition').innerHTML += "<li><a href='product.html?issue=" + issue.Id + "&stock=" + issue.Stock[i].Id + "'>" + issue.Stock[i].Condition + "</a></li>"
	}
	var date = new Date(issue.PublicationDate)
	document.getElementById('publicationDate').innerHTML = date.getFullYear() + "/" + date.getMonth() + "/" + date.getDay()
	document.getElementById('addToCartButton').innerHTML += (stock.AvailableQuantity > 0) ? (stock.AvailableQuantity + " Left") : "Out of Stock"
}

function addToCart(shoppingCart)
{
	if(localStorage.getItem("shoppingCart") == null){
		shoppingCart = []
		shoppingCart.push(issue);
		localStorage.setItem("shoppingCart", JSON.stringify(shoppingCart))
	}
	else{
		shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"));
		shoppingCart.push(issue);
		localStorage.setItem("shoppingCart", JSON.stringify(shoppingCart))
	}
	populateShoppingCart(shoppingCart);
}


function getIssue(issueId){
	for (var i = 0; i < issues.length; i++) {
		if(issues[i].Id == issueId)
		{
			return issues[i]
		}
	}
	if(location.search.includes('homepage.html'))
		return

	window.location.href = "catalogue.html"
}

function getStock(issue, stockId){
	//if(!stockId) return
	for (var i = 0; i < issue.Stock.length; i++) {
		if(issue.Stock[i].Id == stockId)
		{
			return issue.Stock[i]
		}
	}
	if(location.search.includes('homepage.html'))
		return

	return window.location.href = "product.html?issue=" + issue.Id + "&stock=" + issue.Stock[0].Id
 }

 function checkout(){
 	return window.location.href = "Checkout.html"
 }

 function populateShoppingCart(shoppingCart){
 	var shoppingItems = document.getElementById("shoppingItems")

 	if(localStorage.getItem("shoppingCart") != null){
 		shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"))
 		console.log(shoppingCart);
 		var string = "<table class='table table-condensed col-xs-12'><thead><tr><th>Title</th><th>Condition</th><th>Price</th></tr></thead><tbody>"

 		for(var x = 0; x < shoppingCart.length; x++){

 			string += "<tr><td>" + shoppingCart[x].Title + "</td><td>" + "Very Fine" + "</td><td>R" + 255 + "</td></tr>"
 		}
 		string += "</tbody></table><button type='button' class='btn btn-success' onclick='checkout()' id='checkout' style='float:right'>" +
 			"<span class='glyphicon glyphicon-ok' aria-hidden='true'></span> Checkout" +
 			"</button>"
 		shoppingItems.innerHTML = string
 	}
 	else{
 		shoppingItems.innerHTML = "<h4>no items in shopping cart</h4>"
 	}
 }


function hideThis(){
	if(document.getElementById('sidebar').className == "")
	{
		document.getElementById('sidebar').className = "hide"
		document.getElementById('content').className = "expand"
	}
	else {
		document.getElementById('sidebar').className = ""
		document.getElementById('content').className = ""
	}
}
