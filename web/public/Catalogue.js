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

function checkout(){
	return window.location.href = "Checkout.html"
}

function hideOrShowShoppingCart(){
	if(document.getElementById('shoppingCart').className == "")
	{
		document.getElementById('shoppingCart').className = "hide"
	}
	else {
		document.getElementById('shoppingCart').className = ""
	}
}

function populatePaging(page){
	var table = document.getElementById("paging")

	if(page > 1)
		table.innerHTML += "<a class='btn btn-primary' style='float:left' href='Catalogue.html?page=" + (page - 1) + "' role='button'>Page " + (page - 1) + " &raquo;</a>"

	table.innerHTML += "<a class='btn btn-primary' style='float:right' href='Catalogue.html?page=" + (page*1 + 1) + "' role='button'>Page " + (page*1 + 1) + " &raquo;</a>"

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

function populateCatalogue(page, itemsOnPage){
    var table = document.getElementById("catalogue")

    for (var i = (page-1)*itemsOnPage; i < issues.length && i < ((page-1)*itemsOnPage)+itemsOnPage; i++) {
		if(i%4 == 0)
			table.innerHTML += "<div class='container'>"
			var date = new Date(issues[i].PublicationDate)
			table.innerHTML += "<div class='col-xs-12 col-sm-3 col-md-3'><div style='margin:5px; box-shadow: 10px 10px 8px #aaa; border-radius: 5px;'><a href='product.html?issue=" + issues[i].Id + "'><img id='imagecomic' src='https://s-media-cache-ak0.pinimg.com/originals/b8/d8/cb/b8d8cb19503b644127da29e5b287e124.jpg' alt='Loading..' class='img-responsive'/><div style='margin:5px;'></a><h4>" + issues[i].Title + "</h4>"
	            + "<h4>" + date.getFullYear() + "/" + date.getMonth() + "/" + date.getDay() +
				" - #" + issues[i].SeriesNumber + " - " + issues[i].Publisher + "</h4>"
	            + "<p>" + issues[i].Description + "</p>"
	            + "</div></div></div>";
		if(i%4)
			table.innerHTML += "</div>"
    }

}

function search(){
    var name = document.getElementById("search")
    var table = document.getElementById("catalogue")
    table = "";
    for (var i = 0; i < issues.length; i++) {
        if(issues[i].Title == name){
            table.innerHTML += "<tr></tr><td><a href='product.html?issue=" + issues[i].Id + "'>" + issues[i].Title + "</a></td>"
                + "<td>" + issues[i].PublicationDate + "</td>"
                + "<td>" + issues[i].Publisher
                + "</td>" + "<td>" + issues[i].SeriesNumber
                + "</td>"+ "<td>" + issues[i].Description
                + "</td></tr>";
        }
    }
}
