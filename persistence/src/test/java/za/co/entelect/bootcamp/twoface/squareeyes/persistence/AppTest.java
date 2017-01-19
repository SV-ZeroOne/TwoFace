package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.ComicCreators;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.Creators;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Orders;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.CreatorsRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.CreatorsRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepositoryIMP;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple Repository.
 */
public class AppTest {

    @Before
    public void print(){
    }

    @Test
    public void persistNewItemWithNewCustomer() {
        IssuesRepository ir = new IssuesRepositoryIMP();
        SuppliersRepository sr = new SuppliersRepositoryIMP();
        OrdersRepository or = new OrdersRepositoryIMP();
        CreatorsRepository cr = new CreatorsRepositoryIMP();
        StockRepository str = new StockRepositoryIMP();

        Issues issue = new Issues(new Date(), "This is new",
                "Yo Mama", (short)22, "The descripition");
        ir.create(issue);

        Stock stock = new Stock(issue, "Good", (short)2, new BigDecimal(3.00));

        //ComicCreators comicCreators = new ComicCreators();
        //comicCreators.setCreatorRole("The best");
        //comicCreators.setIssues(issue);
        //comicCreators.setCreators(new Creators("Quinton", "South Africa", "112233", "quinton@mail.com"));


        str.create(stock);

        //issue.addIssue(comicCreators);




        ir.create(issue);
    }

}
