using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Implementations
{
    class SupplierRepo : EFRepository<Supplier, int>, SupplierInterface
    {

        public override IEnumerable<Supplier> Paging(int page, int pageSize)
        {
            return context.Set<Supplier>()
                .OrderBy(x => x.SupplierID)
                .Skip((page - 1) * pageSize)
                .Take(pageSize)
                .ToList();
        }

    }
}
