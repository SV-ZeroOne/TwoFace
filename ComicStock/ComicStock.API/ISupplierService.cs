using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.API
{
    public interface ISupplierService
    {
        void placeSupplier(string name, string city, string refnum);
    }
}
