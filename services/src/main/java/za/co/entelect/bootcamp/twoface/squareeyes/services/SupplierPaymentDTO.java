package za.co.entelect.bootcamp.twoface.squareeyes.services;

import java.math.BigDecimal;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class SupplierPaymentDTO {

    private String supplierRefNumber;
    private String paymentRefNumber;
    private BigDecimal amount;

    public SupplierPaymentDTO(String supplierRefNumber, String paymentRefNumber, BigDecimal amount) {
    }

    public String getSupplierRefNumber() {
        return supplierRefNumber;
    }

    public void setSupplierRefNumber(String supplierRefNumber) {
        this.supplierRefNumber = supplierRefNumber;
    }

    public String getPaymentRefNumber() {
        return paymentRefNumber;
    }

    public void setPaymentRefNumber(String paymentRefNumber) {
        this.paymentRefNumber = paymentRefNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
