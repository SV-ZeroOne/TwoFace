using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Interfaces
{
   public interface IssueInterface : IRepository<Issue, int>
    {
        Issue findSpecificIssue(string issueTitle, Int16 seriesNumber);

        IEnumerable<Issue> Search(string searchstr);
    }
}
