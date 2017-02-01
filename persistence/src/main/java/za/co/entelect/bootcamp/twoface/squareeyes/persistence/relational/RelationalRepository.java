package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("Ironman");
    @PersistenceContext
    protected EntityManager entityManager = factory.createEntityManager();
    */@PersistenceContext
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
        //entityManager.getTransaction().begin();
        entityManager.persist(t);
        //entityManager.getTransaction().commit();
        entityManager.flush();
        return t;
    }

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
