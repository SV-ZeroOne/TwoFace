using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using ComicStock.WebAPI.Controllers;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace ComicStock.Controllers
{
    public class CreatorsController : ApiController
    {
        private readonly CreatorInterface creatorRepo;
        private List<CreatorDTO> newCreators;

        public CreatorsController(CreatorInterface creatorRepo)
        {
            this.creatorRepo = creatorRepo;
            newCreators = new List<CreatorDTO>();

        }

        // GET api/creators
        public IEnumerable<CreatorDTO> Get()
        {
            return newCreators;
        }

        // GET api/creators/id
        public CreatorDTO GetById(int id)
        {
            Creator someCreator = creatorRepo.GetById(id);
            if (someCreator == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            CreatorDTO someCreatorDTO = new CreatorDTO(someCreator);
            return someCreatorDTO;
        }

        [Route("api/Creators/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10)
        {
            int skip = (pageNo - 1) * pageSize;

            int total = creatorRepo.recordCount();
            IEnumerable<Creator> creator = creatorRepo.Paging(pageNo, pageSize);
            IList<CreatorDTO> creatorDTO = new List<CreatorDTO>();

            foreach (var item in creator)
            {
                creatorDTO.Add(convertObject(item));
            }

            return Ok(new PagedResult<CreatorDTO>(creatorDTO, pageNo, pageSize, total));
        }

        private CreatorDTO convertObject(Creator creator)
        {
            CreatorDTO newCreator = new CreatorDTO();
            newCreator.CreatorID = creator.CreatorID;
            newCreator.Name = creator.Name;
            newCreator.CountryOfResidence = creator.CountryOfResidence;
            newCreator.TaxReferenceNumber = creator.TaxReferenceNumber;
            newCreator.EmailAddress = creator.EmailAddress;
            return newCreator;
        }

        // POST api/creators/ ~ Works
        [Route("api/Creators/")]
        [HttpPost]
        public CreatorDTO Post(CreatorDTO item)
        {
            if (item == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.NotAcceptable);
            }

            Creator creator = convertDtoToObject(item);

            creatorRepo.Add(creator);

            return item;
        }

        // PUT api/Creators ~ Works
        [Route("api/Creators/")]
        [HttpPut]
        public CreatorDTO Put([FromBody] CreatorDTO creator)
        {
            Creator creatorItem = creatorRepo.GetById(creator.CreatorID);
            if (creatorItem == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.BadRequest);   
            }
            var creatorItemUpdate = updateCreator(creator, creatorItem);
            creatorRepo.Update(creatorItemUpdate);
            return creator;
        }

        // DELETE api/creators/id ~ Works
        public void Delete(int id)
        {
            Creator item = creatorRepo.GetById(id);
            if (item == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            creatorRepo.Delete(item);
        }

        private Creator convertDtoToObject(CreatorDTO creatorDTO)
        {
            Creator tempCreator = new Creator();
            tempCreator.Name = creatorDTO.Name;
            tempCreator.CountryOfResidence = creatorDTO.CountryOfResidence;
            tempCreator.TaxReferenceNumber = creatorDTO.TaxReferenceNumber;
            tempCreator.EmailAddress = creatorDTO.EmailAddress;
            return tempCreator;
        }

        public Creator updateCreator(CreatorDTO creatorDTO, Creator creator)
        {
            creator.CreatorID = creatorDTO.CreatorID;
            creator.Name = creatorDTO.Name;
            creator.CountryOfResidence = creatorDTO.CountryOfResidence;
            creator.TaxReferenceNumber = creatorDTO.TaxReferenceNumber;
            creator.EmailAddress = creatorDTO.EmailAddress;
            creator.IsDeleted = creatorDTO.IsDeleted;
            return creator;
        }

        public IList<CreatorDTO> Get(string search)
        {
            string searchString = search.ToLower();
            return Get().Where(i =>
            i.Name != null && i.Name.ToLower().Contains(searchString) ||
            i.CountryOfResidence.ToLower().Contains(searchString) ||
            i.TaxReferenceNumber.ToString().Contains(searchString) ||
            i.EmailAddress != null && i.EmailAddress.ToLower().Contains(searchString) 
            ).ToList<CreatorDTO>();
        }

        [Route("api/Creators/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        {
            if (searchKey != null)
            {
                string searchString = searchKey.ToLower();
                IEnumerable<CreatorDTO> someStock = Get().Where(i =>
                i.Name != null && i.Name.ToLower().Contains(searchString) ||
                i.CountryOfResidence.ToLower().Contains(searchString) ||
                i.TaxReferenceNumber.ToString().Contains(searchString) ||
                i.EmailAddress != null && i.EmailAddress.ToLower().Contains(searchString));
                int pageSize = 20;

                int skip = (pageNumber - 1) * pageSize;

                int total = someStock.Count();

                List<CreatorDTO> stock = someStock
                    .OrderBy(c => c.CreatorID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                return Ok(new PagedResult<CreatorDTO>(stock, pageNumber, pageSize, total));
            }
            return null;
        }

    }
}
