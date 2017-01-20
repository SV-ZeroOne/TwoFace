package za.co.entelect.bootcamp.twoface.squareeyes.logging;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepositoryIMP;

/**
 * Unit test for simple Repository.
 */
public class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);
    @Before
    public void print(){
        System.out.println("Stating test..");
    }

    @Test
    public void test() {

    }

    @After
    public void end(){
        System.out.println("End test..");
    }

}
