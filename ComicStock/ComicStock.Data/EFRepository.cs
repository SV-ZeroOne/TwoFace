using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Data
{
    public abstract class EFRepository<TEntity, TKey> : IRepository<TEntity, TKey> where TEntity : class, IEntity<TKey>
    {
        private readonly SquareEyesContext context = new SquareEyesContext();

        public virtual TEntity GetById(TKey id)
        {
            return context.Set<TEntity>().Find(id);
        }

        public IEnumerable<TEntity> GetAll()
        {
            return context.Set<TEntity>().Where(x => x.IsDeleted == false).ToList();
        }

        public void Add(TEntity entity)
        {
            if (entity == null) throw new NullReferenceException(entity.ToString());
            context.Set<TEntity>().Add(entity);
            Save();
        }

        public void Update(TEntity entity)
        {
            if (entity == null) throw new NullReferenceException(entity.ToString());
            context.Set<TEntity>().AddOrUpdate(entity);
            Save();
        }

        public void Delete(TEntity entity)
        {
            if (entity == null) throw new NullReferenceException(entity.ToString());
            context.Set<TEntity>().Remove(entity);
            Save();
        }

        public void Save()
        {
            context.SaveChanges();
        }

    }
}
