package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Orders;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Suppliers;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.issues.IssuesRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SuppliersRepositoryIMP;

import java.util.Date;

/**
 * Created by sean.vienings on 2017/01/19.
 */
public class TwoFacePersistenceTest {
    static IssuesRepository singleIssueRepository;
    static OrdersRepository singleOrderRepository;
    static SuppliersRepository singleSupplierRepository;

    @BeforeClass
    public static void testRepositoryConnections()
    {
        System.out.print("Creating Repo");
        singleIssueRepository = new IssuesRepositoryIMP();
        singleOrderRepository = new OrdersRepositoryIMP();
        singleSupplierRepository = new SuppliersRepositoryIMP();
    }

    @Test
    public void ordersIssuesRelationshipMapping() {
        System.out.println(": Issue & Order mapping");
        Orders tempOrder = singleOrderRepository.find(16);
        Issues tempIssue = singleIssueRepository.find(32);
        Assert.assertEquals(tempIssue.getID(), tempOrder.getIssue().getID());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void ordersSuppliersRelationshipMapping() {
        System.out.println(": Supplier & Order mapping");
        Suppliers tempSuppliers = singleSupplierRepository.find(2);
        Orders tempOrder = singleOrderRepository.find(16);
        Assert.assertEquals(tempSuppliers.getID(),tempOrder.getSuppliers().getID());
        System.out.print(" : test passed." + "\n");
    }

}
