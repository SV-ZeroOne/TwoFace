using System;
using NUnit.Framework;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System.Collections.Generic;
using ComicStock.Data.Implementations;
using Moq;
using ComicStock.WebAPI.Controllers;
using System.Web.Http;
using System.Web.Http.Results;
using ComicStock.Models;
using ComicStock.WebAPI.Models;

namespace ComicStock.Tests
{
    //[TestFixture]
    //public class SuppliersControllerTest
    //{
    //    //[TestFixtureSetUp]
    //    //public void Init(SupplierInterface supplierRepo)
    //    //{
    //    //    this.supplierRepo = supplierRepo;
    //    //}

    //    [Test]
    //    public void GetAllSupplier()
    //    {
    //        //Arrange
    //        var mockSupplierRepo = new Mock<SupplierInterface>();
    //        mockSupplierRepo.Setup(x => x.GetById(3)).Returns(new Supplier { SupplierID = 3 });

    //        var controller = new SuppliersController(mockSupplierRepo.Object);

    //        //Act
    //        SupplierDTO supDTO = controller.GetById(3);

    //        //Assert
    //        Assert.AreEqual(3, supDTO.SupplierID);
    //    }

        //[Test]
        //public void GetSupplier()
        //{
        //    //Arrange
        //    var supplierRepo = this.supplierRepo;

        //    //Act
        //    var supplier = supplierRepo.GetById(2);

        //    //Assert
        //    Assert.IsNotNull(supplier);
        //    Assert.AreEqual(2, supplier.SupplierID, "MyInt is not equal");
        //}
    //}
}
