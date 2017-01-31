package za.co.entelect.bootcamp.twoface.squareeyes.domain.sale;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.CustomerAddress;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="CustomerOrders")
public class CustomerOrder {

    private Customer customer;
    private CustomerAddress customerAddress;

    private int customerOrderID;
    private String deliveryOption;
    private String specialInstructions;
    private BigDecimal paymentAmount;
    private String paymentStatus;
    private Date orderDate;

    public CustomerOrder(){}

    public CustomerOrder(String deliveryOption, String specialInstructions, BigDecimal paymentAmount, String paymentStatus, Date orderDate){
        this.deliveryOption = deliveryOption;
        this.specialInstructions = specialInstructions;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "CustomerAddressID", referencedColumnName = "CustomerAddressID")
    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }
    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Id
    @GeneratedValue
    @Column(name="CustomerOrderID")
    public int getCustomerOrderID() {
        return customerOrderID;
    }

    @Column(name="DeliveryOption")
    public String getDeliveryOption() {
        return deliveryOption;
    }
    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    @Column(name="SpecialInstructions")
    public String getSpecialInstructions() {
        return specialInstructions;
    }
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    @Column(name="PaymentAmount")
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Column(name="PaymentStatus")
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="OrderDate")
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
