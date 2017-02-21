using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data
{
    public interface IRepository <TEntity, TKey> where TEntity : class
    {
        TEntity GetById(TKey id);
        IEnumerable<TEntity> GetAll();

        void Add(TEntity Entity);

        void Update(TEntity Entity);

        void Delete(TEntity Entity);

        IEnumerable<TEntity> Paging(int page, int pageSize);

        IEnumerable<TEntity> Paging(int page, int pageSize, string searchQuery);

        int recordCount(string searchQuery);

        int recordCount();
    }
}
