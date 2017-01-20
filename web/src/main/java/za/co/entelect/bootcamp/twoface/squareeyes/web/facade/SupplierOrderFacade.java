package za.co.entelect.bootcamp.twoface.squareeyes.web.facade;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayment;
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

public class SupplierOrderFacade
{
    private IssuesRepository issuesRepository;
    private OrdersRepository ordersRepository;
    private SuppliersRepository suppliersRepository;

    private PaymentService paymentService;
    private SupplierService supplierService;

    private ConcreteIssueOrderAdapterFactory ioFactory;
    private ConcreteSupplierPaymentAdapterFactory spFactory;


    public SupplierOrderFacade(PaymentService paymentService, SupplierService supplierService,
                               IssuesRepository issuesRepository, OrdersRepository ordersRepository,
                               SuppliersRepository suppliersRepository)
    {
        this.paymentService = paymentService;
        this.supplierService = supplierService;

        this.issuesRepository = issuesRepository;
        this.ordersRepository = ordersRepository;
        this.suppliersRepository = suppliersRepository;
    }

    public void placeOrder(Integer issueID, Integer qty){
        Issue issueOrder = (Issue) issuesRepository.find(issueID);
        if(issueOrder == null)
            return;

        List<Order> orderList = ordersRepository.findAll();
        Order order = null;
        for(Order o: orderList){
            if(o.getIssue() == issueOrder){
                order = o;
                break;
            }
        }

        List<Supplier> supplierList = suppliersRepository.findAll();
        Supplier supplier = null;
        for(Supplier s: supplierList){
            if(order.getSupplier() == supplier){
                supplier = s;
                break;
            }
        }

        //move this
        ioFactory = new ConcreteIssueOrderAdapterFactory(issueOrder, order);

        IssueOrderAdapter IOAdapter = (IssueOrderAdapter) ioFactory.createAdapter();
        supplierService.placeOrder(IOAdapter, supplier.getSupplerReference(), order.getQty());

        //ordersRepository.update(order);

        SupplierPayment sp = new SupplierPayment();
        sp.setOrder(order);
        sp.setProcessedDate(new Date());
        sp.setTotal(order.getTotal());

        spFactory = new ConcreteSupplierPaymentAdapterFactory(supplier, sp);

        SupplierPaymentAdapter SPAdapter = (SupplierPaymentAdapter) spFactory.createAdapter();
        paymentService.makePayment(SPAdapter);


        //Save the payment


    }
}
