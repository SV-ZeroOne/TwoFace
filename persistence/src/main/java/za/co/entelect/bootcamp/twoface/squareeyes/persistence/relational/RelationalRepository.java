package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.generic.Repository;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by sean.vienings on 2017/01/16.
 */
@org.springframework.stereotype.Repository
public abstract class RelationalRepository<T> implements Repository<T> {

    private static final Logger logger = LoggerFactory.getLogger(RelationalRepository.class);

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> type;

    public RelationalRepository() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    public T find(Object id) {
        logger.info("name holds {}", type.getSimpleName());
        System.out.println(id);
        return this.entityManager.find(type, id);
    }

    public List<T> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x");
        Query query = this.entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    public List<T> findAll(int pageSize, int pageNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x");
        Query query = this.entityManager.createQuery(sb.toString());
        query.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<T> search(String property, String criteria) {
        Query query = this.entityManager.createQuery("select x from " +
                type.getSimpleName() +
                " x where x."+
                property+
                " like :criteria");
        query.setParameter("criteria", "%" + criteria + "%");
        return query.getResultList();
    }

    public List<T> search(String property, Integer criteria) {
        Query query = this.entityManager.createQuery("select x from " +
                type.getSimpleName() +
                " x where x."+
                property+
                " = :criteria");
        query.setParameter("criteria", criteria);
        return query.getResultList();
    }

    public List<T> search(String property, String criteria, int pageSize, int pageNumber) {
        Query query = this.entityManager.createQuery("select x from " +
                type.getSimpleName() +
                " x where x."+
                property+
                " like :criteria");
        query.setParameter("criteria", "%" + criteria + "%");
        query.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize);
        return query.getResultList();
    }

    public long count() {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(x) from ");
        sb.append(type.getSimpleName());
        sb.append(" x");
        Query query = this.entityManager.createQuery(sb.toString());
        return (Long)query.getSingleResult();
    }

    @Transactional
    public void delete(Object id) {
        entityManager.remove(this.entityManager.getReference(type, id));
    }

    @Transactional
    public T create(T t) {
        entityManager.persist(t);
        entityManager.flush();
        return t;
    }

    @Transactional
    public T update(T t) {
        this.entityManager.merge(t);
        this.entityManager.flush();
        return t;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
