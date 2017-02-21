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
            i.Amount.ToString().Contains(searchString) ||
            i.Code != null && i.Code.Contains(search) ||
            i.DateIssued != null && i.DateIssued.ToString("d").Contains(searchString)
            ).ToList<VoucherDTO>();
        }

        public IEnumerable<VoucherDTO> Get()
        {
            return newVouchers;
        }

        public VoucherDTO GetById(int id)
        {
            Voucher someVoucher = voucherRepo.GetById(id);
            if (someVoucher == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            VoucherDTO someVoucherDTO = new VoucherDTO(someVoucher);
            return someVoucherDTO;
        }

        [Route("api/Vouchers/PlaceVoucher")]
        public void PlaceVoucher(decimal amount, int qty, bool valid)
        {
            if (amount <= 0 || (amount % 10) != 0)
            {
                HttpResponseMessage message = new HttpResponseMessage(HttpStatusCode.NotImplemented);
                message.Content = new StringContent("You entered a invalid Voucher Amount");
                throw new HttpResponseException(message);
            }
            else if (qty <= 0)
            {
                HttpResponseMessage message = new HttpResponseMessage(HttpStatusCode.NotImplemented);
                message.Content = new StringContent("You entered a invalid Voucher Quantity");
                throw new HttpResponseException(message);
            }
            else
            {
                for (int i = 0; i < qty; i++)
                {
                    string codeString = RandomString(10);
                    bool generating = true;

                    try
                    {
                        IEnumerable<Voucher> vouchers = voucherRepo.GetAll();

                        foreach (var item in vouchers)
                        {
                            newVouchers.Add(convertObjectToDTO(item));
                        }

                        while (generating)
                        {
                            foreach (VoucherDTO item in newVouchers)
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
                    }
                    catch (Exception e)
                    {

                        throw e;
                    }
                    

                    voucherService.placeVoucher(amount, codeString, valid);
                }
            }
        }

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

        private VoucherDTO convertObjectToDTO(Voucher voucher)
        {
            VoucherDTO tempVoucher = new VoucherDTO();
            tempVoucher.VoucherID = voucher.VoucherID;
            tempVoucher.Amount = voucher.Amount;
            tempVoucher.Code = voucher.Code;
            tempVoucher.DateIssued = voucher.DateIssued;
            tempVoucher.Valid = voucher.Valid;
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

        [Route("api/Vouchers/GetVouchers")]
        [HttpGet]
        public IHttpActionResult GetVouchers(string query, int pageSize, int pageNumber)
        {
            int total;
            IEnumerable<Voucher> vouchers;

            if (string.IsNullOrWhiteSpace(query) || query == "undefined")
            {
                total = voucherRepo.recordCount();

                vouchers = voucherRepo.Paging(pageNumber, pageSize);
            }
            else
            {
                total = voucherRepo.recordCount(query);

                vouchers = voucherRepo.Paging(pageNumber, pageSize, query);
            }

            IList<VoucherDTO> voucherDTO = new List<VoucherDTO>();

            foreach (var item in vouchers)
            {
                voucherDTO.Add(convertObjectToDTO(item));
            }

            return Ok(new PagedResult<VoucherDTO>(voucherDTO, pageNumber, pageSize, total));
        }
        //[Route("api/Vouchers/GetPaged")]
        //[HttpGet]
        //public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10, string query)
        //{
        //    int total;

        //    if (string.IsNullOrWhiteSpace(searchKey))
        //    {
        //        total = voucherRepo.recordCount();
        //    }
        //    else
        //    {
        //        total = voucherRepo.recordCount(searchKey);
        //    }

        //    IEnumerable<Voucher> vouchers = voucherRepo.Paging(pageNo, pageSize);
        //    IList<VoucherDTO> voucherDTO = new List<VoucherDTO>();

        //    foreach (var item in vouchers)
        //    {
        //        voucherDTO.Add(convertObjectToDTO(item));
        //    }

        //    return Ok(new PagedResult<VoucherDTO>(voucherDTO, pageNo, pageSize, total));
        //}

        //[Route("api/Vouchers/GetSearchPaged")]
        //[HttpGet]
        //public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        //{
        //    if (searchKey != null)
        //    {
        //        IEnumerable<Voucher> vouchers = voucherRepo.Paging(pageNumber, 10, searchKey);
        //        IList<VoucherDTO> voucherDTO = new List<VoucherDTO>();

        //        foreach (var item in vouchers)
        //        {
        //            voucherDTO.Add(convertObjectToDTO(item));
        //        }

        //        return Ok(new PagedResult<VoucherDTO>(voucherDTO, pageNumber, 10, total));
        //    }
        //    return null;
        //}

        [Route("api/Vouchers/GetStats")]
        [HttpGet]
        public Stats GetVoucherStats()
        {
            int usedCount = 0;
            int vouch2017 = 0;
            int vouch2016 = 0;
            int vouch2015 = 0;
            int vouch2014 = 0;
            decimal totalVal = 0;
            decimal valUsed = 0;

            IEnumerable<Voucher> vouchers = voucherRepo.GetAll();

            foreach (var item in vouchers)
            {
                newVouchers.Add(convertObjectToDTO(item));
            }

            foreach (VoucherDTO v in newVouchers)
            {
                totalVal += v.Amount;
                if (v.Valid == false)
                {
                    usedCount++;
                    valUsed += v.Amount;
                }
                if (v.DateIssued.Year == 2017)
                {
                    vouch2017++;
                }
                else if (v.DateIssued.Year == 2016)
                {
                    vouch2016++;
                }
                else if (v.DateIssued.Year == 2015)
                {
                    vouch2015++;
                }
                else if (v.DateIssued.Year == 2014)
                {
                    vouch2014++;
                }
            }
            Stats voucherStats = new Stats();
            voucherStats.TotalUsedVouchers = usedCount;
            voucherStats.Vouchers2017 = vouch2017;
            voucherStats.Vouchers2016 = vouch2016;
            voucherStats.Vouchers2015 = vouch2015;
            voucherStats.Vouchers2014 = vouch2014;
            voucherStats.totalValue = totalVal;
            voucherStats.valueUsed = valUsed;
            return voucherStats;
        }

    }
}
