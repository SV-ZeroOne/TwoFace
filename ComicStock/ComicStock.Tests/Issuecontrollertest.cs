using ComicStock.Controllers;
using ComicStock.Data.Implementations;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using ComicStock.WebAPI.Controllers;
using Moq;
using NUnit.Framework;
using System.Collections;
using System.Collections.Generic;

namespace ComicStock.Tests
{

    [TestFixture]
    class IssuesControllerTest
    {
        //IssueInterface issueRepo;

        //[TestFixtureSetUp]
        //public void Init(IssueInterface issueRepo)
        //{
        //    this.issueRepo = issueRepo;
        //}

        [Test]
        public void GetAllIssue()
        {
            //Arrange
            var mockIssueRepo = new Mock<IssueInterface>();
            mockIssueRepo.Setup(x => x.GetById(3)).Returns(new Issue { IssueID = 3 });

            var controller = new IssuesController(mockIssueRepo.Object);

            //Act
            IssueDTO issueDTO = controller.GetById(3);

            //Assert
            Assert.AreEqual(3, issueDTO.IssueID);
        }

        //[Test]
        //public void GetIssue()
        //{
        //    //Arrange
        //    var issueRepo = this.issueRepo;

        //    //Act
        //    var issue = issueRepo.GetById(2);

        //    //Assert
        //    Assert.IsNotNull(issue);
        //    Assert.AreEqual(2, issue.IssueID, "MyInt is not equal");

        //}
    }
}
