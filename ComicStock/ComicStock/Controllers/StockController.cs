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
        private int totalRecords = 0;

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
            totalRecords = newStocks.Count();
        }

        public StockController()
        {
        }

        // PUT api?id={id}&quantity={quantity}
        [Route("api/Stock/Add")]
        public void Put(int id, int quantity)
        {
            stock.AddStock(id, quantity);
        }

        // DELETE api/id
        [Route("api/Stock/Remove")]
        public void Delete(int id, int quantity)
        {
            stock.RemovingStock(id, quantity);
        }

        // PUT api?id={id}&condition={condition}
        [Route("api/Stock/Return")]
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
            string searchString = search.ToLower();
            return Get().Where(i => i.Condition.ToLower().Contains(searchString)).ToList<StockDTO>();
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
            Stock existingStock = stockRepo.checkExistingStock(stockDto.IssueID, stockDto.Condition);
            if(existingStock != null)
            {
                existingStock.AvailableQty += stockDto.AvailableQuantity;
                existingStock.Price = stockDto.Price;
                stockRepo.Update(existingStock);
                return existingStock;
            }else
            {
                stockRepo.Add(newStock);
                return newStock;
            }

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

        [Route("api/Stock/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10)
        {
            // Determine the number of records to skip
            int skip = (pageNo - 1) * pageSize;

            // Get total number of records

            int total = totalRecords;

            // Select the customers based on paging parameters
            List<StockDTO> stock = newStocks
                .OrderBy(c => c.StockReferenceID)
                .Skip(skip)
                .Take(pageSize)
                .ToList();

            // Return the list of customers
            return Ok(new PagedResult<StockDTO>(stock, pageNo, pageSize, total));
        }

        [Route("api/Stock/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        {
            if (searchKey != null)
            {
                string searchString = searchKey.ToLower();
                IEnumerable<StockDTO> someStock = Get().Where(i => i.Condition.ToLower().Contains(searchString));
                int pageSize = 20;
                // Determine the number of records to skip
                int skip = (pageNumber - 1) * pageSize;

                // Get total number of records

                int total = someStock.Count();

                // Select the customers based on paging parameters
                List<StockDTO> stock = someStock
                    .OrderBy(c => c.StockReferenceID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                // Return the list of customers
                return Ok(new PagedResult<StockDTO>(stock, pageNumber, pageSize, total));
            }
            return null;
        }
    }
}
