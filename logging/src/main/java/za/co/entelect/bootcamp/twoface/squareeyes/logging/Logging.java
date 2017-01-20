package za.co.entelect.bootcamp.twoface.squareeyes.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Orders;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Suppliers;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayments;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.services.factory.ConcreteIssueOrderAdapterFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.services.factory.ConcreteSupplierPaymentAdapterFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.PaymentService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.SupplierPaymentAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.IssueOrderAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.SupplierService;

import java.util.Date;
import java.util.List;

public class Logging
{


    public Logging(PaymentService paymentService, SupplierService supplierService,
                   IssuesRepository issuesRepository, OrdersRepository ordersRepository,
                   SuppliersRepository suppliersRepository)
    {


    }
}
