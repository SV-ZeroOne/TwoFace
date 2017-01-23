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

    for (var i = 0; i < issues.length; i++) {
        table.innerHTML += "<tr></tr><td><a href='product.html?issue=" + issues[i].Id + "'>" + issues[i].Title + "</a></td>"
            + "<td>" + issues[i].PublicationDate + "</td>"
            + "<td>" + issues[i].Publisher
            + "</td>" + "<td>" + issues[i].SeriesNumber
            + "</td>"+ "<td>" + issues[i].Description
            + "</td></tr>";
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

var Person = function(name){
	this.name = name;
}

Person.prototype.toString = function(){
	console.log(this.name)
}

var joe = new Person('Joe')

joe.toString()

/*
	Variables

	Primitives
	var string = "my string" / 'my string'
	var number = 1 / 1.5647
	var boolean = true / false

	var myObject = {
		name: "Pieter",
		age: 73
	}

	Specials
		undifined
		null
		NaN


}
*/
