using ComicStock.Domain;

namespace ComicStock.Data.Interfaces
{
    public interface StockInterface : IRepository<Stock, int>
    {
        Stock checkExistingStock(int issueID, string condition);
    }
}
