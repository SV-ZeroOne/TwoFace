using ComicStock.Data;
using System;
using System.Collections.Generic;
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

        public string Title { get; set; }

        public DateTime PublicationDate { get; set; }

        public string Publisher { get; set; }

        public int SeriesNumber { get; set; }

        public string Description { get; set; }

        public virtual ICollection<Stock> Stock { get; set; }

        public virtual ICollection<Order> Orders { get; set; }

    }

}
