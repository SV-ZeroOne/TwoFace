-- 1.	What is the total value of their current stock (use the retail prices), grouped by publisher? - Sean
SELECT sup.SupplierID,
	SUM(st.AvailableQty) AS TotalValue
FROM dbo.Stock AS st
	INNER JOIN dbo.SupplierQuotes AS sup
	ON st.IssueID = sup.IssueID
GROUP BY sup.SupplierID;

-- 2.	What is the total value of currently outstanding orders (use the Order total), grouped by supplier? - Quinton

SELECT s.Name,
	SUM(o.Total) AS TotalValue
FROM dbo.Orders AS o
	INNER JOIN dbo.Suppliers AS s
	ON o.SupplierID = s.SupplierID
GROUP BY s.Name;

-- 3.	Which comic book title represents the largest portion of the total stock value? - Mpho

-- 4.	What title has been ordered most frequently from suppliers (number of distinct orders, not total quantity ordered), and how many of that title have been ordered? Break the results down by the series number and include the total cost of all the orders. - Sean
WITH temp AS
	(SELECT    TOP(1) i.Title,
    COUNT(*) AS NumberOrdered
FROM dbo.Issues AS i INNER JOIN dbo.Orders AS o ON i.IssueID = o.IssueID
GROUP BY i.Title)

SELECT i.Title, i.SeriesNumber, Count(i.IssueID) AS OrdersPlaced, SUM(o.Total) AS TotalCost

FROM dbo.Issues AS i INNER JOIN dbo.Orders AS o
ON i.IssueID = o.IssueID
WHERE i.Title = (SELECT temp.Title FROM temp)
GROUP BY i.Title, i.SeriesNumber, i.IssueID
ORDER BY i.SeriesNumber;
-- 5.	If we consider only stock in the ‘Very Fine’ condition, and only stock for which we have a quote from a supplier, how much would it cost to bring all comic stock to at least 20 units? In addition, the co-owner would like some information about the comics, for some advertising he’s planning - Quinton

SELECT SUM(sq.Price)
FROM dbo.Stock AS s
	INNER JOIN dbo.SupplierQuotes AS sq
	ON s.IssueID = sq.IssueID
WHERE s.Condition = 'Very Fine';

-- 6.	Identify the writer/artist who has contributed to the most comic books. What is the person’s name, what are the 10 most recent comics he’s contributed to and what was his role on each? - Mpho

-- 7.	What title has the largest number of comics? Specify per publisher.
SELECT i.Publisher,
	count(DISTINCT i.SeriesNumber) AS NumberOfComics
FROM dbo.Issues as i
GROUP BY i.Publisher;
-- In light of the recent increase in interest due to the new movie, the co-owner intends to do some Star Wars specific advertising - Sean
-- 8.	What are the 5 most recently published Star Wars titles, and how much stock is on hand for each one? - Quinton
WITH ordI AS (
	SELECT TOP(5) i.Title, 
		i.PublicationDate,
		i.IssueID 
	FROM dbo.Issues AS i
	WHERE i.Title like '%star wars%'
	ORDER BY i.PublicationDate DESC
	)
SELECT i.Title, SUM(s.AvailableQty) AS StockOnHand
FROM ordI AS i
	INNER JOIN dbo.Stock AS s
	ON i.IssueID = s.IssueID
GROUP BY i.IssueID, i.Title;

-- 9.	Which Star Wars comics have been ordered but not delivered? How many of each have been ordered? How many of each remain in stock? - Mpho
