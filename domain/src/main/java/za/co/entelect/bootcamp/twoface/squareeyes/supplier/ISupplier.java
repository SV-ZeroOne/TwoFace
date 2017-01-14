package za.co.entelect.bootcamp.twoface.squareeyes.supplier;

/**
 * Created by sean.vienings on 2017/01/14.
 */
public interface ISupplier {
    public int getSupplierID();
    public String getSupplierName();
    public String getSupplierCity();
    public String getSupplerReference();

    public boolean setSupplierName(String supplierName);
    public boolean setSupplierCity(String supplierCity);
    public boolean setSupplierReference(String supplierReference);

}
