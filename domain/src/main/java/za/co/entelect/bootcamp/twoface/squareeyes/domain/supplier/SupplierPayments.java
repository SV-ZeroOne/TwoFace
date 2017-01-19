package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Orders;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */

@Entity
@Table
public class SupplierPayments {

    @Id
    @GeneratedValue
    @Column(name="PaymentID")
    private Integer paymentID;

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private Orders orders;

    @Column(name="Total")
    private BigDecimal total;

    @Temporal(value = TemporalType.DATE)
    @Column(name="ProcessedDate")
    private Date processedDate;

    public SupplierPayments() {
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }


}
