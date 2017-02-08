using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class Supplier
    {
        public int SupplierID { get; set; }

        public string Name { get; set; }

        public string City { get; set; }

        public string ReferenceNumber { get; set; }

        public virtual SupplierQuote SupplierQuote { get; set; }

        public virtual Order Order { get; set; }

    }
}
