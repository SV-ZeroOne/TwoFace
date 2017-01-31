package za.co.entelect.bootcamp.twoface.squareeyes.domain.customer;

import javax.persistence.*;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="CustomerAddress")
public class CustomerAddress {

    private Customer customer;

    private int customerAddressID;
    private String deliveryDetails;
    private String suburb;
    private String city;
    private String postalCode;
    private String addressType;

    public CustomerAddress(){}

    public CustomerAddress(String deliveryDetails, String suburb,
                           String city, String postalCode, String addressType)
    {
        this.deliveryDetails = deliveryDetails;
        this.suburb = suburb;
        this.city = city;
        this.postalCode = postalCode;
        this.addressType = addressType;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Id
    @GeneratedValue
    @Column(name = "CustomerAddressID")
    public int getCustomerAddressID() {
        return customerAddressID;
    }
    public void setCustomerAddressID(int customerAddressID) {
        this.customerAddressID = customerAddressID;
    }

    @Column(name = "DeliveryDetails")
    public String getDeliveryDetails() {
        return deliveryDetails;
    }
    public void setDeliveryDetails(String deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    @Column(name = "Suburb")
    public String getSuburb() {
        return suburb;
    }
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    @Column(name = "City")
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "PostalCode")
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Column(name = "AddressType")
    public String getAddressType() {
        return addressType;
    }
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }
}
