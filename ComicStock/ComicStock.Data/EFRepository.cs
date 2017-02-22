using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;

namespace ComicStock.Data
{
    public abstract class EFRepository<TEntity, TKey> : IRepository<TEntity, TKey> where TEntity : class, IEntity<TKey>
    {
        protected readonly SquareEyesContext context = new SquareEyesContext();

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

        public virtual int recordCount(string searchQuery)
        {
            return context.Set<TEntity>().Count();
        }

        public virtual IEnumerable<TEntity> Paging(int page, int pageSize)
        {
            return context.Set<TEntity>().Where(x => x.IsDeleted == false).ToList();
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

        public virtual IEnumerable<TEntity> Paging(int page, int pageSize, string searchQuery)
        {
            return context.Set<TEntity>().Where(x => x.IsDeleted == false).ToList();
        }

        public virtual int recordCount()
        {
            return context.Set<TEntity>().Count();
        }
    }
}
