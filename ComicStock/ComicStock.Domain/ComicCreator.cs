using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    class ComicCreator
    {
        public ComicCreator()
        {
        }

        public int IssueID { get; set; }

        public int CreatorID { get; set; }

        [MaxLength(50)]
        public string CreatorRole { get; set; }

    }
}
