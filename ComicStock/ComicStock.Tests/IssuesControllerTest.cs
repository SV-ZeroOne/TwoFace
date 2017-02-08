using ComicStock.Controllers;
using NUnit.Framework;

namespace ComicStock.Tests
{
    [TestFixture]
    class IssuesControllerTest
    {
        [Test]
        public void GetIssue()
        {
            //Arrange
            var controller = new IssuesController();
            //Act
            var issueDto = controller.Get(2);
            //Assert
            //Assert.IsNotNull(issueDto);
            Assert.AreEqual(2, issueDto.Id, "MyInt is not equal");
        }
    }
}
