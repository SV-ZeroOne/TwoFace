using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using Moq;
using NUnit.Framework;
using ComicStock.WebAPI.Models;
using ComicStock.WebAPI.Controllers;
using System;
using System.Collections.Generic;

namespace ComicStock.Tests
{
    [TestFixture]
    public class VouchersControllerTest
    {
        //VoucherInterface voucherRepo;

        //[TestFixtureSetUp]
        //public void Init(VoucherInterface voucherRepo)
        //{
        //    this.voucherRepo = voucherRepo;
        //}

        [Test]
        public void GetAllVoucher()
        {
            //Arrange
            var mockVoucherRepo = new Mock<VoucherInterface>();
            mockVoucherRepo.Setup(x => x.GetById(3)).Returns(new Voucher { VoucherID = 3 });

            var controller = new VouchersController(mockVoucherRepo.Object);

            //Act
            VoucherDTO voucherDTO = controller.GetById(3);

            //Assert
            Assert.AreEqual(3, voucherDTO.VoucherID);
        }

        //[Test]
        //public void GetVoucher()
        //{
        //    //Arrange
        //    var voucherRepo = this.voucherRepo;

        //    //Act
        //    var voucher = voucherRepo.GetById(2);

        //    //Assert
        //    Assert.IsNotNull(voucher);
        //    Assert.AreEqual(2, voucher.VoucherID, "MyInt is not equal");
        //}
    }
}
