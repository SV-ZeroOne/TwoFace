package za.co.entelect.bootcamp.twoface.squareeyes.web.facade;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayment;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.*;
import za.co.entelect.bootcamp.twoface.squareeyes.services.factory.ConcreteIssueOrderAdapterFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.services.factory.ConcreteSupplierPaymentAdapterFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.PaymentService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.SupplierPaymentAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.IssueOrderAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.SupplierService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SupplierOrderFacade
{
    private IssueRepository issueRepository;
    private OrderRepository orderRepository;
    private SupplierRepository supplierRepository;

    private PaymentService paymentService;
    private SupplierService supplierService;

    private ConcreteIssueOrderAdapterFactory ioFactory;
    private ConcreteSupplierPaymentAdapterFactory spFactory;


    public SupplierOrderFacade(PaymentService paymentService, SupplierService supplierService,
                             IssueRepository issueRepository, OrderRepository orderRepository,
                             SupplierRepository supplierRepository)
    {
        this.paymentService = paymentService;
        this.supplierService = supplierService;

        this.issueRepository = issueRepository;
        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
    }

    public void placeOrder(Integer issueID, Integer qty){
        Issue issueOrder = (Issue) issueRepository.find(issueID);
        if(issueOrder == null)
            return;

        List<Order> orderList = orderRepository.findAll();
        Order order = null;
        for(Order o: orderList){
            if(o.getIssue() == issueOrder){
                order = o;
                break;
            }
        }

        List<Supplier> supplierList = supplierRepository.findAll();
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

        orderRepository.update(order);

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
