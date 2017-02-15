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

        public SuppliersController(SupplierInterface supplierRepo)
        {
            this.supplierRepo = supplierRepo;
            IEnumerable someSupplier = supplierRepo.GetAll();
            newSuppliers = new List<SupplierDTO>();
            foreach (Supplier i in someSupplier)
            {
                SupplierDTO newSupplier = new SupplierDTO(i);
                newSuppliers.Add(newSupplier);

            }
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
            return Get().Where(i => i.Name.Contains(search)).ToList<SupplierDTO>();
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
        public Supplier Post(SupplierDTO supplierDto)
        {
            if (supplierDto == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.NotAcceptable);
            }
            Supplier newSupplier = convertDTO(supplierDto);
            supplierRepo.Add(newSupplier);
            return newSupplier;
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
    }
}
