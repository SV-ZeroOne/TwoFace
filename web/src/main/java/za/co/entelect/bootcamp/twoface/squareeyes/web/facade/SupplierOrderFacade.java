package za.co.entelect.bootcamp.twoface.squareeyes.web.facade;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.SupplierPayment;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.IssueRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.SupplierPaymentAdapter;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.IssueOrderAdapter;

import java.util.Map;

public class SupplierOrderFacade
{
    private Issue placeSOIssue;
    private Order placeSOOrder;
    private Supplier placeSOSupplier;

    void placeSupplierOrder(Issue issue, Order order, Supplier supplier){
        placeSOIssue =  issue;
        placeSOOrder = order;
        placeSOSupplier = supplier;


        Repository issues = new IssueRepositoryIMP();
        issues.create(issue);
        
    }
    public void placeOrder(){
        //if following is true
        validateIssue();
        validateQuote();
        validateSupplier();

        IssueOrderAdapter IOAdapter = new IssueOrderAdapter(placeSOIssue,placeSOOrder);
        IOAdapter.doSomething();

    }

    public void paySupplier(){
        validatePayment();

        SupplierPaymentAdapter SPAdapter = new SupplierPaymentAdapter(placeSOSupplier, SupplierPayment);
        SPAdapter.doSomething();
    }

    public void validateQuote(){ /*Some validation checks here*/}
    public void validateSupplier(){ /*Validation checks*/}
    public void validateIssue(){/*Validation*/}

    public void validatePayment(){/*validate payment*/}

}
