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
    internal class SupplierQuoteRepo : EFRepository<SupplierQuote, int>, SupplierQuoteInterface
    {
        private readonly SquareEyesContext dbContext = new SquareEyesContext();
        public SupplierQuote getSupplierQuote(int issueID, int supplierID)
        {
            try
            {
                SupplierQuote someQuote = dbContext.SupplierQuote.SqlQuery("SELECT * FROM SupplierQuotes WHERE IssueID = @issueID AND SupplierID = @supplierID", 
                    new SqlParameter("issueID", issueID),
                    new SqlParameter("supplierID", supplierID)).FirstOrDefault<SupplierQuote>();
                return someQuote; 
            }
            catch(ArgumentNullException e)
            {
                Console.WriteLine("Exception in SupplierRepo, could not find quote by issue ID");
                Console.WriteLine(e);
                return null;
            }
            
        }

    }
}
