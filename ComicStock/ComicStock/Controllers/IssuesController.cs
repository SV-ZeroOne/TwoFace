using ComicStock.Data;
using ComicStock.Data.Implementations;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System;

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
            Issue someIssue = issueRepo.GetById(id);
            if (someIssue == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            IssueDTO someIssueDTO = new IssueDTO(someIssue);   
            return someIssueDTO;
        }

        // Lamda Function
        public IList<IssueDTO> Get(string search)
        {
            return Get().Where(i => i.Title.Contains(search)).ToList<IssueDTO>();
        }

        public Issue Post(IssueDTO issueDto)
        {
            if (issueDto == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.NotAcceptable);
            }
            return convertDTO(issueDto);
        }

        public void Put(IssueDTO issue)
        {

        }

        private Issue convertDTO(IssueDTO issueDto)
        {
            Issue newIssue = new Issue();
            newIssue.Title = issueDto.Title;
            newIssue.PublicationDate = issueDto.PublicationDate;
            newIssue.Publisher = issueDto.Publisher;
            newIssue.SeriesNumber = issueDto.SeriesNumber;
            newIssue.Description = issueDto.Description;
            return newIssue;
        }

    }
}
