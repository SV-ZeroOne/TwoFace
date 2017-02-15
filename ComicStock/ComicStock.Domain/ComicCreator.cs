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
    public partial class ComicCreator : IEntity<int>
    {
        public ComicCreator()
        {
        }

        [Key, Column(Order = 0)]
        public int IssueID { get; set; }

        [Key, Column(Order = 1)]
        public int CreatorID { get; set; }

        [MaxLength(50)]
        public string CreatorRole { get; set; }

        public Boolean IsDeleted { get; set; }

        public virtual Issue Issue { get; set; }

        public virtual Creator Creator { get; set; }

        public bool isDeleted => IsDeleted;
       
    }
}
