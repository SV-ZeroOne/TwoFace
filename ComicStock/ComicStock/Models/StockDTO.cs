using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.Models
{
    public class StockDTO
    {
        public int StockReferenceID { get; set; }

        public int IssueID { get; set; }

        public string Condition { get; set; }

        public Int16 AvailableQuantity { get; set; }

        public decimal Price { get; set; }

        public StockDTO()
        {

        }

        public StockDTO(Stock stock)
        {
            this.StockReferenceID = stock.StockReferenceID;
            this.IssueID = stock.IssuesID;
            this.Condition = stock.Condition;
            this.AvailableQuantity = stock.AvailableQty;
            this.Price = stock.Price;
        }
    }
}