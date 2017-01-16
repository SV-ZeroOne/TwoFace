package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

/**
 * Created by sean.vienings on 2017/01/14.
 */
public class Supplier{
    private int supplierID = 0;
    private String supplierName;
    private String supplierCity;
    private String supplierReferenceNumber;

    public Supplier Supplier(String supplierName, String supplierCity, String supplierReferenceNumber){
        this.supplierName = supplierName;
        this.supplierCity = supplierCity;
        this.supplierReferenceNumber = supplierReferenceNumber;
        return this;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public String getSupplerReference() {
        return supplierReferenceNumber;
    }

    public boolean setSupplierName(String supplierName) {
        if((this.supplierName = supplierName)!= null){
            return true;
        }else{return false;}
    }

    public boolean setSupplierCity(String supplierCity) {
        if((this.supplierCity = supplierCity)!=null) {
            return true;
        }else{return false;}
    }

    public boolean setSupplierReference(String supplierReference) {
        if((this.supplierReferenceNumber = supplierReference)!=null) {
            return true;
        }else{return false;}
    }
}