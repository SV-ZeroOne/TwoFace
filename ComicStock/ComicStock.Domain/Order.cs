using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class Order : IEntity<int>
    {
        public Order()
        {

        }

        public int OrderID { get; set; }

        public DateTime OrderDate { get; set; }

        public int IssueID { get; set; }

        public Int16 QtyOrdered { get; set; }

        public decimal Total { get; set; }

        [MaxLength(10)]
        public string ShipmentRef { get; set; }

        public DateTime? ShipmentDate { get; set; }

        [MaxLength(20)]
        public string DeliveryStatus { get; set; }

        [ForeignKey("Supplier")]
        public int SupplierID { get; set; }

        public Boolean IsDeleted { get; set; }
       
        public bool isDeleted => IsDeleted;

        public virtual Supplier Supplier { get; set; }

        public virtual Issue Issue { get; set; }
    }
}
