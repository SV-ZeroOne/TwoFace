package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */

@javax.persistence.Entity
@Table
public class SupplierPayment {

    @Id
    private Integer paymentID;

    @OneToOne
    private Order order;

    @Column
    private BigDecimal total;

    @Temporal(value = TemporalType.DATE)
    private Date processedDate;

    public SupplierPayment() {
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
