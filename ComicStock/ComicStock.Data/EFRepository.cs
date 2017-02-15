﻿using System;
using System.Collections.Generic;
using System.Linq;
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
           // var entities = context.Set<TEntity>().Where(x => x.IsDeleted == false).ToList();
           //return entities;

            return context.Set<TEntity>().Where(x => x.IsDeleted == false).ToList();

            //if (context.Set<TEntity>().Where(x => x.isDeleted == false))
            //{
            //    return context.Set<TEntity>().ToList();
            //}
            //var entities = context.Set<TEntity>().Where(x => !x.isDeleted);

            //if (entities)
            //{

            //}

            //return context.Set<TEntity>().ToList();

            return null;
        }

        public IEnumerable<TEntity> find(string Criteria)
        {
            throw new NotImplementedException();
            // To Be Completed
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
            context.Set<TEntity>().Attach(entity);
            context.Entry(entity).State = System.Data.Entity.EntityState.Modified;
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
