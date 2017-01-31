package za.co.entelect.bootcamp.twoface.squareeyes.logging;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.RelationalIssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.RelationalOrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.RelationalSuppliersRepository;

/**
 * Unit test for simple Repository.
 */
@Ignore
public class AppTest {
    @Before
    public void print(){
        System.out.println("Stating test..");
    }

    @Test
    public void test() {

        IssuesRepository ir = new RelationalIssuesRepository();
        SuppliersRepository sr = new RelationalSuppliersRepository();
        OrdersRepository or = new RelationalOrdersRepository();

    }

    @After
    public void end(){
        System.out.println("End test..");
    }

}
