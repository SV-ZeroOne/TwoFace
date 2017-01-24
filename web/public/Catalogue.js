//document.getElementById('remove-me').className = "hide"
populateCatalogue();

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

function populateCatalogue(){
    var table = document.getElementById("catalogue")

    for (var i = 0; i < issues.length / 10; i++) {
		if(i%4 == 0)
			table.innerHTML += "<div class='container'>"
			var date = new Date(issues[i].PublicationDate)
			table.innerHTML += "<div class='col-xs-12 col-sm-3 col-md-3' style='padding:2px;'><h2>" + issues[i].Title + "</h2><a href='product.html?issue=" + issues[i].Id + "'><img id='imagecomic' src='https://s-media-cache-ak0.pinimg.com/originals/b8/d8/cb/b8d8cb19503b644127da29e5b287e124.jpg' alt='Loading..' class='img-responsive'/></a>"
	            + "<h4>" + date.getFullYear() + "/" + date.getMonth() + "/" + date.getDay() + "</h4>"
				+ "<h4>#" + issues[i].SeriesNumber + "</h4>"
	            + "<h4>" + issues[i].Publisher + "</h4>"
	            + "<p>" + issues[i].Description + "</p>"
	            + "</div>";
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
