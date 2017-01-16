package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;

import java.util.List;
import java.util.Map;

public interface Repository<T extends Entity<key>,key>
{
    void create(T item);
    Map<key, T> read();
    T read(key id);
    void delete(key id);
    void update(T item);

}
