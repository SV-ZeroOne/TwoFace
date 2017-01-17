package za.co.entelect.bootcamp.twoface.squareeyes.persistence;


import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

/**
 * Created by sean.vienings on 2017/01/16.
 */
public class SupplierRepositoryIMP extends RepositoryBase implements SupplierRepository{

    public SupplierRepositoryIMP()
    {
        tList.put(123, new Supplier("Dell", "New York", "RefNumber123"));
        tList.put(124, new Supplier("Hp", "Cape Town", "RefNumber124"));
        tList.put(125, new Supplier("Shark", "JHB", "RefNumber125"));
        tList.put(126, new Supplier("Logitech", "Tokyo", "RefNumber126"));
    }
}
