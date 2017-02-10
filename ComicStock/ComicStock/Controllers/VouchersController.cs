using ComicStock.Data.Interfaces;
using ComicStock.WebAPI.Models;
using ComicStock.Domain;
using ComicStock.Data.Implementations;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Collections;

namespace ComicStock.WebAPI.Controllers
{
    public class VouchersController : ApiController
    {

        private readonly VoucherInterface voucherRepo;
        private List<VoucherDTO> newVouchers;

        public VouchersController(VoucherInterface voucherRepo)
        {
            this.voucherRepo = voucherRepo;
            IEnumerable someVoucher = voucherRepo.GetAll();
            newVouchers = new List<VoucherDTO>();
            foreach (Voucher i in someVoucher)
            {
                VoucherDTO newVoucher = new VoucherDTO(i);
                newVouchers.Add(newVoucher);

            }
        }

        // GET api/vouchers
        public IEnumerable<VoucherDTO> Get()
        {
            return newVouchers;
        }

        // GET api/vouchers/id
        public VoucherDTO GetById(int id)
        {
            //Have to do some error handling here if id doesnt exist
            Voucher someVoucher = voucherRepo.GetById(id);
            if (someVoucher == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            VoucherDTO someVoucherDTO = new VoucherDTO(someVoucher);
            return someVoucherDTO;
        }

        // POST api/creators/ 
        public VoucherDTO Post(VoucherDTO item)
        {
            Voucher voucher = convertDtoToObject(item);

            if (voucher == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            voucherRepo.Add(voucher);

            return item;
        }

        // PUT api/vouchers/id ~ Works
        public void PutCreator(VoucherDTO item)
        {
            //CreatorDTO CreatorDTO = GetById(id);
            Voucher voucherItem = voucherRepo.GetById(item.VoucherID);

            //newCreators.Find(creator);
            if (voucherItem == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            var voucherItemUpdate = updateVoucher(voucherItem, item);

            voucherRepo.Update(voucherItemUpdate);

        }

        // DELETE api/creators/id ~ Works
        public void DeleteVoucher(int id)
        {
            Voucher item = voucherRepo.GetById(id);
            if (item == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            voucherRepo.Delete(item);
        }

        private Voucher convertDtoToObject(VoucherDTO voucherDTO)
        {
            Voucher tempVoucher = new Voucher();
            tempVoucher.VoucherID = voucherDTO.VoucherID;
            tempVoucher.Amount = voucherDTO.Amount;
            tempVoucher.Code = voucherDTO.Code;
            tempVoucher.DateIssued = voucherDTO.DateIssued;
            tempVoucher.Valid = voucherDTO.Valid;
            return tempVoucher;
        }

        public Voucher updateVoucher(Voucher voucher, VoucherDTO voucherDTO)
        {
            voucher.VoucherID = voucherDTO.VoucherID;
            voucher.Amount = voucherDTO.Amount;
            voucher.Code = voucherDTO.Code;
            voucher.DateIssued = voucherDTO.DateIssued;
            voucher.Valid = voucherDTO.Valid;
            return voucher;
        }
    }
}
