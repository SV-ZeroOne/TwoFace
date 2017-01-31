package za.co.entelect.bootcamp.twoface.squareeyes.domain.sale;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="VoucherPayments")
public class VoucherPayment {

    private CustomerOrder customerOrder;
    private Voucher voucher;

    private int paymentID;
    private BigDecimal voucherAmount;

    public VoucherPayment() {
    }

    public VoucherPayment(BigDecimal voucherAmount) {
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VoucherID", referencedColumnName = "VoucherID")
    public Voucher getVoucher() {
        return voucher;
    }
    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    @Id
    @GeneratedValue
    @Column(name = "PaymentID")
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    @Column(name = "VoucherAmount")
    public BigDecimal getVoucherAmount() {
        return voucherAmount;
    }
    public void setVoucherAmount(BigDecimal voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

}
