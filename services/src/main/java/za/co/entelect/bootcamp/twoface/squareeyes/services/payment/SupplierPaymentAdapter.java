package za.co.entelect.bootcamp.twoface.squareeyes.services.payment;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayment;

import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class SupplierPaymentAdapter extends SupplierPaymentDTO {
    private Supplier supplier;
    private SupplierPayment supplierPayment;

    public SupplierPaymentAdapter(Supplier supplier, SupplierPayment supplierPayment){
        this.supplier = supplier;
        this.supplierPayment = supplierPayment;
    }

    public String getSupplierRefNumber() {
        return supplier.getSupplerReference();
    }

    public String getPaymentRefNumber() {
        return supplierPayment.getPaymentID().toString();
    }

    public BigDecimal getAmount() {
        return supplierPayment.getTotal();
    }
}
