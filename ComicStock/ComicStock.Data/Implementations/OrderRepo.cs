﻿using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using LinqKit;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Implementations
{
    internal class OrderRepo : EFRepository<Order, int>, OrderInterface
    {
        SquareEyesContext newContext = new SquareEyesContext();

        public IEnumerable<Order> SearchOrders(params string[] keywords)
        {
            var predicate = PredicateBuilder.False<Order>();
            foreach(string keyword in keywords)
            {
                var temp = keyword;
                predicate = predicate.Or(p => p.OrderID.ToString().Contains(temp));
            }
            return newContext.Orders.AsExpandable().Where (predicate);
        }

        public override IEnumerable<Order> Paging(int page, int pageSize)
        {
            return context.Set<Order>()
                .OrderBy(x => x.OrderID)
                .Skip((page-1) * pageSize)
                .Take(pageSize)
                .ToList();
        }

    }
}
