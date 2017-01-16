package za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier;

import java.util.Date;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class SupplierPayment {
    private int paymentID;
    private int orderID;
    private int total;
    private Date processedDate;

    public SupplierPayment() {
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }


}
