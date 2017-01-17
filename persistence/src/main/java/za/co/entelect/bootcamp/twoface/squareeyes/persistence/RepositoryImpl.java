package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by sean.vienings on 2017/01/16.
 */

public abstract class RepositoryImpl<T> implements Repository<T>{
    protected Map<key, T> tList = new HashMap<key, T>();

    public RepositoryBase()
    {

    }

    public void create(T item) {
        tList.put(item.getID(), item);
    }

    public Map<key, T> read() {
        return tList;
    }

    public T read(key id) {
        return tList.get(id);

    }

    public void delete(key id) {
        tList.remove(id);
    }

    public void update(T item) {
       tList.put(item.getID(), item);
    }
}
