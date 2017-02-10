using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ComicStock.WebAPI;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Data.Implementations;

namespace ComicStock.API
{
    public class StockService : IStockService
    {
        //gonna need to use the repos and controllers
        private StockInterface stockInterface;
        //private Stock stockObject;

        public StockService(StockInterface stockInterface)
        {
            this.stockInterface = stockInterface;
        }

        //public void AddStock(int id, Stock stock)
        public void AddStock(int id, int quantity)
        {
            Stock stock = stockInterface.GetById(id);

            if ((quantity <= 0) && (stock == null))
            {
                throw new Exception();
            }

            stock.AvailableQty += (Int16) quantity;

            stockInterface.Update(stock);
        }

        public void RemovingStock(int id)
        {
            Stock stock = stockInterface.GetById(id);

            if (stock == null)
            {
                throw new Exception();
            }

            stockInterface.Delete(stock);
        }

        public void ReturningStock(int id, String condition)
        {
            Stock stock = stockInterface.GetById(id);

            if (stock == null)
            {
                throw new Exception();
            }

            stock.Condition = condition;

            stockInterface.Update(stock);
        }
    }
}
