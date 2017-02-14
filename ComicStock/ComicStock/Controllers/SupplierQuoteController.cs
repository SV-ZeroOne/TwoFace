using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ComicStock.WebAPI.Controllers
{
    public class SupplierQuoteController : ApiController
    {
        private readonly SupplierQuoteInterface quoteRepo;
        private List<SupplierQuoteDTO> newQuotes;

        public SupplierQuoteController(SupplierQuoteInterface quoteRepo)
        {
            this.quoteRepo = quoteRepo;

            newQuotes = new List<SupplierQuoteDTO>();
            foreach (SupplierQuote i in quoteRepo.GetAll())
            {
                SupplierQuoteDTO newQuote = new SupplierQuoteDTO(i);
                newQuotes.Add(newQuote);
            }
        }

        public IEnumerable<SupplierQuoteDTO> Get()
        {
            return newQuotes;
        }

        public IList<SupplierQuoteDTO> Get(int issueID)
        {
            return Get().Where(q => q.IssueID.Equals(issueID)).ToList<SupplierQuoteDTO>();
        }

    }
}
