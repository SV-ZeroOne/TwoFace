using System;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;

namespace ComicStock.API
{
    public class StockService : IStockService
    {

        private StockInterface stockInterface;

        public StockService(StockInterface stockInterface)
        {
            this.stockInterface = stockInterface;
        }

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

        public void RemovingStock(int id, int quantity)
        {
            Stock stock = stockInterface.GetById(id);

            if ((stock == null) && (quantity <= 0))
            {
                throw new Exception();
            }

            stock.AvailableQty -= (Int16)quantity;

            stockInterface.Update(stock);
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
