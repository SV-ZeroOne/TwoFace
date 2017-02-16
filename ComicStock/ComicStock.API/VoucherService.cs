using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.API
{
    public class VoucherService : IVoucherService
    {
        private readonly VoucherInterface voucherRepo;

        public VoucherService()
        {

        }

        public VoucherService(VoucherInterface voucherRepo)
        {
            this.voucherRepo = voucherRepo;
        }

        public void placeVoucher(decimal amount, string code, bool valid)
        {
            Voucher voucher = new Voucher();
            voucher.Amount = amount;
            voucher.Code = code;
            voucher.DateIssued = DateTime.Now;
            voucher.Valid = valid;

            voucherRepo.Add(voucher);
        }
    }
}
