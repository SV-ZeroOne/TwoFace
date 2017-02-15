using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain 
{
    public partial class Supplier : IEntity<int>
    {
        public Supplier()
        {

        }

        public int SupplierID { get; set; }

        [MaxLength(50)]
        public string Name { get; set; }

        [MaxLength(50)]
        public string City { get; set; }

        [MaxLength(25)]
        public string ReferenceNumber { get; set; }

        public Boolean IsDeleted { get; set; }

        public bool isDeleted => IsDeleted;

        //public virtual SupplierQuote SupplierQuote { get; set; }

        //public virtual Order Order { get; set; }

    }
}
