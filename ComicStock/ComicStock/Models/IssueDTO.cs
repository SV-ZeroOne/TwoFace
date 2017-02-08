using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.Models
{
    public class IssueDTO
    {

        public int Id { get; set; }

        public String Title { get; set; }

        public String Description { get; set; }

        public int SeriesNumber { get; set; }

        public DateTime PublicationDate { get; set; }

        public StockDTO[] Stock { get; set; }
    }
}