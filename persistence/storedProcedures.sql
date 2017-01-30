-- Stored Procedures

-- Write a stored procedure to process an order. It should take the contents of a customer�s shopping cart, 
--		create the appropriate order and order details entries as necessary, 
--		reduce the in-stock quantity of the items purchased and empty the shopping cart. 
--		Ensure that the entire process either completes correctly or rolls back entirely and throws a relevant error message.



-- Write a table-valued function that takes a customer, 
--		start date and end date as parameters and returns the list of items they purchased in that period. 
--		An item should only appear once, with a total of how many were purchased.



-- Write a stored procedure which returns a list of stock items 
--		for which the current in-stock quantity is less than the total quantity in all current customers�
--		shopping carts combined.
USE SquareEyes
GO

ALTER PROCEDURE CheckStockAvailability (@IssueStock VARCHAR(50))
AS

--SELECT
	--s.AvailableQty FROM Stock AS s
--WHERE s.IssueID = @IssueStock


--DECLARE query VARCHAR(MAX)
--SET query = ('SELECT s.Quantity, SUM(c.Quantity) AS CartsTotal FROM ShoppingCart AS c
--INNER JOIN Stock AS s ON s.IssueID = ShoppingCart);

SELECT * FROM STOCK AS s
WHERE Exists
(SELECT SUM(c.Quantity) FROM CusShoppingCarts AS c --CusShoppingCart is storing Quantity as a varchar :(
WHERE c.Title = @IssueStock);
--
EXEC CheckStockAvailability @IssueStock = "52";
GO
--Problem here, stock table doesnt have a reference to the issue,
--IssueID is returning not the unique Identifier Expected,
--rather the issue number for a comic book series
--Example issue 1 of Action Comics and issue 1 of Incredible Hulk


-- Write a stored procedure which takes a start date and an end date as parameters
--		returns the sales totals for that period per title. 
--		There should also be a total per publisher and a grand total. 

	--for TotalPerIssue
CREATE PROCEDURE TotalSales
	@startdate SMALLDATETIME,
	@enddate SMALLDATETIME
AS
SELECT iss.Title,
	   SUM(co.PaymentAmount) AS TotalPerIssue
FROM dbo.CustomerOrders AS co 
INNER JOIN dbo.Invoices AS inv
ON inv.orderID = co.customerOrdersID
INNER JOIN dbo.Stock AS s
ON s.StockReferenceID = inv.StockID
INNER JOIN dbo.Issues AS iss
ON iss.IssueID = s.IssueID
INNER JOIN dbo.Orders AS o
ON o.IssueID = iss.IssueID
WHERE o.ShipmentDate BETWEEN @startdate AND @enddate
GROUP BY iss.Title
ORDER BY TotalPerIssue DESC;

	--for TotalPerPublisher
CREATE PROCEDURE TotalSales
	@startdate SMALLDATETIME,
	@enddate SMALLDATETIME
AS
SELECT iss.Publisher,
	   SUM(co.PaymentAmount) AS TotalPerPublisher
FROM dbo.CustomerOrders AS co 
INNER JOIN dbo.Invoices AS inv
ON inv.orderID = co.customerOrdersID
INNER JOIN dbo.Stock AS s
ON s.StockReferenceID = inv.StockID
INNER JOIN dbo.Issues AS iss
ON iss.IssueID = s.IssueID
INNER JOIN dbo.Orders AS o
ON o.IssueID = iss.IssueID
WHERE o.ShipmentDate BETWEEN @startdate AND @enddate
GROUP BY iss.Publisher
ORDER BY TotalPerPublisher DESC;

-- Take one of the procedures you have written and do the following: ( WE SHOULD MAYBE DO THIS IN WORD I DON'T KNOW )
--		1.	Recommend an index which will improve performance of that procedure.
--		2.	What are the performance characteristics and execution plans before and after the change? (include execution plans as .sqlplan files, not images)

