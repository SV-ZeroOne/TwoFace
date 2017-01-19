package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.ComicCreators;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.CreatorsRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.CreatorsRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepositoryIMP;

import java.util.List;

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
        CreatorsRepository cr = new CreatorsRepositoryIMP();

        Issues issue = ir.find(30);

        List<ComicCreators> list = cr.find(3).getComicCreators();
        System.out.println(cr.find(3).getName());
        System.out.println();
        for(ComicCreators i: list){
            System.out.println(i.getIssues().getIssueTitle());
        }


    }

}
