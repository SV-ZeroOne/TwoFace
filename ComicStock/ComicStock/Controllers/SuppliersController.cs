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
            int skip = (pageNo - 1) * pageSize;

            int total = supplierRepo.recordCount();

            IEnumerable<Supplier> suppliers = supplierRepo.Paging(pageNo, pageSize);
            IList<SupplierDTO> supplierDTO = new List<SupplierDTO>();

            foreach (var item in suppliers)
            {
                supplierDTO.Add(convertObject(item));
            }

            return Ok(new PagedResult<SupplierDTO>(supplierDTO, pageNo, pageSize, total));
        }

        private SupplierDTO convertObject(Supplier supplier)
        {
            SupplierDTO newSupplier = new SupplierDTO();
            newSupplier.SupplierID = supplier.SupplierID;
            newSupplier.Name = supplier.Name;
            newSupplier.City = supplier.City;
            newSupplier.ReferenceNumber = supplier.ReferenceNumber;
            return newSupplier;
        }

        // GET api/suppliers
        public IEnumerable<SupplierDTO> Get()
        {
            newSuppliers = new List<SupplierDTO>();
            foreach (var supplier in supplierRepo.GetAll())
            {
                newSuppliers.Add(convertObject(supplier));
            }
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
            Supplier supplierToGet = supplierRepo.GetById(supplier.SupplierID);
            if (supplierToGet == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
            var supplierToUpdate = updateSupplier(supplier, supplierToGet);
            if (supplierToUpdate == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
            supplierRepo.Update(supplierToUpdate);
            return supplier;
        }

        [Route("api/Suppliers/PlaceSupplier")]
        public void PlaceSupplier(string name, string city)
        {
            string codeString = RandomString(15);
            bool generating = true;

            IEnumerable<Supplier> suppliers = supplierRepo.GetAll();
            while (generating)
            {
                foreach (Supplier item in suppliers)
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
            supplierToUpdate.IsDeleted = supplier.IsDeleted;
            return supplierToUpdate;
        }

        //DELETE api/issues/id
        public void Delete(SupplierDTO supplier)
        {
            var supplierToDelete = supplierRepo.GetById(supplier.SupplierID);
            if (supplierToDelete == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
            supplierRepo.Delete(supplierToDelete);
        }

        private Supplier convertDTO(SupplierDTO supplierDto)
        {
            Supplier newSupplier = new Supplier();
            newSupplier.Name = supplierDto.Name;
            newSupplier.City = supplierDto.City;
            newSupplier.ReferenceNumber = supplierDto.ReferenceNumber;
            newSupplier.IsDeleted = supplierDto.IsDeleted;
            return newSupplier;
        }

        [Route("api/Suppliers/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        {
            if (searchKey != null)
            {
                string searchString = searchKey.ToLower();
                IEnumerable<SupplierDTO> someStock = Get().Where(i => i.SupplierID.ToString().Contains(searchString) ||
                i.Name != null && i.Name.ToLower().Contains(searchString) ||
                i.City != null && i.City.ToLower().Contains(searchString) ||
                i.ReferenceNumber != null && i.ReferenceNumber.ToLower().Contains(searchString));
                int pageSize = 20;
                int skip = (pageNumber - 1) * pageSize;

                int total = someStock.Count();

                List<SupplierDTO> stock = someStock
                    .OrderBy(c => c.SupplierID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                return Ok(new PagedResult<SupplierDTO>(stock, pageNumber, pageSize, total));
            }
            return null;
        }

    }
}
