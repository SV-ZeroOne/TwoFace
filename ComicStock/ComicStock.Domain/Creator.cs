using ComicStock.Data;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Domain
{
    public partial class Creator : IEntity<int>
    {
        public Creator()
        {
        }

        public int CreatorID { get; set; }

        [MaxLength(50)]
        public string Name { get; set; }

        [MaxLength(25)]
        public string CountryOfResidence { get; set; }

        [MaxLength(512)]
        public Byte[] TaxReferenceNumber { get; set; }

        [MaxLength(256)]
        public string EmailAddress { get; set; }

        public Boolean IsDeleted { get; set; }

        public bool isDeleted => IsDeleted;

        public virtual ICollection<ComicCreator> ComicCreator { get;  set; }

    }
}
