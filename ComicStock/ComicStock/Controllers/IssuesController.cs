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

        //GET api/issues
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

        [Route("api/Issues/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10)
        {
            // Determine the number of records to skip
            int skip = (pageNo - 1) * pageSize;

            // Get total number of records

            int total = newIssues.Count();

            // Select the customers based on paging parameters
            List<IssueDTO> issues = newIssues
                .OrderBy(c => c.IssueID)
                .Skip(skip)
                .Take(pageSize)
                .ToList();

            // Return the list of customers
            return Ok(new PagedResult<IssueDTO>(issues, pageNo, pageSize, total));
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

        //public void Put(int id)
        //{
        //    //Need to have error handling!
        //    Issue issueToGet = issueRepo.GetById(id);
        //    IssueDTO issueDTO = new IssueDTO(issueToGet);
        //    Issue issueToUpdate = updateIssue(issueDTO);
        //    issueRepo.Update(issueToUpdate);

        //}

        //private Issue updateIssue(IssueDTO issue)
        //{
        //    Issue newIssue = new Issue();
        //    newIssue.IssueID = issue.IssueID;
        //    newIssue.Title = issue.Title;
        //    newIssue.Description = issue.Description;
        //    newIssue.PublicationDate = issue.PublicationDate;
        //    newIssue.Publisher = issue.Publisher;
        //    newIssue.SeriesNumber = issue.SeriesNumber;
        //    newIssue.IsDeleted = issue.IsDeleted;
        //    //Might need to map more fields to update.
        //    return newIssue;
        //}

        private Issue updateIssue(IssueDTO issue, Issue issueToUpdate)
        {

            issueToUpdate.Title = issue.Title;
            issueToUpdate.Description = issue.Description;
            issueToUpdate.PublicationDate = issue.PublicationDate;
            issueToUpdate.Publisher = issue.Publisher;
            issueToUpdate.SeriesNumber = issue.SeriesNumber;
            issueToUpdate.IsDeleted = issue.IsDeleted;
            //Might need to map more fields to update.
            return issueToUpdate;
        }

        public void Delete(int id)
        {
            Issue issueToDelete = issueRepo.GetById(id);

            if (issueToDelete == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            issueRepo.Delete(issueToDelete);
        }

        //public void Delete(IssueDTO issue)
        //{
        //    //Need to have error handling!
        //    var issueToDelete = issueRepo.GetById(issue.IssueID);
        //    issueRepo.Delete(issueToDelete);
        //}

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

        // Lamda Function
        //public IList<IssueDTO> Get(string search)
        //{
        //    return Get().Where(i => i.Title.Contains(search)).ToList<IssueDTO>();
        //}

        public IList<IssueDTO> Get(string search)
        {
            string searchString = search.ToLower();
            return Get().Where(i =>
            i.Title != null && i.Title.ToLower().Contains(searchString) ||
            i.SeriesNumber.ToString().Contains(search) ||
            i.IssueID.ToString().Contains(searchString) ||
            i.PublicationDate != null && i.PublicationDate.ToString().Contains(searchString) ||
            i.Publisher != null && i.Publisher.ToLower().Contains(searchString) 
            ).ToList<IssueDTO>();
        }

    }
}
