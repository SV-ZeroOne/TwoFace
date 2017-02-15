using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.WebAPI.Models
{
    public class SupplierDTO
    {
        public int SupplierID { get; set; }

        public string Name { get; set; }

        public string City { get; set; }

        public string ReferenceNumber { get; set; }

        public Boolean IsDeleted { get; set; }

        public SupplierDTO()
        {

        }

        public SupplierDTO(Supplier supplier)
        {
            this.SupplierID = supplier.SupplierID;
            this.Name = supplier.Name;
            this.City = supplier.City;
            this.ReferenceNumber = supplier.ReferenceNumber;
            this.IsDeleted = supplier.IsDeleted;
        }
    }
}