using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{   
    public partial class Issue : IEntity<int>
    {
        public Issue()
        {

        }

        public int IssueID { get; set; }

        [MaxLength(500)]
        public string Title { get; set; }

        public DateTime PublicationDate { get; set; }

        [MaxLength(50)]
        public string Publisher { get; set; }

        public Int16 SeriesNumber { get; set; }

        [MaxLength(300)]
        public string ImageURL { get; set; }

        public string Description { get; set; }

        public virtual ICollection<Stock> Stock { get; set; }

        public virtual ICollection<Order> Orders { get; set; }

    }

}
