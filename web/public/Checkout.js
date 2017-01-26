//document.getElementById('remove-me').className = "hide"
var page = 1;
var itemsOnPage = 8;
var shoppingCart
var issue
var stock

if(location.search.includes('cart-display')){
	page = location.search('cart-display');
}

//populateShoppingCart(shoppingCart)
populateCheckoutCart(shoppingCart)
populateCatalogue(page, itemsOnPage)

populatePaging(page);


function populateCheckoutCart(shoppingCart){
    var shoppingItems = document.getElementById("shoppingItems")

    if(localStorage.getItem("shoppingCart") != null){
        shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"))
        console.log(shoppingCart);
        var string = "<table class='table table-condensed col-xs-12'><thead><tr><th>Title</th><th>Condition</th><th>Price</th></tr></thead><tbody>"

        for(var x = 0; x < shoppingCart.length; x++){

            string += "<tr><td>" + shoppingCart[x].Title + "</td><td>" + "Very Fine" + "</td><td>R" + shoppingCart[x].selectedStock + "</td></tr>"

        }
      /*  string += "</tbody></table><button type='button' class='btn btn-success' onclick='checkout()' id='checkout' style='float:right'>" +
            "<span class='glyphicon glyphicon-ok' aria-hidden='true'></span> Checkout" +
            "</button>"
      */  shoppingItems.innerHTML = string
    }
    else{
        shoppingItems.innerHTML = "<h4>no items in shopping cart</h4>"
    }
}