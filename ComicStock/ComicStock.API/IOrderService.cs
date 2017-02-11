﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.API
{
    public interface IOrderService
    {
        void placeOrder(int issueID, Int16 quantity, int supplierIDs);

        void makePayment(int orderID);
    }
}