using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class Stock : IEntity<int>
    {   
        [Key]
        public int StockReferenceID { get; set; }

        public ICollection<Issue> IssuesID { get; set; }

        public string Condition { get; set; }

        public int AvailableQty { get; set; }

        public decimal Price { get; set; }

    }
}
