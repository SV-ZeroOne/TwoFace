using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class SupplierPayment
    {
        public int PaymentID { get; set; }

        public int OrderID { get; set; }

        public decimal Total { get; set; }

        public DateTime ProcessedDate { get; set; }

        public virtual Order Order { get; set; }

        public virtual Supplier Supplier { get; set; }
   
    }
}
