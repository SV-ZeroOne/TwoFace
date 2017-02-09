using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.API
{
    public class OrderService : OrderServiceInterface
    {
        private readonly IssueInterface issueRepo;
        private readonly OrderInterface orderRepo;
        private readonly SupplierInterface supplierRepo;
        private readonly SupplierQuoteInterface supplierQuoteRepo;

        public OrderService()
        {

        }

        public OrderService(IssueInterface issuerepo, OrderInterface orderrepo, SupplierInterface supplierRepo, SupplierQuoteInterface supplierQuoteRepo)
        {
            this.issueRepo = issuerepo;
            this.orderRepo = orderrepo;
            this.supplierRepo = supplierRepo;
            this.supplierQuoteRepo = supplierQuoteRepo;
        }

        public void placeOrder(int issueID, Int16 quantity)
        {
            Issue issue = new Issue();
            issue = issueRepo.GetById(issueID);
            //Check for supplier quote
            SupplierQuote theQuote = supplierQuoteRepo.getSupplierQuoteByIssue(issueID);
            Supplier theSupplier = supplierRepo.GetById(theQuote.SupplierID);
            Order newOrder = new Order();
            newOrder.OrderDate = DateTime.Now;
            newOrder.QtyOrdered = quantity;
            newOrder.Total = quantity * theQuote.Price;
            newOrder.SupplierID = theSupplier.SupplierID;
            //These next few fields and their info must come from the supplier API
            newOrder.ShipmentRef = null;
            newOrder.ShipmentDate = null;
            newOrder.DeliveryStatus = "Pending Payment";
        }

    }
}
