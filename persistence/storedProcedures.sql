-- Stored Procedures

-- Write a stored procedure to process an order. It should take the contents of a customer’s shopping cart, 
--		create the appropriate order and order details entries as necessary, 
--		reduce the in-stock quantity of the items purchased and empty the shopping cart. 
--		Ensure that the entire process either completes correctly or rolls back entirely and throws a relevant error message.



-- Write a table-valued function that takes a customer, 
--		start date and end date as parameters and returns the list of items they purchased in that period. 
--		An item should only appear once, with a total of how many were purchased.



-- Write a stored procedure which returns a list of stock items 
--		for which the current in-stock quantity is less than the total quantity in all current customers’
--		shopping carts combined.



-- Write a stored procedure which takes a start date and an end date as parameters
--		returns the sales totals for that period per title. 
--		There should also be a total per publisher and a grand total. 



-- Take one of the procedures you have written and do the following: ( WE SHOULD MAYBE DO THIS IN WORD I DON'T KNOW )
--		1.	Recommend an index which will improve performance of that procedure.
--		2.	What are the performance characteristics and execution plans before and after the change? (include execution plans as .sqlplan files, not images)

