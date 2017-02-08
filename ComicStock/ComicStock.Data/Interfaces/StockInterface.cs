using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Interfaces
{
    class StockInterface : IRepository<Stock, int>
    {
        SquareEyesContext context = new SquareEyesContext();

        public void Add(Stock Entity)
        {
            context.Stock.Add(Entity);
        }

        public void Delete(Stock Entity)
        {
            Stock stock = context.Stock.Remove(Entity);
        }

        public IEnumerable<Stock> GetAll()
        {
            return context.Stock.ToList();
        }

        public Stock GetById(int id)
        {
            return context.Stock.Find(id);
        }

        public void Update(Stock Entity)
        {
            context.Entry(Entity).State = System.Data.Entity.EntityState.Modified;
            context.SaveChanges();
        }
    }
}
