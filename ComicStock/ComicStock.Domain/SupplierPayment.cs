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
    public partial class SupplierPayment : IEntity<int>
    {
        public SupplierPayment()
        {

        }

        [Key]
        public int PaymentID { get; set; }

        [ForeignKey("Order")]
        public int OrderID { get; set; }

        [Required]
        public decimal Total { get; set; }

        public DateTime ProcessedDate { get; set; }

        public virtual Order Order { get; set; }

        public bool IsDeleted { get; set; }

        public bool isDeleted => IsDeleted;

    }
}
