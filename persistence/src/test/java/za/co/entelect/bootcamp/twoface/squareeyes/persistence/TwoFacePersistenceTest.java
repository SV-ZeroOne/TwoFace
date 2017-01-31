package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.RelationalIssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.RelationalOrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.RelationalSuppliersRepository;

import java.util.Date;

/**
 * Created by sean.vienings on 2017/01/19.
 */
@Ignore
public class TwoFacePersistenceTest {
    static IssuesRepository singleIssueRepository;
    static OrdersRepository singleOrderRepository;
    static SuppliersRepository singleSupplierRepository;

    @BeforeClass
    @Ignore
    public static void testRepositoryConnections()
    {
        System.out.print("Creating Repo");
        singleIssueRepository = new RelationalIssuesRepository();
        singleOrderRepository = new RelationalOrdersRepository();
        singleSupplierRepository = new RelationalSuppliersRepository();
    }

    @Test
    @Ignore
    public void ordersIssuesRelationshipMapping() {
        System.out.println(": Issue & Order mapping");
        Order tempOrder = singleOrderRepository.find(16);
        Issue tempIssue = singleIssueRepository.find(32);
        Assert.assertEquals(tempIssue.getIssueID(), tempOrder.getIssue().getIssueID());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    @Ignore
    public void ordersSuppliersRelationshipMapping() {
        System.out.println(": Supplier & Order mapping");
        Supplier tempSuppliers = singleSupplierRepository.find(2);
        Order tempOrder = singleOrderRepository.find(16);
        Assert.assertEquals(tempSuppliers.getSupplierID(),tempOrder.getSupplier().getSupplierID());
        System.out.print(" : test passed." + "\n");
    }

    @Ignore
    @Test
    public void addingIssueElement() {
        System.out.println(": Adding issue");
        Issue tempIssue = new Issue(new Date(),"title", "publisher", (short) 7, "description");
        Issue testIssue = singleIssueRepository.create(tempIssue);
        System.out.print(testIssue.getIssueID());
        Assert.assertEquals(tempIssue.getDescription(),testIssue.getDescription());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    @Ignore
    public void removingIssueElement() {
        System.out.println(": Removing issue");
        Issue tempIssue = singleIssueRepository.find(24705);
        System.out.print(tempIssue.getIssueID());
        singleIssueRepository.delete(tempIssue);
        System.out.print(" : test passed." + "\n");
    }

}
