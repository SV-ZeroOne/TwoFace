using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.API
{
    public interface IVoucherService
    {
        void placeVoucher(decimal amount, string code, bool valid);
    }
}
