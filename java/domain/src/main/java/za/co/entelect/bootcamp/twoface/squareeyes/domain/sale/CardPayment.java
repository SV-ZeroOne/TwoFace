package za.co.entelect.bootcamp.twoface.squareeyes.domain.sale;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="CardPayments")
public class CardPayment {

    private CustomerOrder customerOrder;

    private int paymentID;
    private String referenceID;
    private BigDecimal voucherAmount;

    public CardPayment() {
    }

    public CardPayment(String referenceID, BigDecimal voucherAmount) {
        this.referenceID = referenceID;
        this.voucherAmount = voucherAmount;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustomerOrderID", referencedColumnName = "CustomerOrderID")
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }
    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Id
    @GeneratedValue
    @Column(name = "CardPaymentID")
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    @Column(name = "ReferenceID")
    public String getReferenceID() {
        return referenceID;
    }
    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    @Column(name = "CardAmount")
    public BigDecimal getVoucherAmount() {
        return voucherAmount;
    }
    public void setVoucherAmount(BigDecimal voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

}


