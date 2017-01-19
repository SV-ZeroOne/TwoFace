package za.co.entelect.bootcamp.twoface.squareeyes.services.payment;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Suppliers;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayments;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;

import java.math.BigDecimal;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class SupplierPaymentAdapter extends SupplierPaymentDTO implements AbstractAdapter{
    private Suppliers suppliers;
    private SupplierPayments supplierPayments;

    public SupplierPaymentAdapter(Suppliers suppliers, SupplierPayments supplierPayments){
        this.suppliers = suppliers;
        this.supplierPayments = supplierPayments;
    }

    public String getSupplierRefNumber() {
        return suppliers.getSupplerReference();
    }

    public String getPaymentRefNumber() {
        return supplierPayments.getPaymentID().toString();
    }

    public BigDecimal getAmount() {
        return supplierPayments.getTotal();
    }
}
