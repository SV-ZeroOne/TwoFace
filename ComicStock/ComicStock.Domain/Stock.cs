using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    [Table("Stock")]
    public partial class Stock : IEntity<int>
    {
        public Stock()
        {

        }

        [Key]
        public int StockReferenceID { get; set; }

        public int IssueID { get; set; }

        [MaxLength(10)]
        public string Condition { get; set; }

        public Int16 AvailableQty { get; set; }

        public decimal Price { get; set; }

        public Boolean IsDeleted { get; set; }     

        public bool isDeleted => IsDeleted;

    }
}
