package za.co.entelect.bootcamp.twoface.squareeyes.services.factory;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayment;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.SupplierPaymentAdapter;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class ConcreteSupplierPaymentAdapterFactory {
    Supplier supplier;
    SupplierPayment supplierPayment;

    public ConcreteSupplierPaymentAdapterFactory(Supplier supplier, SupplierPayment supplierPayment)
    {
        this.supplier = supplier;
        this.supplierPayment = supplierPayment;
    }

    public AbstractAdapter createAdapter()
    {
        return new SupplierPaymentAdapter(supplier, supplierPayment);
    }
}
