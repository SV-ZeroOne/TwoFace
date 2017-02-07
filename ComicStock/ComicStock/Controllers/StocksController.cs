using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ComicStock.Controllers
{
    public class StocksController : ApiController
    {
	    public int Id { get; set; }
        public int Condition { get; set; }
        public int AvailableQuantity { get; set; }
        public double Price { get; set; }
    }
}
