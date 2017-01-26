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
            stock = shoppingCart[x].selectedStock
            issue = shoppingCart[x]
            string += "<tr><td>" + issue.Title + "</td><td>" + stock.Condition + "</td><td>R" + stock.Price + "</td></tr>"

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

function removeCartAfterPurchase(){
    removeCart()
    window.location="Catalogue.html"
}