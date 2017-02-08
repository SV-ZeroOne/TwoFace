using ComicStock.Controllers;
using ComicStock.Data.Implementations;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.Models;
using NUnit.Framework;
using System.Collections;
using System.Collections.Generic;

namespace ComicStock.Tests
{

    [TestFixture]
    class IssuesControllerTest
    {
        IssueInterface issueRepo;

        [TestFixtureSetUp]
        public void Init(IssueInterface issueRepo)
        {
            this.issueRepo = issueRepo;
        }

        [Test]
        public void GetAllIssue()
        {
            //Arrange
            var newissueRepo = issueRepo;

            //Act
            IEnumerable<Issue> issues = newissueRepo.GetAll();

            //Assert
            Assert.IsNotNull(issues);
            //Assert.AreEqual(2, issueDto.Id, "MyInt is not equal");
        }

        [Test]
        public void GetIssue()
        {
            //Arrange
            var issueRepo = this.issueRepo;

            //Act
            var issue = issueRepo.GetById(2);

            //Assert
            Assert.IsNotNull(issue);
            Assert.AreEqual(2, issue.IssueID, "MyInt is not equal");

        }
    }
}
