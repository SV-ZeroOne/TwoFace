using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.WebAPI.Models
{
    public class SupplierQuoteDTO
    {

        public int QuoteID { get; set; }

        public int IssueID { get; set; }

        public int SupplierID { get; set; }

        public decimal Price { get; set; }

        public DateTime EffectiveDate { get; set; }

        public virtual Supplier Supplier { get; set; }

        public SupplierQuoteDTO()
        {

        }

        public SupplierQuoteDTO(SupplierQuote supplierQuote)
        {
            this.QuoteID = supplierQuote.QuoteID;
            this.IssueID = supplierQuote.IssueID;
            this.SupplierID = supplierQuote.SupplierID;
            this.Price = supplierQuote.Price;
            this.EffectiveDate = supplierQuote.EffectiveDate;
            this.Supplier = supplierQuote.Supplier;
        }
    }
}