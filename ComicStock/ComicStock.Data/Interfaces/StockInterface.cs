﻿using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Interfaces
{
    public interface StockInterface : IRepository<Stock, int>
    {
        Stock checkExistingStock(int issueID, string condition);
    }
}
