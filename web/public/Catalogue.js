//document.getElementById('remove-me').className = "hide"
var page = 1
var itemsOnPage = 8
var shoppingCart

if(location.search.includes('page=')){
	page = location.search.split('page=')[1]
}

populateShoppingCart(shoppingCart)

populateCatalogue(page, itemsOnPage)

populatePaging(page)
