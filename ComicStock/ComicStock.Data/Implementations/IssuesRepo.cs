using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Implementations
{
    internal class IssuesRepo : EFRepository<Issue, int>, IssueInterface
    {
        private readonly SquareEyesContext dbContext = new SquareEyesContext();
        public Issue findSpecificIssue(string issueTitle, short seriesNumber)
        {
            var theIssue = dbContext.Issues.SqlQuery("SELECT * FROM Issues WHERE Title = @title AND SeriesNumber = @seriesnum",
                new SqlParameter("title", issueTitle),
                new SqlParameter("seriesnum", seriesNumber)).FirstOrDefault<Issue>();
            return theIssue;
        }

        public IEnumerable<Issue> Search(string searchstr)
        {
                var query =
                    from issue in dbContext.Issues
                    where
                        (issue.Title != null && issue.Title.Contains(searchstr)) ||
                        (issue.Description != null && issue.Description.Contains(searchstr)) ||
                        (issue.SeriesNumber != null && issue.SeriesNumber.ToString().Contains(searchstr)) ||
                        (issue.Publisher != null && issue.Publisher.Contains(searchstr))
                    select issue;

                return query.AsEnumerable();
        }
    }
}
