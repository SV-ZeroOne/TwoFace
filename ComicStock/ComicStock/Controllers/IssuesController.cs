using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace ComicStock.WebAPI.Controllers
{
    public class IssuesController : ApiController
    {
        private readonly IssueInterface issueRepo;
        private List<IssueDTO> newIssues;

        public IssuesController(IssueInterface issueRepo)
        {
            this.issueRepo = issueRepo;
            newIssues = new List<IssueDTO>();
        }

        //GET api/issues
        public IEnumerable<IssueDTO> Get()
        {
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
            int skip = (pageNo - 1) * pageSize;

            int total = issueRepo.recordCount();
            IEnumerable<Issue> issues = issueRepo.Paging(pageNo, pageSize);
            IList<IssueDTO> issueDTO = new List<IssueDTO>();

            foreach (var item in issues)
            {
                issueDTO.Add(convertObject(item));
            }

            return Ok(new PagedResult<IssueDTO>(issueDTO, pageNo, pageSize, total));
        }

        private IssueDTO convertObject(Issue issue)
        {
            IssueDTO newIssue = new IssueDTO();
            newIssue.Title = issue.Title;
            newIssue.IssueID = issue.IssueID;
            newIssue.PublicationDate = issue.PublicationDate;
            newIssue.Publisher = issue.Publisher;
            newIssue.SeriesNumber = issue.SeriesNumber;
            newIssue.Description = issue.Description;
            return newIssue;
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
            Issue issueToGet = issueRepo.GetById(issue.IssueID);
            if (issueToGet == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
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
            issueToUpdate.IsDeleted = issue.IsDeleted;
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

        [Route("api/Issues/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        {
            if(searchKey != null)
            {
                string searchString = searchKey.ToLower();
                IEnumerable<IssueDTO> someIssues = Get().Where(i => i.Title != null && i.Title.ToLower().Contains(searchString) ||
                i.SeriesNumber.ToString().Contains(searchKey) ||
                i.IssueID.ToString().Contains(searchString) ||
                i.PublicationDate != null && i.PublicationDate.ToString().Contains(searchString) ||
                i.Publisher != null && i.Publisher.ToLower().Contains(searchString));
                int pageSize = 20;

                int skip = (pageNumber - 1) * pageSize;

                int total = someIssues.Count();

                List<IssueDTO> issues = someIssues
                    .OrderBy(c => c.IssueID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                return Ok(new PagedResult<IssueDTO>(issues, pageNumber, pageSize, total));
            }
            return null;
        }


    }
}
