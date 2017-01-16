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
        return supplier.getSupplerReference();
    }

    public String getPaymentRefNumber() {
        return supplierPayment.getPaymentID().toString();
    }

    public BigDecimal getAmount() {
        return supplierPayment.getTotal();
    }

    public void doSomething() {
        System.out.println("supplier payment adapter");
    }
}
