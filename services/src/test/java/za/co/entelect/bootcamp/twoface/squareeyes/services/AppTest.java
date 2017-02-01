package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.RelationalStockRepository;

import java.util.List;

/**
 * Unit test for simple Repository.
 */
public class AppTest {

    private CatalogueService cs;

    public AppTest(){

    }
    @Before
    public void print(){
    }

    @Test
    public void testCatalogueService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:za/co/entelect/bootcamp/twoface/squareeyes/services/root-context.xml");
        cs = context.getBean(CatalogueService.class);
        List<Issue> issues = cs.getCataloguePage(1);
        for (Issue i: issues) {
            System.out.println("Title: " + i.getIssueTitle());
        }
    }


}
