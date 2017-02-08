using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class Voucher
    {
        public int VoucherID { get; set; }

        public decimal Amount { get; set; }

        public string Code { get; set; }

        public DateTime DateIssued { get; set; }

        public bool Valid { get; set; }
    }
}
