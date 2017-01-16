package za.co.entelect.bootcamp.twoface.squareeyes.services.payment;

import java.math.BigDecimal;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public class SupplierPaymentDTO {

    private String supplierRefNumber;
    private String paymentRefNumber;
    private BigDecimal amount;

    public SupplierPaymentDTO(){}

    public SupplierPaymentDTO(String supplierRefNumber, String paymentRefNumber, BigDecimal amount) {
        this.supplierRefNumber = supplierRefNumber;
        this.paymentRefNumber = paymentRefNumber;
        this.amount = amount;
    }

    public String getSupplierRefNumber() {
        return supplierRefNumber;
    }

    public String getPaymentRefNumber() {
        return paymentRefNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
