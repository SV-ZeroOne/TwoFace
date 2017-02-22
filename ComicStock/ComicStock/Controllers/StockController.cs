using ComicStock.API;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using System.Collections.Generic;
using System.Linq;
using System.Net;
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
            newStocks = new List<StockDTO>();
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
            return Get().Where(i => i.Condition.ToLower().Contains(searchString) ||
            i.IssueID.ToString().Contains(searchString) ||
            i.StockReferenceID.ToString().Contains(searchString) ||
            i.Price.ToString().Contains(searchString)
            ).ToList<StockDTO>();
        }

        //PUT api/stocks
        public StockDTO Put(StockDTO stock)
        {
            Stock stockToGet = stockRepo.GetById(stock.StockReferenceID);
            if (stockToGet == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
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
            var stockToDelete = stockRepo.GetById(stock.StockReferenceID);
            if (stockToDelete == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
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

        private StockDTO convertObject(Stock stock)
        {
            StockDTO newStock = new StockDTO();
            newStock.StockReferenceID = stock.StockReferenceID;
            newStock.IssueID = stock.IssueID;
            newStock.Condition = stock.Condition;
            newStock.AvailableQuantity = stock.AvailableQty;
            newStock.Price = stock.Price;
            return newStock;
        }

        [Route("api/Stock/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10)
        {
            int skip = (pageNo - 1) * pageSize;

            int total = stockRepo.recordCount();
            IEnumerable<Stock> stock = stockRepo.Paging(pageNo, pageSize);
            IList<StockDTO> stockDTO = new List<StockDTO>();

            foreach (var item in stock)
            {
                stockDTO.Add(convertObject(item));
            }

            return Ok(new PagedResult<StockDTO>(stockDTO, pageNo, pageSize, total));
        }

        [Route("api/Stock/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        {
            if (searchKey != null)
            {
                string searchString = searchKey.ToLower();
                IEnumerable<StockDTO> someStock = Get().Where(i => i.Condition.ToLower().Contains(searchString) ||
                i.IssueID.ToString().Contains(searchString) ||
                i.StockReferenceID.ToString().Contains(searchString) ||
                i.Price.ToString().Contains(searchString));
                int pageSize = 20;
                int skip = (pageNumber - 1) * pageSize;

                int total = someStock.Count();

                List<StockDTO> stock = someStock
                    .OrderBy(c => c.StockReferenceID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                return Ok(new PagedResult<StockDTO>(stock, pageNumber, pageSize, total));
            }
            return null;
        }
    }
}
