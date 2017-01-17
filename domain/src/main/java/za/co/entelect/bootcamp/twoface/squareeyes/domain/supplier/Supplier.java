package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;

/**
 * Created by sean.vienings on 2017/01/14.
 */
public class Supplier{
    private Integer supplierID;
    private String supplierName;
    private String supplierCity;
    private String supplierReferenceNumber;

    public Supplier(String supplierName, String supplierCity, String supplierReferenceNumber){
        this.supplierName = supplierName;
        this.supplierCity = supplierCity;
        this.supplierReferenceNumber = supplierReferenceNumber;
    }

    public Supplier()
    {

    }

    public Integer getID() {
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
