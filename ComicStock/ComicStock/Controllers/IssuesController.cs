using ComicStock.Data;
using ComicStock.Data.Implementations;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;

namespace ComicStock.Controllers
{
    public class IssuesController : ApiController
    {
        private readonly IssueInterface issueRepo;
        private List<IssueDTO> newIssues;

        public IssuesController(IssueInterface issueRepo)
        {
            this.issueRepo = issueRepo;
            //issueRepo = new IssuesRepo();
            IEnumerable someIssues = issueRepo.GetAll();
            newIssues = new List<IssueDTO>();
            foreach(Issue i in someIssues)
            {
                IssueDTO newIssue = new IssueDTO(i);
                newIssues.Add(newIssue);

            }
        }

        // GET api/issues
        public IEnumerable<IssueDTO> Get()
        {
            //issueList = issueRepo.GetAll();
            //IssueDTO[] issues = issueList.Cast<IssueDTO>().ToArray();
            return newIssues;
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
