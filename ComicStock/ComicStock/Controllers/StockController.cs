using ComicStock.API;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ComicStock.WebAPI.Controllers
{
    public class StockController : ApiController
    {
        private readonly IStockService stock;
        private readonly StockInterface stockRepo;
        private List<StockDTO> newStocks;

        public StockController(StockInterface stockRepo, IStockService stock)
        {
            this.stock = stock;
            this.stockRepo = stockRepo;
            IEnumerable someStock = stockRepo.GetAll();
            newStocks = new List<StockDTO>();
            foreach (Stock i in someStock)
            {
                StockDTO newStock = new StockDTO(i);
                newStocks.Add(newStock);
            }
        }

        public StockController()
        {
        }

        // PUT api?id={id}&quantity={quantity}
        [Route("api/")]
        public void Put(int id, int quantity)
        {
            stock.AddStock(id, quantity);
        }

        // DELETE api/id
        [Route("api/id")]
        public void Delete(int id, int quantity)
        {
            stock.RemovingStock(id, quantity);
        }

        // PUT api?id={id}&condition={condition}
        [Route("api/")]
        public void PutForReturn (int id, string condition)
        {
            stock.ReturningStock(id, condition);
        }

        // GET api/stocks
        public IEnumerable<StockDTO> Get()
        {
            return newStocks;
        }

        // GET api/stocks/id
        public StockDTO GetById(int id)
        {
            Stock someStock = stockRepo.GetById(id);
            if (someStock == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            StockDTO someStockDTO = new StockDTO(someStock);
            return someStockDTO;
        }

        // Lamda
        public IList<StockDTO> Get(string search)
        {
            return Get().Where(i => i.Condition.Contains(search)).ToList<StockDTO>();
        }

        //PUT api/stocks
        public StockDTO Put(StockDTO stock)
        {
            //Need to have error handling!
            Stock stockToGet = stockRepo.GetById(stock.StockReferenceID);
            var stockToUpdate = updateStock(stock, stockToGet);
            stockRepo.Update(stockToUpdate);
            return stock;
        }

        private Stock updateStock(StockDTO stock, Stock stockToUpdate)
        {
            stockToUpdate.IssueID = stock.IssueID;
            stockToUpdate.Condition = stock.Condition;
            stockToUpdate.AvailableQty = stock.AvailableQuantity;
            stockToUpdate.Price = stock.Price;
            return stockToUpdate;
        }

        //DELETE api/stocks/id
        public void Delete(StockDTO stock)
        {
            //Need to have error handling!
            var stockToDelete = stockRepo.GetById(stock.StockReferenceID);
            stockRepo.Delete(stockToDelete);
        }

        //POST api/stocks
        public Stock Post(StockDTO stockDto)
        {
            if (stockDto == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.NotAcceptable);
            }
            Stock newStock = convertDTO(stockDto);
            stockRepo.Add(newStock);
            return newStock;
        }

        private Stock convertDTO(StockDTO stockDto)
        {
            Stock newStock = new Stock();
            newStock.IssueID = stockDto.IssueID;
            newStock.Condition = stockDto.Condition;
            newStock.AvailableQty = stockDto.AvailableQuantity;
            newStock.Price = stockDto.Price;
            newStock.IsDeleted = stockDto.IsDeleted;
            return newStock;
        }
    }
}
