package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;


import javax.persistence.*;

/**
 * Created by sean.vienings on 2017/01/14.
 */

@Entity
@Table(name="Suppliers")
public class Supplier {

    private int supplierID;
    private String supplierName;
    private String supplierCity;
    private String supplierReferenceNumber;

    public Supplier() {}

    public Supplier(String supplierName, String supplierCity, String supplierReferenceNumber){
        this.supplierName = supplierName;
        this.supplierCity = supplierCity;
        this.supplierReferenceNumber = supplierReferenceNumber;
    }

    @Id
    @GeneratedValue
    @Column(name="SupplierID")
    public int getSupplierID() {
        return supplierID;
    }
    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    @Column(name="Name")
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName){
        this.supplierName = supplierName;
    }

    @Column(name="City")
    public String getSupplierCity() {
        return supplierCity;
    }
    public void setSupplierCity(String supplierCity){
        this.supplierCity = supplierCity;
    }

    @Column(name="ReferenceNumber")
    public String getSupplierReferenceNumber() {
        return supplierReferenceNumber;
    }
    public void setSupplierReferenceNumber(String supplierReferenceNumber) {
        this.supplierReferenceNumber = supplierReferenceNumber;
    }
}
