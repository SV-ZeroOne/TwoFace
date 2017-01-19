package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;

import static org.junit.Assert.*;

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
