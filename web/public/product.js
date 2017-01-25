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
