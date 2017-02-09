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

namespace ComicStock.WebAPI.Controllers
{
    public class IssuesController : ApiController
    {
        private readonly IssueInterface issueRepo;
        private List<IssueDTO> newIssues;

        public IssuesController(IssueInterface issueRepo)
        {
            this.issueRepo = issueRepo;
            //issueRepo = new IssuesRepo();
            //IEnumerable someIssues = issueRepo.GetAll();
            newIssues = new List<IssueDTO>();
            foreach (Issue i in issueRepo.GetAll())
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
            Issue newIssue = convertDTO(issueDto);
            issueRepo.Add(newIssue);
            return newIssue;
        }

        public IssueDTO Put(IssueDTO issue)
        {
            //Need to have error handling!
            Issue issueToGet = issueRepo.GetById(issue.IssueID);
            var issueToUpdate = updateIssue(issue, issueToGet);
            issueRepo.Update(issueToUpdate);
            return issue;
        }

        private Issue updateIssue(IssueDTO issue, Issue issueToUpdate)
        {
            issueToUpdate.Title = issue.Title;
            issueToUpdate.Description = issue.Description;
            issueToUpdate.PublicationDate = issue.PublicationDate;
            issueToUpdate.Publisher = issue.Publisher;
            issueToUpdate.SeriesNumber = issue.SeriesNumber;
            //Might need to map more fields to update.
            return issueToUpdate;
        }

        public void Delete(IssueDTO issue)
        {
            //Need to have error handling!
            var issueToDelete = issueRepo.GetById(issue.IssueID);
            issueRepo.Delete(issueToDelete);
        }

        private Issue convertDTO(IssueDTO issueDto)
        {
            Issue newIssue = new Issue();
            newIssue.Title = issueDto.Title;
            newIssue.PublicationDate = issueDto.PublicationDate;
            newIssue.Publisher = issueDto.Publisher;
            newIssue.SeriesNumber = issueDto.SeriesNumber;
            newIssue.Description = issueDto.Description;
            //newIssue.ComicCreator = issueDto.ComicCreator;
            return newIssue;
        }

    }
}
