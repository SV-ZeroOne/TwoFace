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
using System.Web.Http.Cors;
using ComicStock.API;

namespace ComicStock.WebAPI.Controllers
{
    //[EnableCors(origins: "http://localhost:49511", headers: "*", methods: "*")]
    public class VouchersController : ApiController
    {
        private readonly VoucherInterface voucherRepo;
        private List<VoucherDTO> newVouchers;

        private readonly VoucherService voucherService;

        private static Random random = new Random();

        public VouchersController(VoucherInterface voucherRepo, VoucherService voucherService)
        {
            this.voucherService = voucherService;
            this.voucherRepo = voucherRepo;
            IEnumerable someVoucher = voucherRepo.GetAll();
            newVouchers = new List<VoucherDTO>();
            foreach (Voucher i in someVoucher)
            {
                VoucherDTO newVoucher = new VoucherDTO(i);
                newVouchers.Add(newVoucher);
            }
        }

        public static string RandomString(int length)
        {
            const string chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            return new string(Enumerable.Repeat(chars, length)
              .Select(s => s[random.Next(s.Length)]).ToArray());
        }

        public IList<VoucherDTO> Get(string search)
        {
            string searchString = search.ToLower();
            return Get().Where(i =>
            i.VoucherID.ToString().Contains(searchString) ||
            i.Amount.ToString().Contains(searchString) ||
            i.Code != null && i.Code.ToLower().Contains(searchString) ||
            i.DateIssued != null && i.DateIssued.ToString().Contains(searchString)
            ).ToList<VoucherDTO>();
        }

        [Route("api/Vouchers/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10)
        {
            // Determine the number of records to skip
            int skip = (pageNo - 1) * pageSize;

            // Get total number of records

            int total = newVouchers.Count();

            // Select the customers based on paging parameters
            List<VoucherDTO> vouchers = newVouchers
                .OrderBy(c => c.VoucherID)
                .Skip(skip)
                .Take(pageSize)
                .ToList();

            // Return the list of customers
            return Ok(new PagedResult<VoucherDTO>(vouchers, pageNo, pageSize, total));
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

        //// POST api/vouchers/ 
        //public Voucher Post(VoucherDTO item)
        //{
        //    Voucher voucher = convertDtoToObject(item);

        //    if (voucher == null)
        //    {
        //        throw new HttpResponseException(HttpStatusCode.NotFound);
        //    }

        //    voucherRepo.Add(voucher);

        //    return voucher;
        //}

        [Route("api/Vouchers/PlaceVoucher")]
        public void PlaceVoucher(decimal amount, bool valid)
        {
            if (amount < 0)
            {
                HttpResponseMessage message = new HttpResponseMessage(HttpStatusCode.NotImplemented);
                message.Content = new StringContent("You entered a invalid Voucher Amount");
                throw new HttpResponseException(message);
            }
            else
            {
                string codeString = RandomString(10);
                bool generating = true;

                IEnumerable<VoucherDTO> vouchers = Get();
                while (generating)
                {
                    foreach (VoucherDTO item in vouchers)
                    {
                        if (item.Code.ToLower() == codeString.ToLower())
                        {
                            codeString = RandomString(10);
                            generating = true;
                        }
                        else
                        {
                            generating = false;
                        }
                    }
                }
                
                voucherService.placeVoucher(amount, codeString, valid);
            }
        }

        // PUT api/vouchers/id ~ Works
        public VoucherDTO PutVoucher(VoucherDTO item)
        {
            Voucher voucherItem = voucherRepo.GetById(item.VoucherID);

            if (voucherItem == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }

            var voucherItemUpdate = updateVoucher(voucherItem, item);

            voucherRepo.Update(voucherItemUpdate);

            return item;

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

        [Route("api/Vouchers/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        {
            if (searchKey != null)
            {
                string searchString = searchKey.ToLower();
                IEnumerable<VoucherDTO> someVouch = Get().Where(i => i.VoucherID.ToString().Contains(searchString) ||
                i.Amount.ToString().Contains(searchString) ||
                i.Code != null && i.Code.ToLower().Contains(searchString) ||
                i.DateIssued != null && i.DateIssued.ToString().Contains(searchString));
                int pageSize = 20;
                // Determine the number of records to skip
                int skip = (pageNumber - 1) * pageSize;

                // Get total number of records

                int total = someVouch.Count();

                // Select the customers based on paging parameters
                List<VoucherDTO> voucher = someVouch
                    .OrderBy(c => c.VoucherID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                // Return the list of customers
                return Ok(new PagedResult<VoucherDTO>(voucher, pageNumber, pageSize, total));
            }
            return null;
        }

    }
}
