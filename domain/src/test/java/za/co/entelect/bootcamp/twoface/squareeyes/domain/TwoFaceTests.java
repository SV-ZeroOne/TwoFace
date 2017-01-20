package za.co.entelect.bootcamp.twoface.squareeyes.domain;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.Creator;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import org.junit.Before;
import org.junit.Test;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sean.vienings on 2017/01/19.
 */
public class TwoFaceTests {
    /**
     * TwoFace project test cases
     */
   @Before
    public void printBefore(){
        System.out.print("Starting test ");
    }

    @AfterClass
    public static void printAfter(){
        System.out.println("Finished tests");
    }

    @Test
    public void issueExists() {
        System.out.print(": Issue Exists...");
        Issue cutIssue = new Issue(new Date(), "title","publisher",(short)001,"description");
        Assert.assertEquals("title",cutIssue.getIssueTitle());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void creatorExists() {
        System.out.print(": Creator Exists...");
        Creator cutCreator = new Creator( "name", "countryOfResidence", "taxReference".getBytes(), "emailAddress");
        Assert.assertEquals("name",cutCreator.getName());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void stockExists() {
        System.out.print(": Stock Exists...");
        Stock cutStock = new Stock("condition", (short)10, BigDecimal.ONE);
        Assert.assertEquals("condition",cutStock.getCondition());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void supplierExists() {
        System.out.print(": Supplier Exists...");
        Supplier cutSuppliers = new Supplier("supplierName", "supplierCity", "supplierReferenceNumber");
        Assert.assertEquals("supplierReferenceNumber",cutSuppliers.getSupplierReferenceNumber());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void orderExists() {
        System.out.print(": Order Exists...");
        Order cutOrder = new Order(new Date(), (short)5, BigDecimal.valueOf(10), "shipmentRef",new Date(), "deliveryStatus");
        Assert.assertEquals("shipmentRef",cutOrder.getShipmentRef());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void issueRetrievePublicationDate() {
        System.out.print(": Issue Exists...");
        Date temp = new Date();
        Issue cutIssue = new Issue(temp, "title","publisher",(short)001,"description");
        Assert.assertEquals(temp,cutIssue.getPublicationDate());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void ordersIssuesRelationshipMapping() {
        System.out.print(": Issue & Order mapping");
        Issue tempIssue =  new Issue(new Date(), "title","publisher",(short)001,"description");
        Supplier tempSuppliers = new Supplier("supplierName", "supplierCity", "supplierReferenceNumber");
        Order cutOrder = new Order(new Date(), (short)5, BigDecimal.valueOf(10), "shipmentRef",new Date(), "deliveryStatus");
        cutOrder.setIssue(tempIssue);
        cutOrder.setSupplier(tempSuppliers);
        Assert.assertEquals(tempIssue.getIssueID(),cutOrder.getIssue().getIssueID());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void ordersSuppliersRelationshipMapping() {
        System.out.print(": Supplier & Order mapping");
        Issue tempIssue =  new Issue(new Date(), "title","publisher",(short)001,"description");
        Supplier tempSuppliers = new Supplier("supplierName", "supplierCity", "supplierReferenceNumber");
        Order cutOrder = new Order(new Date(), (short)5, BigDecimal.valueOf(10), "shipmentRef",new Date(), "deliveryStatus");
        cutOrder.setIssue(tempIssue);
        cutOrder.setSupplier(tempSuppliers);
        Assert.assertEquals(tempSuppliers.getSupplierID(),cutOrder.getSupplier().getSupplierID());
        System.out.print(" : test passed." + "\n");
    }

}