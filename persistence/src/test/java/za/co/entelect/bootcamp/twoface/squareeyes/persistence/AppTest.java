package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.ComicCreator;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.Creator;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.CreatorsRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.creators.CreatorsRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepositoryIMP;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple Repository.
 */
@Ignore
public class AppTest {

    @Before
    @Ignore
    public void print(){
    }

    @Test
    @Ignore
    public void persistNewStockWithItem() {
        StockRepository str = new StockRepositoryIMP();

        Issue issue = new Issue(new Date(), "New 2",
                "Quinton", (short)22, "The descripition");

        Stock stock = new Stock("Good", (short)2, new BigDecimal(3.00));
        stock.setIssue(issue);

        str.create(stock);

    }

    @Test
    @Ignore
    public void getStockByID() {
        StockRepository str = new StockRepositoryIMP();

        Stock stock = str.find(30);
        stock.toString();

    }

    @Test
    @Ignore
    public void persistNewCreatorThroughIssue() {
        IssuesRepository ir = new IssuesRepositoryIMP();

        Issue issue = new Issue(new Date(), "New 2",
                "Quinton", (short)22, "The descripition");
        Creator creator = new Creator("Quinton Weenink",
                "South Africa",
                "123".getBytes(),
                "quinton@mail.com");
        CreatorsRepository cr = new CreatorsRepositoryIMP();
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
        IssuesRepository ir = new IssuesRepositoryIMP();

        List<Issue> issues = ir.search("title","Star Wars: The Clone Wars");

    }

}
