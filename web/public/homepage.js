document.getElementById('remove-me').className = "hide"

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


