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
        public IssuesController()
        {
           
        }

        // GET api/issues
        public IEnumerable<IssueDTO> Get()
        {
            string jsonFile = AppDomain.CurrentDomain.GetData("DataDirectory").ToString() + "\\Issues.json";
            String issuesString = System.IO.File.ReadAllText(jsonFile);
            IssueDTO[] issues = JsonConvert.DeserializeObject<IssueDTO[]>(issuesString);
            return issues;
        }

        // GET api/issues/id
        public IssueDTO GetById(int id)
        {
            string jsonFile = AppDomain.CurrentDomain.GetData("DataDirectory").ToString() + "\\Issues.json";
            String issuesString = System.IO.File.ReadAllText(jsonFile);
            IssueDTO[] issues = JsonConvert.DeserializeObject<IssueDTO[]>(issuesString);
            foreach (var item in issues)
            {
                if (item.Id == id)
                {
                    return item;
                }
            }
            return null;
        }

        // Lamda
        public IList<IssueDTO> Get(string search)
        {
            return Get().Where(i => i.Title.Contains(search)).ToList<IssueDTO>();
        }
    }
}
