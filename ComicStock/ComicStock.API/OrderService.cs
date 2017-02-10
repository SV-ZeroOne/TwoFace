using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.API
{
    public class OrderService : IOrderService
    {
        private readonly IssueInterface issueRepo;
        private readonly OrderInterface orderRepo;
        private readonly SupplierInterface supplierRepo;
        private readonly SupplierQuoteInterface supplierQuoteRepo;
        private readonly SupplierPaymentInterface supplierPaymentRepo;

        public OrderService()
        {

        }

        public OrderService(IssueInterface issuerepo, OrderInterface orderrepo, SupplierInterface supplierRepo, SupplierQuoteInterface supplierQuoteRepo, SupplierPaymentInterface suppPay)
        {
            this.issueRepo = issuerepo;
            this.orderRepo = orderrepo;
            this.supplierRepo = supplierRepo;
            this.supplierQuoteRepo = supplierQuoteRepo;
            this.supplierPaymentRepo = suppPay;
        }

        public void placeOrder(int issueID, Int16 quantity, int supplierID)
        {
            Issue issue = new Issue();
            issue = issueRepo.GetById(issueID);
            var someissueID = issue.IssueID;
            //Check for supplier quote
            SupplierQuote theQuote = supplierQuoteRepo.getSupplierQuote(issueID, supplierID);
            Supplier theSupplier = supplierRepo.GetById(theQuote.SupplierID);
            Order newOrder = new Order();
            newOrder.IssueID = issue.IssueID;
            newOrder.OrderDate = DateTime.Now;
            newOrder.QtyOrdered = quantity;
            newOrder.Total = quantity * theQuote.Price;
            newOrder.SupplierID = theSupplier.SupplierID;
            //These next few fields and their info must come from the supplier API
            newOrder.ShipmentRef = null;
            newOrder.ShipmentDate = null;
            newOrder.DeliveryStatus = "Pending Payment";
            orderRepo.Add(newOrder);
        }

        //Make a payment to supplier
        public void makePayment(int orderID)
        {
            Order order = orderRepo.GetById(orderID);
            SupplierPayment newPayment = new SupplierPayment();
            newPayment.OrderID = order.OrderID;
            //might have to pass in partial payment
            newPayment.Total = order.Total;
            newPayment.ProcessedDate = DateTime.Now;
            //might need to change the order status if paid.
            supplierPaymentRepo.Add(newPayment);
        }

    }
}
