using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Implementations
{
    class VoucherRepo : EFRepository<Voucher, int>, VoucherInterface
    {
        public override IEnumerable<Voucher> Paging(int page, int pageSize)
        {
            return context.Set<Voucher>()
                .OrderByDescending(x => x.DateIssued)
                .Skip((page-1) * pageSize)
                .Take(pageSize)
                .ToList();
        }

        public override int recordCount(string searchQuery)
        {
            searchQuery = searchQuery.ToLower() == "valid" ? "true" : searchQuery.ToLower() == "invalid" ? "false" : searchQuery;

            return context.Set<Voucher>()
                .OrderByDescending(x => x.DateIssued)
                .Where(x => x.Amount.ToString().Contains(searchQuery) ||
                x.Code.Contains(searchQuery) ||
                x.Valid.ToString().Contains(searchQuery))
                .Count();
        }

        public override IEnumerable<Voucher> Paging(int page, int pageSize, string searchQuery)
        {
            searchQuery = searchQuery.ToLower() == "valid" ? "true" : searchQuery.ToLower() == "invalid" ? "false" : searchQuery;

            return context.Set<Voucher>()
                .OrderByDescending(x => x.DateIssued)
                .Where(x => x.Amount.ToString().Contains(searchQuery) ||
                x.Code.Contains(searchQuery) ||
                x.Valid.ToString().Contains(searchQuery))
                .Skip((page - 1) * pageSize)
                .Take(pageSize)
                .ToList();
        }
    }
}
