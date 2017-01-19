package za.co.entelect.bootcamp.twoface.squareeyes.services.factory;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Suppliers;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayments;
import za.co.entelect.bootcamp.twoface.squareeyes.services.AbstractAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.SupplierPaymentAdapter;

/**
 * Created by quinton.weenink on 2017/01/16.
 */
public class ConcreteSupplierPaymentAdapterFactory {
    Suppliers suppliers;
    SupplierPayments supplierPayments;

    public ConcreteSupplierPaymentAdapterFactory(Suppliers suppliers, SupplierPayments supplierPayments)
    {
        this.suppliers = suppliers;
        this.supplierPayments = supplierPayments;
    }

    public AbstractAdapter createAdapter()
    {
        return new SupplierPaymentAdapter(suppliers, supplierPayments);
    }
}
