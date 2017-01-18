package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;

import java.util.List;
import java.util.Map;

public interface Repository<T>
{
    T create(T item);
    List<T> read();
    T read(Object id);
    void delete(Object id);
    T   update(T item);
    List<T> findAll();
    List<T> findAll(int pageSize, int pageNumber);
    List<T> search(String property, String criteria);
    List<T> search(String property, String criteria, int pageSize, int pageNumber);

}
