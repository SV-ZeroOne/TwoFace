using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ComicStock.Models
{
    public class IssueDTO
    {

        public int IssueID { get; set; }

        public string Title { get; set; }

        public DateTime PublicationDate { get; set; }

        public string Publisher { get; set; }

        public Int16 SeriesNumber { get; set; }

        public string ImageURL { get; set; }

        public string Description { get; set; }

        public IssueDTO()
        {

        }

        public IssueDTO(Issue newIssue)
        {
            this.IssueID = newIssue.IssueID;
            this.Title = newIssue.Title;
            this.PublicationDate = newIssue.PublicationDate;
            this.Publisher = newIssue.Publisher;
            this.SeriesNumber = newIssue.SeriesNumber;
            this.Description = newIssue.Description;
        }
    }
}