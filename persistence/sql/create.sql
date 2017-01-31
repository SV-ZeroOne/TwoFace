-- Create Statements


DROP TABLE SquareEyes.dbo.Payments;
GO
DROP TABLE SquareEyes.dbo.VoucherPayments;
GO
DROP TABLE SquareEyes.dbo.CardPayments;
GO
DROP TABLE SquareEyes.dbo.Vouchers;
GO
DROP TABLE SquareEyes.dbo.ShoppingCarts;
GO
DROP TABLE SquareEyes.dbo.Invoices;
GO
DROP TABLE SquareEyes.dbo.CustomerOrders;
GO
DROP TABLE SquareEyes.dbo.CustomerAddress;
GO
DROP TABLE SquareEyes.dbo.Customers;




CREATE TABLE Customers
(
	CustomerID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    Email VARCHAR(50) UNIQUE NOT NULL,
    Title VARCHAR(5) NOT NULL,
	FirstName VARCHAR(30) NOT NULL,
	Surname VARCHAR(30) NOT NULL,
	Salt VARCHAR(15) NOT NULL,
	PasswordHash BINARY(64) NOT NULL
);

CREATE TABLE CustomerAddress
(
	CustomerAddressID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	CustomerID INT FOREIGN KEY REFERENCES Customers(CustomerID) NOT NULL,
	DeliveryDetails VARCHAR(255) NOT NULL,
	Suburb VARCHAR(50) NOT NULL,
	City VARCHAR(50) NOT NULL,
	PostalCode VARCHAR(4) NOT NULL,
	AddressType VARCHAR(15) NOT NULL
);

CREATE TABLE CustomerOrders
(
	CustomerOrdersID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	CustomerID INT FOREIGN KEY REFERENCES Customers(CustomerID) NOT NULL,
	CustomerAddressID INT FOREIGN KEY REFERENCES CustomerAddress(CustomerAddressID) NULL,
	DeliveryOption VARCHAR(15) NOT NULL,
	SpecialInstructions VARCHAR(50),
	PaymentAmount NUMERIC(8, 2) NOT NULL,
	PaymentStatus VARCHAR(15) NOT NULL,
	OrderDate DATETIME NOT NULL
);

CREATE TABLE Invoices
(
	InvoiceID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	CustomerOrderID INT FOREIGN KEY REFERENCES CustomerOrders(CustomerOrdersID) NOT NULL,
	StockID INT FOREIGN KEY REFERENCES Stock(StockReferenceID) NOT NULL,
	Quantity SMALLINT NOT NULL,
	Price NUMERIC(8,2) NOT NULL
);

CREATE TABLE ShoppingCarts
(
	ShoppingCartID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
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
	VoucherValue NUMERIC(8,2) NOT NULL
);

CREATE TABLE VoucherPayments
(
	PaymentID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	CustomerOrderID INT FOREIGN KEY REFERENCES CustomerOrders(CustomerOrdersID) NOT NULL,
	VoucherID INT FOREIGN KEY REFERENCES Vouchers(VoucherID) NOT NULL,
	VoucherAmount NUMERIC(8,2) NULL
);

CREATE TABLE CardPayments
(
	PaymentID INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	CustomerOrderID INT FOREIGN KEY REFERENCES CustomerOrders(CustomerOrdersID) NOT NULL,
	ReferenceID VARCHAR(50) UNIQUE NOT NULL,
	VoucherAmount NUMERIC(8,2) NULL
);

