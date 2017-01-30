-- Stored Procedures

-- Write a stored procedure to process an order. It should take the contents of a customerï¿½s shopping cart, 
--		create the appropriate order and order details entries as necessary, 
--		reduce the in-stock quantity of the items purchased and empty the shopping cart. 
--		Ensure that the entire process either completes correctly or rolls back entirely and throws a relevant error message.

--DUMMY DATA

INSERT INTO dbo.Customers (Email, Title, FirstName, Surname)
VALUES ('quinton@gmail.com', 'Mr', 'Quinton', 'Weenink'),
('sean@gmail.com', 'Mr', 'Sean', 'Seansurname'),
('Mpho@gmail.com', 'Mr', 'Mpho', 'Mphosurname');

INSERT INTO dbo.CustomerAddress (CustomerID, DeliveryDetails, Suburb, City, PostalCode, AddressType)
VALUES (1, '7 Malberry Drive', 'Queens Park', 'Johannesburg', '2256', 'Delivery'),
(2, '7 Malberry Drive', 'Queens Park', 'Johannesburg', '2256', 'Delivery'),
(3, '7 Malberry Drive', 'Queens Park', 'Johannesburg', '2256', 'Delivery')

INSERT INTO dbo.ShoppingCarts(CustomerID, StockID, Quantity)
VALUES (1, 1, 2), (1, 3, 1),
(2, 2, 1),(2, 6, 7),
(3, 8, 1)

ALTER PROCEDURE dbo.ProcessCustomerOrder (@CustomerID INT, @CustomerAddressID INT, @DeliveryOption VARCHAR(15), @SpecialInstructions VARCHAR(50))
AS

BEGIN TRY
BEGIN TRANSACTION

DECLARE @PaymentAmount NUMERIC(8,2);
DECLARE @StockLeft varchar(15);
DECLARE @OrderTbl TABLE (ID INT)
DECLARE @OrderID int;

SET @CustomerID = (SELECT c.CustomerID FROM dbo.Customers as c
	WHERE c.CustomerID = @CustomerID);

IF @CustomerID IS NULL
	RAISERROR ('No such customer with that ID exists', -- Message text.
               16, -- Severity.
               1 -- State.
               );

SET @CustomerAddressID = (SELECT ca.CustomerAddressID FROM dbo.CustomerAddress as ca
	WHERE ca.CustomerID = @CustomerID AND ca.CustomerAddressID = @CustomerAddressID);

IF @CustomerAddressID IS NULL
	RAISERROR ('Customer Address is not associated with the Customer ID or No such customer address with that ID exists', -- Message text.
               16, -- Severity.
               1 -- State.
               );

SET @PaymentAmount = (SELECT SUM(s.Price * sc.Quantity) AS TotalCostOfOrder
	FROM dbo.Stock as s
	INNER JOIN dbo.ShoppingCarts as sc
	ON s.StockReferenceID = sc.StockID
	GROUP BY sc.CustomerID
	HAVING sc.CustomerID = @CustomerID);

IF @PaymentAmount IS NULL OR @PaymentAmount < 0
	RAISERROR ('There is no items in cart or the payment amount is null', -- Message text.
               16, -- Severity.
               1 -- State.
               );





INSERT INTO dbo.CustomerOrders (CustomerID, CustomerAddressID, DeliveryOption, SpecialInstructions, PaymentAmount, PaymentStatus) 
OUTPUT INSERTED.CustomerOrdersID INTO @OrderTbl(ID)
VALUES (@CustomerID, @CustomerAddressID, @DeliveryOption, @SpecialInstructions, @PaymentAmount, 'Pending Payment');

SET @OrderID = (
	SELECT ot.ID FROM @OrderTbl AS ot
);

IF @OrderID IS NULL
	RAISERROR ('Problem processing order', -- Message text.
               16, -- Severity.
               1 -- State.
               );

INSERT INTO dbo.Invoices (OrderID, StockID, Quantity, Price)
SELECT @OrderID, s.StockReferenceID, sc.Quantity, s.Price
FROM dbo.ShoppingCarts AS sc
INNER JOIN dbo.Stock AS s
ON sc.StockID = s.StockReferenceID
	
UPDATE
    s
SET
    s.AvailableQty = s.AvailableQty - sc.Quantity
FROM
    dbo.Stock AS s
    INNER JOIN dbo.ShoppingCarts AS sc
        ON s.StockReferenceID = sc.StockID AND sc.CustomerID = @CustomerID
WHERE
    s.StockReferenceID = sc.StockID

DELETE FROM dbo.ShoppingCarts
WHERE dbo.ShoppingCarts.CustomerID = @CustomerID;

COMMIT

END TRY
BEGIN CATCH
	SELECT 'Error "' + ERROR_MESSAGE() + '" occurred on line ' + CAST(ERROR_LINE() AS VARCHAR(5)); 
    ROLLBACK TRANSACTION
END CATCH


EXEC dbo.ProcessCustomerOrder @CustomerID = 1, @CustomerAddressID = 1, @DeliveryOption = 'Pickup', @SpecialInstructions = 'Leave it on the desk';

-- No such customer id
EXEC dbo.ProcessCustomerOrder @CustomerID = 55, @CustomerAddressID = 1, @DeliveryOption = 'Pickup', @SpecialInstructions = 'Leave it on the desk';

-- No such address id
EXEC dbo.ProcessCustomerOrder @CustomerID = 1, @CustomerAddressID = 55, @DeliveryOption = 'Pickup', @SpecialInstructions = 'Leave it on the desk';

-- Incorrect address id
EXEC dbo.ProcessCustomerOrder @CustomerID = 1, @CustomerAddressID = 2, @DeliveryOption = 'Pickup', @SpecialInstructions = 'Leave it on the desk';

-- Incorrect customer id
EXEC dbo.ProcessCustomerOrder @CustomerID = 2, @CustomerAddressID = 1, @DeliveryOption = 'Pickup', @SpecialInstructions = 'Leave it on the desk';


-- No stock left for issue customer id
EXEC dbo.ProcessCustomerOrder @CustomerID = 3, @CustomerAddressID = 3, @DeliveryOption = 'Pickup', @SpecialInstructions = 'Leave it on the desk';

-- Customer order
EXEC dbo.ProcessCustomerOrder @CustomerID = 1, @CustomerAddressID = 1, @DeliveryOption = 'Pickup', @SpecialInstructions = 'Leave it on the desk';


SET @StockLeft = (
	SELECT 'No stock left'
	FROM dbo.Stock as s
	INNER JOIN dbo.ShoppingCarts as sc
	ON s.StockReferenceID = sc.StockID
	WHERE (s.AvailableQty - sc.Quantity) < 0
);

IF @StockLeft IS NOT NULL
	RAISERROR ('There is not enough stock for one of the items in the shopping cart', -- Message text.
               16, -- Severity.
               1 -- State.
               );

-- Write a table-valued function that takes a customer, 
--		start date and end date as parameters and returns the list of items they purchased in that period. 
--		An item should only appear once, with a total of how many were purchased.



-- Write a stored procedure which returns a list of stock items 
<<<<<<< HEAD
--		for which the current in-stock quantity is less than the total quantity in all current customers’
--		shopping carts combined.Error "Subquery returned more than 1 value. This is not permitted when the subquery follows =, !=, <, <= , >, >= or when the subquery is used as an expression." occurred on line 42
=======
--		for which the current in-stock quantity is less than the total quantity in all current customersï¿½
--		shopping carts combined.
USE SquareEyes
GO
>>>>>>> e671f08f345166e99b8756ad6d8ad7151b20bd99

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

