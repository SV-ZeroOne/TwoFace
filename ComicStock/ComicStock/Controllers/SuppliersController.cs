using ComicStock.API;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.WebAPI.Models;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace ComicStock.WebAPI.Controllers
{
    public class SuppliersController : ApiController
    {
        private readonly SupplierInterface supplierRepo;
        private List<SupplierDTO> newSuppliers;

        private readonly SupplierService supplierService;

        private static Random random = new Random();

        public SuppliersController(SupplierInterface supplierRepo, SupplierService supplierService)
        {
            this.supplierRepo = supplierRepo;
            this.supplierService = supplierService;
            IEnumerable someSupplier = supplierRepo.GetAll();
            newSuppliers = new List<SupplierDTO>();
            foreach (Supplier i in someSupplier)
            {
                SupplierDTO newSupplier = new SupplierDTO(i);
                newSuppliers.Add(newSupplier);

            }
        }

        public static string RandomString(int length)
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            return new string(Enumerable.Repeat(chars, length)
              .Select(s => s[random.Next(s.Length)]).ToArray());
        }

        [Route("api/Suppliers/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10)
        {
            // Determine the number of records to skip
            int skip = (pageNo - 1) * pageSize;

            // Get total number of records

            int total = newSuppliers.Count();

            // Select the customers based on paging parameters
            List<SupplierDTO> suppliers = newSuppliers
                .OrderBy(c => c.SupplierID)
                .Skip(skip)
                .Take(pageSize)
                .ToList();

            // Return the list of customers
            return Ok(new PagedResult<SupplierDTO>(suppliers, pageNo, pageSize, total));
        }

        // GET api/suppliers
        public IEnumerable<SupplierDTO> Get()
        {
            return newSuppliers;
        }

        // GET api/suppliers/id
        public SupplierDTO GetById(int id)
        {
            Supplier someSupplier = supplierRepo.GetById(id);
            if (someSupplier == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            SupplierDTO someSupplierDTO = new SupplierDTO(someSupplier);
            return someSupplierDTO;
        }

        // Lamda
        public IList<SupplierDTO> Get(string search)
        {
            string searchString = search.ToLower();
            return Get().Where(i =>
            i.SupplierID.ToString().Contains(searchString) ||
            i.Name != null && i.Name.ToLower().Contains(searchString) ||
            i.City != null && i.City.ToLower().Contains(searchString) ||
            i.ReferenceNumber != null && i.ReferenceNumber.ToLower().Contains(searchString)
            ).ToList<SupplierDTO>();
        }

        //PUT api/suppliers
        public SupplierDTO Put(SupplierDTO supplier)
        {
            //Need to have error handling!
            Supplier supplierToGet = supplierRepo.GetById(supplier.SupplierID);
            var supplierToUpdate = updateSupplier(supplier, supplierToGet);
            supplierRepo.Update(supplierToUpdate);
            return supplier;
        }

        [Route("api/Suppliers/PlaceSupplier")]
        public void PlaceSupplier(string name, string city)
        {
            string codeString = RandomString(15);
            bool generating = true;

            IEnumerable<SupplierDTO> suppliers = Get();
            while (generating)
            {
                foreach (SupplierDTO item in suppliers)
                {
                    if (item.ReferenceNumber.ToLower() == codeString.ToLower())
                    {
                        codeString = RandomString(15);
                        generating = true;
                    }
                    else
                    {
                        generating = false;
                    }
                }
            }

            supplierService.placeSupplier(name, city, codeString);
        }

        private Supplier updateSupplier(SupplierDTO supplier, Supplier supplierToUpdate)
        {
            supplierToUpdate.Name = supplier.Name;
            supplierToUpdate.City = supplier.City;
            supplierToUpdate.ReferenceNumber = supplier.ReferenceNumber;
            //Might need to map more fields to update.
            return supplierToUpdate;
        }

        //DELETE api/issues/id
        public void Delete(SupplierDTO supplier)
        {
            //Need to have error handling!
            var supplierToDelete = supplierRepo.GetById(supplier.SupplierID);
            supplierRepo.Delete(supplierToDelete);
        }

        //POST api/issues
        //public Supplier Post(SupplierDTO supplierDto)
        //{
        //    if (supplierDto == null)
        //    {
        //        return null;
        //        throw new HttpResponseException(HttpStatusCode.NotAcceptable);
        //    }
        //    Supplier newSupplier = convertDTO(supplierDto);
        //    supplierRepo.Add(newSupplier);
        //    return newSupplier;
        //}

        private Supplier convertDTO(SupplierDTO supplierDto)
        {
            Supplier newSupplier = new Supplier();
            newSupplier.Name = supplierDto.Name;
            newSupplier.City = supplierDto.City;
            newSupplier.ReferenceNumber = supplierDto.ReferenceNumber;
            return newSupplier;
        }
    }
}
