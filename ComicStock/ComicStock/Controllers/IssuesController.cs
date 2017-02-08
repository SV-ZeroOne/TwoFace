using ComicStock.Data.Implementations;
using ComicStock.Domain;
using ComicStock.Models;
using Newtonsoft.Json;
using System;
using System.Collections;
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
        IssuesRepo issueRepo;

        public IssuesController()
        {
            issueRepo = new IssuesRepo();
        }

        // GET api/issues
        public IEnumerable<IssueDTO> Get()
        {
            IEnumerable issueList = issueRepo.GetAll();
            IssueDTO[] issues = issueList.Cast<IssueDTO>().ToArray();
            return issues;
        }

        // GET api/issues/id
        public IssueDTO GetById(int id)
        {
            //Have to do some error handling here if id doesnt exist
            Issue someIssue = issueRepo.GetById(id);
            IssueDTO someIssueDTO = new IssueDTO(someIssue);
            return someIssueDTO;
        }

        // Lamda
        public IList<IssueDTO> Get(string search)
        {
            return Get().Where(i => i.Title.Contains(search)).ToList<IssueDTO>();
        }
    }
}
