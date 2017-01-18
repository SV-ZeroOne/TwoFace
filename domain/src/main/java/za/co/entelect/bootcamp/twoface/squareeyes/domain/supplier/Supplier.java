package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sean.vienings on 2017/01/14.
 */

@Entity
@Table
public class Supplier{

    @Id
    @Column(nullable = false)
    private Integer supplierID;

    @Column
    private String supplierName;

    @Column
    private String supplierCity;

    @Column
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
