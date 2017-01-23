document.getElementById('remove-me').className = "hide"

var issue = getIssue()
populateIssue(issue)


function populateIssue(issue){
	document.getElementById('title').innerHTML = issue.Publisher + " - " + issue.Title
	document.getElementById('seriesNumber').innerHTML += issue.SeriesNumber
	document.getElementById('description').innerHTML = issue.Description
	var date = new Date(issue.PublicationDate);
	document.getElementById('publicationDate').innerHTML = date.getFullYear() + "/" + date.getMonth() + "/" + date.getDay()
	document.getElementById('addToCartButton').innerHTML += (issue.Stock[0].AvailableQuantity > 0) ? (issue.Stock[0].AvailableQuantity + " Left") : "Out of Stock"
}

function addToCart()
{
	console.log("Adding item to cart")
}

function getIssue(){
	var issueId = location.search.split('issue=')[1]

	for (var i = 0; i < issues.length; i++) {
		if(issues[i].Id == issueId)
		{
			return issues[i]
		}
	}

	window.location.href = "index.html"
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
