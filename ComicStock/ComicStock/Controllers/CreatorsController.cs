using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using ComicStock.WebAPI.Controllers;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
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

            IEnumerable someCreator = creatorRepo.GetAll();

            newCreators = new List<CreatorDTO>();

            foreach (Creator i in someCreator)
            {
                CreatorDTO newCreator = new CreatorDTO(i);
                newCreators.Add(newCreator);
            }

        }

        // GET api/creators
        public IEnumerable<CreatorDTO> Get()
        {
            return newCreators;
        }

        // GET api/creators/id
        public CreatorDTO GetById(int id)
        {
            //Have to do some error handling here if id doesnt exist
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
            // Determine the number of records to skip
            int skip = (pageNo - 1) * pageSize;

            // Get total number of records

            int total = newCreators.Count();

            // Select the customers based on paging parameters
            List<CreatorDTO> creators = newCreators
                .OrderBy(c => c.CreatorID)
                .Skip(skip)
                .Take(pageSize)
                .ToList();

            // Return the list of customers
            return Ok(new PagedResult<CreatorDTO>(creators, pageNo, pageSize, total));
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


        // POST api/creators/
        /*public CreatorDTO PostCreator(Creator item)
        {
            CreatorDTO newCreator = new CreatorDTO(item);
            if (newCreator == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotImplemented);
            }
            creatorRepo.Add(item);
            //var response = Request.CreateResponse<newVoucher>(HttpStatusCode.Created, item);
            return newCreator;
        }*/

        // PUT api/Creators ~ Works
        [Route("api/Creators/")]
        [HttpPut]
        public CreatorDTO Put([FromBody] CreatorDTO creator)
        {
            //CreatorDTO CreatorDTO = GetById(id);
            Creator creatorItem = creatorRepo.GetById(creator.CreatorID);
            var creatorItemUpdate = updateCreator(creator, creatorItem);
            creatorRepo.Update(creatorItemUpdate);
            return creator;
        }

        // PUT api/vouchers/id ~ Works
        //public void PutCreator(CreatorDTO item)
        //{
        //    //CreatorDTO CreatorDTO = GetById(id);
        //    Creator creatorItem = creatorRepo.GetById(item.CreatorID);

        //    //newCreators.Find(creator);
        //    if (creatorItem == null)
        //    {
        //        throw new HttpResponseException(HttpStatusCode.NotFound);
        //    }

        //    var creatorItemUpdate = updateCreator(creatorItem, item);

        //    creatorRepo.Update(creatorItemUpdate);

        //}

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
            //tempCreator.CreatorID = creatorDTO.CreatorID;
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
                // Determine the number of records to skip
                int skip = (pageNumber - 1) * pageSize;

                // Get total number of records

                int total = someStock.Count();

                // Select the customers based on paging parameters
                List<CreatorDTO> stock = someStock
                    .OrderBy(c => c.CreatorID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                // Return the list of customers
                return Ok(new PagedResult<CreatorDTO>(stock, pageNumber, pageSize, total));
            }
            return null;
        }

    }
}
