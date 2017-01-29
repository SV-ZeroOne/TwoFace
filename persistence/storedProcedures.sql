-- Stored Procedures

-- Write a stored procedure to process an order. It should take the contents of a customer’s shopping cart, 
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

SET @CustomerID = (SELECT c.CustomerID FROM dbo.Customers as c
	WHERE c.CustomerID = @CustomerID)

IF @CustomerID IS NULL
	RAISERROR ('No such customer with that ID exists', -- Message text.
               16, -- Severity.
               1 -- State.
               );

SET @CustomerAddressID = (SELECT ca.CustomerAddressID FROM dbo.CustomerAddress as ca
	WHERE ca.CustomerID = @CustomerID AND ca.CustomerAddressID = @CustomerAddressID)

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
	HAVING sc.CustomerID = @CustomerID)

IF @PaymentAmount IS NULL OR @PaymentAmount < 0
	RAISERROR ('There is no items in cart or the payment amount is null', -- Message text.
               16, -- Severity.
               1 -- State.
               );


SET @StockLeft = (
	SELECT 'No stock left'
	FROM dbo.Stock as s
	INNER JOIN dbo.ShoppingCarts as sc
	ON s.StockReferenceID = sc.StockID
	WHERE (s.AvailableQty - sc.Quantity) < 0
	GROUP BY sc.CustomerID
)

IF @StockLeft IS NOT NULL
	RAISERROR ('There is not enough stock for one of the items in the shopping cart', -- Message text.
               16, -- Severity.
               1 -- State.
               );


INSERT INTO dbo.CustomerOrders (CustomerID, CustomerAddressID, DeliveryOption, SpecialInstructions, PaymentAmount, PaymentStatus)  
SELECT @CustomerID, @CustomerAddressID, @DeliveryOption, @SpecialInstructions, @PaymentAmount, 'pending'

COMMIT

END TRY
BEGIN CATCH
	SELECT 'Error "' + ERROR_MESSAGE() + '" occurred on line ' + CAST(ERROR_LINE() AS VARCHAR(5)); 
    ROLLBACK TRANSACTION
END CATCH

GO

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

-- Write a table-valued function that takes a customer, 
--		start date and end date as parameters and returns the list of items they purchased in that period. 
--		An item should only appear once, with a total of how many were purchased.



-- Write a stored procedure which returns a list of stock items 
--		for which the current in-stock quantity is less than the total quantity in all current customers’
--		shopping carts combined.Error "Subquery returned more than 1 value. This is not permitted when the subquery follows =, !=, <, <= , >, >= or when the subquery is used as an expression." occurred on line 42



-- Write a stored procedure which takes a start date and an end date as parameters
--		returns the sales totals for that period per title. 
--		There should also be a total per publisher and a grand total. 



-- Take one of the procedures you have written and do the following: ( WE SHOULD MAYBE DO THIS IN WORD I DON'T KNOW )
--		1.	Recommend an index which will improve performance of that procedure.
--		2.	What are the performance characteristics and execution plans before and after the change? (include execution plans as .sqlplan files, not images)

