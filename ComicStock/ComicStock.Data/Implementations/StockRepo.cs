using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data.Implementations
{
    class StockRepo : EFRepository<Stock, int>,  StockInterface
    {
        private readonly SquareEyesContext dbContext = new SquareEyesContext();

        public Stock checkExistingStock(int issueID, string condition)
        {
            var theStock = dbContext.Stock.SqlQuery("SELECT * FROM Stock WHERE IssueID = @issueID AND Condition = @condition",
                new SqlParameter("issueID", issueID),
                new SqlParameter("condition", condition)).First<Stock>();
            return theStock;
        }

        public override IEnumerable<Stock> Paging(int page, int pageSize)
        {
            return context.Set<Stock>()
                .OrderBy(x => x.StockReferenceID)
                .Skip((page - 1) * pageSize)
                .Take(pageSize)
                .ToList();
        }
    }
}
