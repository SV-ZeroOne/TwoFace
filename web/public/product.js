document.getElementById('remove-me').className = "hide"

var issueId
var stockId
var issue
var stock

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

function addToCart()
{
	console.log("Adding item to cart")
}

function getIssue(issueId){
	for (var i = 0; i < issues.length; i++) {
		if(issues[i].Id == issueId)
		{
			return issues[i]
		}
	}
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
	return window.location.href = "product.html?issue=" + issue.Id + "&stock=" + issue.Stock[0].Id
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
