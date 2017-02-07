using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.Models
{
    public class IssueDTO
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public int SeriesNumber { get; set; }
        public string PublicationDate { get; set; }
        public int Publisher { get; set; }
        public List<StockDTO> Stock { get; set; }

        public static implicit operator List<object>(IssueDTO v)
        {
            throw new NotImplementedException();
        }
    }
}