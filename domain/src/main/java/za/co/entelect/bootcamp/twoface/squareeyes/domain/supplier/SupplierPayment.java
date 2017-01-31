package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */

@Entity
@Table(name="SupplierPayments")
public class SupplierPayment {

    private Order order;

    private int paymentID;
    private BigDecimal total;
    private Date processedDate;

    public SupplierPayment(BigDecimal total, Date processedDate) {
        this.total = total;
        this.processedDate = processedDate;
    }

    public SupplierPayment(){}

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "OrderID")
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    @Id
    @GeneratedValue
    @Column(name="PaymentID")
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    @Column(name="Total")
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name="ProcessedDate")
    public Date getProcessedDate() {
        return processedDate;
    }
    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }


}
