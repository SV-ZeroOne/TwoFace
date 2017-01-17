package za.co.entelect.bootcamp.twoface.squareeyes.web;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.*;
import za.co.entelect.bootcamp.twoface.squareeyes.web.facade.SupplierOrderFacade;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test for simple Repository.
 */
public class AppTest {
    @Before
    public void print(){
    }

    @Test
    public void test() {

        IssueRepository ir = new IssueRepositoryIMP();
        OrderRepository or = new OrderRepositoryIMP();
        SupplierRepository sr = new SupplierRepositoryIMP();

        SupplierOrderFacade sof = new SupplierOrderFacade(new MockPaymentService(), new MockSupplierService(), ir, or, sr);
    }

}
