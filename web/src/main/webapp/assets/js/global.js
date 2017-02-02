function hideOrShowShoppingCart(){
	if(document.getElementById('shoppingCart').className == "")
	{
		document.getElementById('shoppingCart').className = "hide"
	}
	else {
		document.getElementById('shoppingCart').className = ""
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

function populateCatalogue(page, itemsOnPage){
	var table = document.getElementById("catalogue")

	var images = []
	images.push("https://s-media-cache-ak0.pinimg.com/originals/12/55/76/125576e29feaded7181edbe006d23b5a.jpg")
	images.push("https://s-media-cache-ak0.pinimg.com/originals/b8/d8/cb/b8d8cb19503b644127da29e5b287e124.jpg")
	images.push("http://pre10.deviantart.net/23f7/th/pre/i/2015/327/0/d/star_wars_vintage_tfa_comic_cover_issue1_by_daztibbles-d9hq35o.png")
	images.push("http://2.bp.blogspot.com/-tTzzl84Ws5Q/UTUs9CDTcoI/AAAAAAAAgd8/iza6iW_ccmI/s1600/Dead+of+Night+11+-+00+-+FC.JPG")
	images.push("http://static3.wikia.nocookie.net/__cb20130903005637/mlp/images/0/01/Comic_Issue_10_Cover_B.jpg")
	images.push("https://s-media-cache-ak0.pinimg.com/originals/12/55/76/125576e29feaded7181edbe006d23b5a.jpg")
	images.push("https://s-media-cache-ak0.pinimg.com/originals/b8/d8/cb/b8d8cb19503b644127da29e5b287e124.jpg")
	images.push("http://pre10.deviantart.net/23f7/th/pre/i/2015/327/0/d/star_wars_vintage_tfa_comic_cover_issue1_by_daztibbles-d9hq35o.png")

	for (var i = (page-1)*itemsOnPage; i < issues.length && i < ((page-1)*itemsOnPage)+itemsOnPage; i++) {
		if(i%4 == 0)
			table.innerHTML += "<div class='container'>"

		var date = new Date(issues[i].PublicationDate)
		var description = ""
		//if(issues[i].Description != null)
		//	description = issues[i].Description
		var publisher = "-"
		var pubColor = "#34495E"
		if(issues[i].Publisher != 0){
			publisher = issues[i].Publisher
			if(publisher == "Marvel")
				pubColor = "#b71c1c"
			if(publisher == "DC")
				pubColor = "#1565C0"
		}

		table.innerHTML += "<div class='col-xs-offset-1 col-xs-10 col-sm-offset-0 col-sm-3 col-md-3'><div class='issue' style='margin:5px; box-shadow: 10px 10px 8px #222; margin-top: 15px;'><a class='image-container' href='product.html?issue=" + issues[i].Id + "'><img src='" + images.pop() + "' alt='Loading..' class='img-responsive'/></a><div class='issueNo'><button type='button' class='btn flat-butt'><strong>#" + issues[i].SeriesNumber + "</strong></button></div><div class='publisher'><button type='button' style='background-color:"+pubColor+";color: white;' class='btn flat-butt'><strong>" + publisher + "</strong></button></div><div class='details' style='padding:5px;'><div class='date'><button type='button' style='background-color:#666;color: white;' class='btn flat-butt'><strong>" + date.getFullYear() + "/" + date.getMonth() + "/" + date.getDay() + "</strong></button></div><h4><strong>" + issues[i].Title + "</strong></h4>" + "</div></div></div>";

		if(i%4)
			table.innerHTML += "</div>"
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
	if(stock.AvailableQuantity > 0) {
        if (localStorage.getItem("shoppingCart") == null) {
            shoppingCart = []
            issue.selectedStock = stock
			issue.qty = 1
            shoppingCart.push(issue)
            localStorage.setItem("shoppingCart", JSON.stringify(shoppingCart))
        }
        else {
            shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"))
            issue.selectedStock = stock
			issue.qty = 1
            shoppingCart.push(issue)
            localStorage.setItem("shoppingCart", JSON.stringify(shoppingCart))
        }
        populateShoppingCart(shoppingCart)
		hideOrShowShoppingCart()
    }else
	{
		alert("No items in stock")
	}
}

function increaseQty(stockID, csrftoken)
{
    $.ajax({
        type: "POST",
        url: "/shoppingcart/increase",
        data: { stock: stockID, _csrf: csrftoken}
    }).done(function() {
        $("#"+stockID+" .quantity").html(($("#"+stockID+" .quantity").html()*1)+1);

    });
}

function decreaseQty(stockID, csrftoken)
{
    $.ajax({
        type: "POST",
        url: "/shoppingcart/decrease",
        data: { stock: stockID, _csrf: csrftoken}
    }).done(function() {
        var quantityDiv = $("#"+stockID+" .quantity");
        $("#"+stockID+" .quantity").html(($("#"+stockID+" .quantity").html()*1)-1);
    });
}

function removeFromCart(stockID, csrftoken)
{
    $.ajax({
        type: "POST",
        url: "/shoppingcart/remove",
        data: { stock: stockID, _csrf: csrftoken}
    }).done(function() {
		$('#'+stockID).slideUp()

    });
}


function getIssue(issueId){
	for (var i = 0; i < issues.length; i++) {
		if(issues[i].Id == issueId)
		{
			return issues[i]
		}
	}
	if(location.search.includes('homepage.jsp'))
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
	if(location.search.includes('homepage.jsp'))
	return

	return window.location.href = "product.html?issue=" + issue.Id + "&stock=" + issue.Stock[0].Id
}

function checkout(){
	return window.location.href = "Checkout.html"
}

function populateShoppingCart(shoppingCart){
	//localStorage.setItem("shoppingCart") = null;
	var shoppingItems = document.getElementById("shoppingItems")

	if(localStorage.getItem("shoppingCart") != null){
		shoppingCart = JSON.parse(localStorage.getItem("shoppingCart"))
		if(shoppingCart.length > 0){
			var string = "<table class='table table-condensed col-xs-12'><thead><tr><th>Title</th><th>Condition</th><th>Price</th><th>Qty</th><th>Actions</th></tr></thead><tbody>"

			for(var x = 0; x < shoppingCart.length; x++){

				string += ""
			}
			string += ""
			shoppingItems.innerHTML = string
		}
		else{
			shoppingItems.innerHTML = "<h4>no items in shopping cart</h4><button type='button' class='btn' onclick='removeCart()' id='checkout' style='float:left; margin:5px;'>" +"<span class='glyphicon glyphicon-trash' aria-hidden='true'></span> Trash" +"</button>"
		}
	}
	else{
		shoppingItems.innerHTML = "<h4>no items in shopping cart</h4><button type='button' class='btn' onclick='removeCart()' id='checkout' style='float:left; margin:5px;'>" + "<span class='glyphicon glyphicon-trash' aria-hidden='true'></span> Trash" +	"</button>"
	}
}

function removeCart(){
	localStorage.removeItem("shoppingCart");
	populateShoppingCart()
}

function populatePaging(page){
	var table = document.getElementById("paging")

	if(page > 1)
	table.innerHTML += "<a class='btn flat-butt' style='float:left; background-color:#fff; color:black; margin:5px; box-shadow: 10px 10px 8px #222;' href='Catalogue.html?page=" + (page - 1) + "' role='button'>Page " + (page - 1) + " &raquo;</a>"

	table.innerHTML += "<a class='btn flat-butt' style='float:right; background-color:#fff; color:black; margin:5px; box-shadow: 10px 10px 8px #222;' href='Catalogue.html?page=" + (page*1 + 1) + "' role='button'>Page " + (page*1 + 1) + " &raquo;</a>"

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
