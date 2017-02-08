package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.ComicCreator;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.Creator;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.CreatorsRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.RelationalCreatorsRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.RelationalIssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.RelationalStockRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple Repository.
 */
@Ignore
public class AppTest {

    StockRepository sr;

    @Before
    public void print(){
        //ApplicationContext context = new ClassPathXmlApplicationContext("classpath:za/co/entelect/bootcamp/twoface/squareeyes/persistence/root-context.xml");
        //sr = context.getBean(RelationalStockRepository.class);
    }

    @Test
    @Ignore
    public void persistNewStockWithItem() {
        StockRepository str = new RelationalStockRepository();

        Issue issue = new Issue(new Date(), "New 2",
                "Quinton", (short)22, "The descripition");

        Stock stock = new Stock("Good", (short)2, new BigDecimal(3.00));
        stock.setIssue(issue);

        str.create(stock);

    }

    @Test
    @Ignore
    public void getStockByID() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:za/co/entelect/bootcamp/twoface/squareeyes/persistence/root-context.xml");
        sr = context.getBean(RelationalStockRepository.class);

        Stock stock = sr.find(30);
        System.out.println("R " + stock.getPrice());
    }

    @Test
    @Ignore
    public void persistNewCreatorThroughIssue() {
        IssuesRepository ir = new RelationalIssuesRepository();

        Issue issue = new Issue(new Date(), "New 2",
                "Quinton", (short)22, "The descripition");
        Creator creator = new Creator("Quinton Weenink",
                "South Africa",
                "123".getBytes(),
                "quinton@mail.com");
        CreatorsRepository cr = new RelationalCreatorsRepository();
        cr.create(creator);

        ComicCreator comicCreator = new ComicCreator();
        comicCreator.setCreator(creator);
        comicCreator.setIssue(issue);
        comicCreator.setCreatorRole("Eats");

        issue.addComicCreator(comicCreator);

        ir.create(issue);

    }

    @Test
    @Ignore
    public void searchPropertyTest() {
        IssuesRepository ir = new RelationalIssuesRepository();

        List<Issue> issues = ir.search("title","Star Wars: The Clone Wars");

    }

}
