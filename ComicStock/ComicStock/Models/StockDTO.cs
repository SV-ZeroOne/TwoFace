using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.Models
{
    public class StockDTO
    {
        public int Id { get; set; }

        public int Condition { get; set; }

        public int AvailableQuantity { get; set; }

        public float Price { get; set; }

        public StockDTO()
        {

        }

        public StockDTO(Stock stock)
        {
            this.StockReferenceID = stock.StockReferenceID;
            this.IssueID = stock.IssueID;
            this.Condition = stock.Condition;
            this.AvailableQuantity = stock.AvailableQty;
            this.Price = stock.Price;
        }
    }
}