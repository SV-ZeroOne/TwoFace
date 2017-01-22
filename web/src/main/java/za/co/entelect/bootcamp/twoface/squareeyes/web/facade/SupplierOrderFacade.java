package za.co.entelect.bootcamp.twoface.squareeyes.web.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(SupplierOrderFacade.class);
    private IssuesRepository issuesRepository;
    private OrdersRepository ordersRepository;
    private SuppliersRepository suppliersRepository;

    private PaymentService paymentService;
    private SupplierService supplierService;

    private ConcreteIssueOrderAdapterFactory ioFactory;
    private ConcreteSupplierPaymentAdapterFactory spFactory;

    public SupplierOrderFacade(){
        logger.info("Starting the log for class SupplierOrderFacade");
    }

    public SupplierOrderFacade(PaymentService paymentService, SupplierService supplierService,
                               IssuesRepository issuesRepository, OrdersRepository ordersRepository,
                               SuppliersRepository suppliersRepository)
    {
        logger.info("Starting the log for class SupplierOrderFacade");
        this.paymentService = paymentService;
        this.supplierService = supplierService;

        this.issuesRepository = issuesRepository;
        this.ordersRepository = ordersRepository;
        this.suppliersRepository = suppliersRepository;
        logger.debug("SupplierOrderFacade given the following values: ");
        logger.debug("paymentService holds the following: {}" + paymentService.getClass().getSimpleName());
        logger.debug("supplierService holds the following: {}" + supplierService.getClass().getSimpleName());
        logger.debug("issuesRepository holds the following: {}" + issuesRepository.getClass().getSimpleName());
        logger.debug("ordersRepository holds the following: {}" + ordersRepository.getClass().getSimpleName());
        logger.debug("suppliersRepository holds the following value: {}" + suppliersRepository.getClass().getSimpleName());
    }

    public void placeOrder(Integer issueID, Integer qty){
        Issue issueOrder = (Issue) issuesRepository.find(issueID);
        logger.warn("issueOrder could hold the a null reference if a specific issue was not found with the given issueID: {}", issueID.getClass().getSimpleName());
        if(issueOrder == null) {
            logger.info("issueOrder is null at the moment");
            return;
        }


        Order order = ordersRepository.search("IssueID", issueID).get(0);
        logger.info("order has a value: {}", order.getClass().getSimpleName());

        Supplier supplier = order.getSupplier();

        //move this
        ioFactory = new ConcreteIssueOrderAdapterFactory(issueOrder, order);

        IssueOrderAdapter IOAdapter = (IssueOrderAdapter) ioFactory.createAdapter();
        supplierService.placeOrder(IOAdapter, supplier.getSupplierReferenceNumber(), order.getQty());

        //ordersRepository.update(order);

        SupplierPayment sp = new SupplierPayment();
        sp.setOrder(order);
        sp.setProcessedDate(new Date());
        sp.setTotal(order.getTotal());

        spFactory = new ConcreteSupplierPaymentAdapterFactory(supplier, sp);

        SupplierPaymentAdapter SPAdapter = (SupplierPaymentAdapter) spFactory.createAdapter();
        paymentService.makePayment(SPAdapter);


        logger.info("Ending method {}", getClass().getEnclosingMethod());
        //Save the payment


    }

    public void testDummyMethod(int value1, String text){
        logger.debug("This is just to test with the object I'm working with");
        logger.info("value1 holds an integer of: {}", value1);
        logger.info("text is a string with the value: {}", text);
    }
}
