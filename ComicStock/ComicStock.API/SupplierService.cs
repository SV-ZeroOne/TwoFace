using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.API
{
    public class SupplierService : ISupplierService
    {
        private readonly SupplierInterface supplierRepo;

        public SupplierService()
        {

        }

        public SupplierService(SupplierInterface supplierRepo)
        {
            this.supplierRepo = supplierRepo;
        }

        public void placeSupplier(string name, string city, string refnum)
        {
            Supplier supplier = new Supplier();
            supplier.Name = name;
            supplier.City = city;
            supplier.ReferenceNumber = refnum;

            supplierRepo.Add(supplier);
        }
    }
}
