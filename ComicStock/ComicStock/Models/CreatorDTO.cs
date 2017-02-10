using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.Models
{
    public class CreatorDTO
    {
        public int CreatorID { get; set; }

        public string Name { get; set; }

        public string CountryOfResidence { get; set; }

        public Byte[] TaxReferenceNumber { get; set; }

        public string EmailAddress { get; set; }

        public CreatorDTO()
        {

        }

        public CreatorDTO(Creator creator)
        {
            this.CreatorID = creator.CreatorID;
            this.Name = creator.Name;
            this.CountryOfResidence = creator.CountryOfResidence;
            this.TaxReferenceNumber = creator.TaxReferenceNumber;
            this.EmailAddress = creator.EmailAddress;
        }
    }
}