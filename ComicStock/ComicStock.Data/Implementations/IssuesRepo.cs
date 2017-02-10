using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
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
    }
}
