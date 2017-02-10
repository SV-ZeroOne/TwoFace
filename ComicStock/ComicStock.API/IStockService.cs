using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
namespace ComicStock.API
{
    public interface IStockService
    {
         void AddStock(int id, int quantity);
         void RemovingStock(int id);
         void ReturningStock(int id, String condition);
    }
}
