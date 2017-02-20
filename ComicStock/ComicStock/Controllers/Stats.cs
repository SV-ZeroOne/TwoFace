using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.WebAPI.Controllers
{
    public class Stats
    {
        public Stats()
        {

        }

        public int TotalUsedVouchers { get; set; }
        public int Vouchers2017 { get; set; }
        public int Vouchers2016 { get; set; }
        public int Vouchers2015 { get; set; }
        public int Vouchers2014 { get; set; }
        public decimal totalValue { get; set; }
        public decimal valueUsed { get; set; }
    }
}