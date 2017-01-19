package za.co.entelect.bootcamp.twoface.squareeyes.web;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.*;
import za.co.entelect.bootcamp.twoface.squareeyes.web.facade.SupplierOrderFacade;

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

        IssuesRepository ir = new IssuesRepositoryIMP();
        SuppliersRepository sr = new SuppliersRepositoryIMP();
        OrdersRepository or = new OrdersRepositoryIMP();


        SupplierOrderFacade sof = new SupplierOrderFacade(new MockPaymentService(), new MockSupplierService(), ir, or, sr);
        sof.placeOrder(30, 2);
    }

    @After
    public void end(){
        System.out.println("End test..");
    }

}
