using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class SupplierQuote : IEntity<int>
    {
        public SupplierQuote()
        {

        }

        [Key]
        public int QuoteID { get; set; }

        public int IssueID { get; set; }

        public int SupplierID { get; set; }

        public decimal Price { get; set; }

        public DateTime EffectiveDate { get; set; }

        public virtual Issue Issue { get; set; }

        public virtual Supplier Supplier { get; set; }
    }
}
