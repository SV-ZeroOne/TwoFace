using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class Order
    {
        public int OrderID { get; set; }

        public DateTime OrderDate { get; set; }

        //Check this foreign key relationship
        public int IssueID { get; set; }

        public int QtyOrdered { get; set; }

        public decimal Total { get; set; }

        public string ShipmentRef { get; set; }

        public DateTime ShipmentDate { get; set; }

        public string DeliveryStatus { get; set; }

        //Check this foreign key relationship
        public int SupplierID { get; set; }

        public virtual SupplierPayment SupplierPayment { get; set; }

        public virtual Supplier Supplier { get; set; }

        public virtual Issue Issue { get; set; }
    }
}
