﻿using ComicStock.Data.Interfaces;
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

    }
}
