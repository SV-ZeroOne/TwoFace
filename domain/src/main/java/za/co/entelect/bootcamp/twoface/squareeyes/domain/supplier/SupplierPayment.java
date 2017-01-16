package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class SupplierPayment {
    private Integer paymentID;
    private Integer orderID;
    private BigDecimal total;
    private Date processedDate;

    public SupplierPayment() {
    }

    public Integer getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
