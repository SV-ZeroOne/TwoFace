using NUnit.Framework;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using Moq;
using ComicStock.WebAPI.Controllers;
using ComicStock.Models;

namespace ComicStock.Tests
{
    [TestFixture]
    public class StocksControllerTest
    {
        //StockInterface stockRepo;

        //[TestFixtureSetUp]
        //public void Init(StockInterface stockRepo)
        //{
        //    this.stockRepo = stockRepo;
        //}

        //[Test]
        //public void GetAllStocks()
        //{
        //    //Arrange
        //    var mockStockRepo = new Mock<StockInterface>();
        //    mockStockRepo.Setup(x => x.GetById(3)).Returns(new Stock { StockReferenceID = 3 });

        //    var controller = new StockController(mockStockRepo.Object);

        //    //Act
        //    StockDTO stockDTO = controller.GetById(3);

        //    //Assert
        //    Assert.AreEqual(3, stockDTO.StockReferenceID);
        //}

        //[Test]
        //public void GetStock()
        //{
        //    //Arrange
        //    var stockRepo = this.stockRepo;

        //    //Act
        //    var stock = stockRepo.GetById(2);

        //    //Assert
        //    Assert.IsNotNull(stock);
        //    Assert.AreEqual(2, stock.StockReferenceID, "MyInt is not equal");
        //}
    }
}
