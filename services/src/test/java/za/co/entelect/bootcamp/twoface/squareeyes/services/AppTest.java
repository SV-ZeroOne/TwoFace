package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
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

    @Before
    public void print(){
    }

    @Test
    public void givenRandValueWhenConvertedToDollarExpectCorrectConversion() {
        IssuesRepository ir = new IssuesRepositoryIMP();
        SuppliersRepository sr = new SuppliersRepositoryIMP();
        OrdersRepository or = new OrdersRepositoryIMP();

        Issues issue = ir.find(30);

    }

}
