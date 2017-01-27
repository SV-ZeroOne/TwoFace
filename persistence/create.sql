-- Create Statements

CREATE TABLE Invoice
(
	OrderID int FOREIGN KEY REFERENCES CustomerOrders(OrderID) NOT NULL,
	StockID int FOREIGN KEY REFERENCES Stock(StockReferenceID) NOT NULL,
	Quantity smallint NOT NULL,
	Price numeric(8,2) NOT NULL
);

CREATE TABLE ShoppingCarts
(
	CustomerID INT FOREIGN KEY REFERENCES Customers(CustomerID) NOT NULL,
	StockID INT FOREIGN KEY REFERENCES Stock(StockReferenceID) NOT NULL,
	Quantity SMALLINT NOT NULL
);

CREATE TABLE Vouchers
(
	VoucherID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	VoucherNumber VARCHAR(10) UNIQUE NOT NULL,
	DateIssued DATETIME NOT NULL,
	DateRedeemed DATETIME NULL,
	VoucherValue NUMERIC(8,2) NOT NULL,
);

CREATE TABLE Payments
(
	PaymentID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	OrderID INT FOREIGN KEY REFERENCES CustomerOrders(OrderID) NOT NULL,
	VoucherID INT FOREIGN KEY REFERENCES Vouchers(VoucherID) NOT NULL,
	VoucherAmount NUMERIC(8,2) NULL,
);