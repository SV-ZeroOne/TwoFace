using ComicStock.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ComicStock.Controllers
{
    public class IssuesController : ApiController
    {
        public IEnumerable<IssueDTO> list;

        public IEnumerable<IssueDTO> readJson()
        {
            string json = File.ReadAllText(@"C:\Users\mpho.mahase\Documents\Graduate Bootcamp 2017\DotNet projects\ComicStock\ComicStock\App_Data\Issues.json");
            var issueList = JsonConvert.DeserializeObject<IEnumerable<Models.IssueDTO>>(json);
            return issueList;
        }
      
       
        public IEnumerable<IssueDTO> GetAllIssues()
        {
            list = readJson();
            return list;
        }

        public IssueDTO GetIssuesById(int ID)
        {
            IEnumerable<IssueDTO> issues = GetAllIssues();
            return issues.FirstOrDefault(x => x.Id == ID);
        }

        public IEnumerable<IssueDTO> GetSearch(String title)
        {
            IEnumerable<IssueDTO> issues = GetAllIssues();
            return issues.Where(x => x.Title.Equals(title, StringComparison.OrdinalIgnoreCase));
        }
    }
}
