package za.co.entelect.bootcamp.twoface.squareeyes.services.payment;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayment;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;

import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class SupplierPaymentAdapter extends SupplierPaymentDTO implements AbstractAdapter{
    private Supplier supplier;
    private SupplierPayment supplierPayment;

    public SupplierPaymentAdapter(Supplier supplier, SupplierPayment supplierPayment){
        this.supplier = supplier;
        this.supplierPayment = supplierPayment;
    }

    public String getSupplierRefNumber() {
        return supplier.getSupplierReferenceNumber();
    }

    public String getPaymentRefNumber() {
        return Integer.toString(supplierPayment.getPaymentID());
    }

    public BigDecimal getAmount() {
        return supplierPayment.getTotal();
    }
}
