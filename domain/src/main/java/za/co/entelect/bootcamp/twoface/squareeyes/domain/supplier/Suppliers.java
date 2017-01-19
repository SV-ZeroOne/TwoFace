package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;


import javax.persistence.*;

/**
 * Created by sean.vienings on 2017/01/14.
 */

@Entity
@Table
public class Suppliers {

    @Id
    @GeneratedValue
    @Column(name="SupplierID")
    private Integer supplierID;

    @Column(name="Name")
    private String supplierName;

    @Column(name="City")
    private String supplierCity;

    @Column(name="ReferenceNumber")
    private String supplierReferenceNumber;

    public Suppliers(String supplierName, String supplierCity, String supplierReferenceNumber){
        this.supplierName = supplierName;
        this.supplierCity = supplierCity;
        this.supplierReferenceNumber = supplierReferenceNumber;
    }

    public Suppliers()
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
