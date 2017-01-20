package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.generic.Repository;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by sean.vienings on 2017/01/16.
 */
public abstract class RelationalRepository<T> implements Repository<T> {

    private static final Logger logger = LoggerFactory.getLogger(RelationalRepository.class);

    protected EntityManager entityManager;
    protected EntityManagerFactory entityManagerFactory;

    private Class<T> type;

    public RelationalRepository() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];

        entityManagerFactory = Persistence.createEntityManagerFactory("Ironman");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public T find(Object id) {
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
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x where x.");
        sb.append(property);
        sb.append(" like %");
        sb.append(criteria);
        sb.append("%");
        Query query = this.entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    public List<T> search(String property, Integer criteria) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x where x.");
        sb.append(property);
        sb.append(" = ");
        sb.append(criteria.toString());
        Query query = this.entityManager.createQuery(sb.toString());
        return query.getResultList();
    }

    public List<T> search(String property, String criteria, int pageSize, int pageNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("select x from ");
        sb.append(type.getSimpleName());
        sb.append(" x where x.");
        sb.append(property);
        sb.append(" like %");
        sb.append(criteria);
        sb.append("%");
        Query query = this.entityManager.createQuery(sb.toString());
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

    @PersistenceContext
    public void delete(Object id) {
        entityManager.getTransaction().begin();
        entityManager.remove(this.entityManager.getReference(type, id));
        entityManager.getTransaction().commit();
    }

    @PersistenceContext
    public T create(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return t;
    }

    public T update(T t) {
        this.entityManager.merge(t);
        this.entityManager.flush();
        return t;
    }

}
