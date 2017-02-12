using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.WebAPI.Models
{
    public class OrderDTO
    {
        public int OrderID { get; set; }

        public DateTime OrderDate { get; set; }

        public int IssueID { get; set; }

        public Int16 QtyOrdered { get; set; }

        public decimal Total { get; set; }

        public string ShipmentRef { get; set; }

        public DateTime? ShipmentDate { get; set; }

        public string DeliveryStatus { get; set; }

        public int SupplierID { get; set; }

        public OrderDTO()
        {

        }

        public OrderDTO(Order someOrder)
        {
            //might need to add orderID as it returns an ID of 0
            this.OrderID = someOrder.OrderID;
            this.OrderDate = someOrder.OrderDate;
            this.IssueID = someOrder.IssueID;
            this.QtyOrdered = someOrder.QtyOrdered;
            this.Total = someOrder.Total;
            this.ShipmentRef = someOrder.ShipmentRef;
            this.ShipmentDate = someOrder.ShipmentDate;
            this.DeliveryStatus = someOrder.DeliveryStatus;
            this.SupplierID = someOrder.SupplierID;
        }
    }
}