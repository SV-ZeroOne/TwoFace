package za.co.entelect.bootcamp.twoface.squareeyes.web.facade;

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
        Issues issueOrder = (Issues) issuesRepository.find(issueID);
        if(issueOrder == null)
            return;

        List<Orders> ordersList = ordersRepository.findAll();
        Orders orders = null;
        for(Orders o: ordersList){
            if(o.getIssue() == issueOrder){
                orders = o;
                break;
            }
        }

        List<Suppliers> suppliersList = suppliersRepository.findAll();
        Suppliers suppliers = null;
        for(Suppliers s: suppliersList){
            if(orders.getSuppliers() == suppliers){
                suppliers = s;
                break;
            }
        }

        //move this
        ioFactory = new ConcreteIssueOrderAdapterFactory(issueOrder, orders);

        IssueOrderAdapter IOAdapter = (IssueOrderAdapter) ioFactory.createAdapter();
        supplierService.placeOrder(IOAdapter, suppliers.getSupplerReference(), orders.getQty());

        //ordersRepository.update(orders);

        SupplierPayments sp = new SupplierPayments();
        sp.setOrders(orders);
        sp.setProcessedDate(new Date());
        sp.setTotal(orders.getTotal());

        spFactory = new ConcreteSupplierPaymentAdapterFactory(suppliers, sp);

        SupplierPaymentAdapter SPAdapter = (SupplierPaymentAdapter) spFactory.createAdapter();
        paymentService.makePayment(SPAdapter);


        //Save the payment


    }
}
