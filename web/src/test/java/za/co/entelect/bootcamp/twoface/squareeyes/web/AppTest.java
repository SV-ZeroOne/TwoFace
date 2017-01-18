package za.co.entelect.bootcamp.twoface.squareeyes.web;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.*;
import za.co.entelect.bootcamp.twoface.squareeyes.web.facade.SupplierOrderFacade;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test for simple Repository.
 */
public class AppTest {
    @Before
    public void print(){
        System.out.println("Stating test..");
    }

    @Test
    public void test() {

        IssueRepository ir = new IssueRepositoryIMP();
        SupplierRepository sr = new SupplierRepositoryIMP();
        OrderRepository or = new OrderRepositoryIMP();


        SupplierOrderFacade sof = new SupplierOrderFacade(new MockPaymentService(), new MockSupplierService(), ir, or, sr);
        sof.placeOrder(30, 2);
    }

    @After
    public void end(){
        System.out.println("End test..");
    }

}
