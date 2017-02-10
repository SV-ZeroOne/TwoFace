using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
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

        // POST api/creators/ ~ Works
        public CreatorDTO Post(CreatorDTO item)
        {
            Creator creator = convertDtoToObject(item);

            if (creator == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

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

        // PUT api/vouchers/id ~ Works
        public void PutCreator(CreatorDTO item)
        {
            //CreatorDTO CreatorDTO = GetById(id);
            Creator creatorItem = creatorRepo.GetById(item.CreatorID);

            //newCreators.Find(creator);
            if (creatorItem == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            var creatorItemUpdate = updateCreator(creatorItem, item);

            creatorRepo.Update(creatorItemUpdate);

        }

        // DELETE api/creators/id ~ Works
        public void DeleteCreator(int id)
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

        public Creator updateCreator(Creator creator, CreatorDTO creatorDTO)
        {
            creator.CreatorID = creatorDTO.CreatorID;
            creator.Name = creatorDTO.Name;
            creator.CountryOfResidence = creatorDTO.CountryOfResidence;
            creator.TaxReferenceNumber = creatorDTO.TaxReferenceNumber;
            creator.EmailAddress = creatorDTO.EmailAddress;
            return creator;
        }
    }
    }
