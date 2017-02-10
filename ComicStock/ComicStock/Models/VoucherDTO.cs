using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.WebAPI.Models
{
    public class VoucherDTO
    {
        public int VoucherID { get; set; }

        public decimal Amount { get; set; }

        public string Code { get; set; }

        public DateTime DateIssued { get; set; }

        public bool Valid { get; set; }

        public VoucherDTO()
        {

        }

        public VoucherDTO(Voucher voucher) 
        {
            this.VoucherID = voucher.VoucherID;
            this.Amount = voucher.Amount;
            this.Code = voucher.Code;
            this.DateIssued = voucher.DateIssued;
            this.Valid = voucher.Valid;
        }

    }
}