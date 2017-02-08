using ComicStock.Controllers;
using ComicStock.Data.Implementations;
using ComicStock.Models;
using NUnit.Framework;

namespace ComicStock.Tests
{
    [TestFixture]
    class IssuesControllerTest
    {
        [Test]
        public void GetAllIssue()
        {
            //Arrange
            var issueRepo = new IssuesRepo();

            //Act
            var issue = issueRepo.GetAll();

            //Assert
            Assert.IsNotNull(issue);
            //Assert.AreEqual(2, issueDto.Id, "MyInt is not equal");
        }

        [Test]
        public void GetIssue()
        {
            //Arrange
            var issueRepo = new IssuesRepo();

            //Act
            var issue = issueRepo.GetById(2);

            //Assert
            Assert.IsNotNull(issue);
            Assert.AreEqual(2, issue.IssueID, "MyInt is not equal");

        }
    }
}
