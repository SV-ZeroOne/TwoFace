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
        //IEnumerable<TEntity> Get(Expression<Func<TEntity, bool> where);

        //IEnumerable<TEntity> Find(string criteria);

        void Add(TEntity Entity);

        void Update(TEntity Entity);

        void Delete(TEntity Entity);
    }
}
