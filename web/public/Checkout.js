//document.getElementById('remove-me').className = "hide"
var page = 1;
var itemsOnPage = 8;
var shoppingCart

if(location.search.includes('cart-display')){
	page = location.search('cart-display');
}

populateShoppingCart(shoppingCart)
//populateCheckoutCart(shoppingCart)
populateCatalogue(page, itemsOnPage)

populatePaging(page);
