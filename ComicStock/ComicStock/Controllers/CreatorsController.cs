using ComicStock.Data;
using ComicStock.Data.Implementations;
using ComicStock.Domain;
using ComicStock.Data.Interfaces;
using ComicStock.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Collections;

namespace ComicStock.Controllers
{
    public class CreatorsController : ApiController
    {
        private readonly CreatorInterface creatorRepo;
        private List<CreatorDTO> newCreator;

        public CreatorsController(CreatorInterface creatorRepo) 
        {
            this.creatorRepo = creatorRepo;
         
          
            IEnumerable someCreator = creatorRepo.GetAll();
            newCreator = new List<CreatorDTO>();
            foreach (Creator i in someCreator)
            {
                CreatorDTO newCreator = new CreatorDTO(i);
                newCreator.Add(newCreator);

            }
        }

        // GET api/creators
        public IEnumerable<CreatorDTO> Get()
        {
            return newCreator;
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

        // Lamda
        /*public IList<CreatorDTO> Get(string search)
        {
            return Get().Where(i => i.Title.Contains(search)).ToList<CreatorDTO>();
        }*/
    }
}
