package za.co.entelect.bootcamp.twoface.squareeyes.persistence.generic;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public interface Repository<T>
{
    T find(Object id);
    List<T> findAll();
    List<T> findAll(int pageSize, int pageNumber);
    List<T> search(String property, String criteria);
    List<T> search(String property, Integer criteria);
    List<T> search(String property, String criteria, int pageSize, int pageNumber);
    long count();
    void delete(Object id);

    T create(T t);
    T update(T t);
}
