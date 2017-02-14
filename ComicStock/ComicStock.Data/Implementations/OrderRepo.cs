using ComicStock.Data.Interfaces;
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
                //var result = newContext.Set<Order>().Where(x => x.IssueID == Convert.ToInt32(keyword) ||
                //x.OrderDate == DateTime.Parse(keyword) ||
                //).ToList();
            }
            return newContext.Orders.AsExpandable().Where (predicate);
        }

    }
}
