package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;

import java.util.List;
import java.util.Map;

public interface Repository<T>
{
    void create(T item);
    List<T> read();
    T read(Object id);
    void delete(Object id);
    void update(T item);

}
