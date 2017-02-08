using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class Voucher : IEntity<int>
    {
        public Voucher()
        {

        }

        public int VoucherID { get; set; }

        [Required]
        public decimal Amount { get; set; }

        [Required]
        [MaxLength(50)]
        public string Code { get; set; }

        [Required]
        public DateTime DateIssued { get; set; }

        [Required]
        public bool Valid { get; set; }
    }
}
