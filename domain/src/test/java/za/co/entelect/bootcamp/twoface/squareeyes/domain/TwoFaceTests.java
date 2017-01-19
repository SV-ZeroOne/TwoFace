package za.co.entelect.bootcamp.twoface.squareeyes.domain;

import org.junit.*;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.creator.Creators;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import org.junit.Before;
import org.junit.Test;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Orders;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Suppliers;

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
        Issues cutIssue = new Issues(new Date(), "title","publisher",(short)001,"description");
        Assert.assertEquals("title",cutIssue.getIssueTitle());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void creatorExists() {
        System.out.print(": Creator Exists...");
        Creators cutCreator = new Creators("name", "countryOfResidence", "taxReference", "emailAddress");
        Assert.assertEquals("name",cutCreator.getName());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void stockExists() {
        System.out.print(": Stock Exists...");
        Stock cutStock = new Stock(new Issues(), "condition", (short)10, BigDecimal.ONE);
        Assert.assertEquals("condition",cutStock.getCondition());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void supplierExists() {
        System.out.print(": Supplier Exists...");
        Suppliers cutSuppliers = new Suppliers("supplierName", "supplierCity", "supplierReferenceNumber");
        Assert.assertEquals("supplierReferenceNumber",cutSuppliers.getSupplerReference());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void orderExists() {
        System.out.print(": Order Exists...");
        Orders cutOrder = new Orders(new Date(), (short)5, BigDecimal.valueOf(10), "shipmentRef",new Date(), "deliveryStatus",new Issues(),new Suppliers());
        Assert.assertEquals("shipmentRef",cutOrder.getShipmentRef());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void issueRetrievePublicationDate() {
        System.out.print(": Issue Exists...");
        Date temp = new Date();
        Issues cutIssue = new Issues(temp, "title","publisher",(short)001,"description");
        Assert.assertEquals(temp,cutIssue.getPublicationDate());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void ordersIssuesRelationshipMapping() {
        System.out.print(": Issue & Order mapping");
        Issues tempIssue =  new Issues(new Date(), "title","publisher",(short)001,"description");
        Suppliers tempSuppliers = new Suppliers("supplierName", "supplierCity", "supplierReferenceNumber");
        Orders cutOrder = new Orders(new Date(), (short)5, BigDecimal.valueOf(10), "shipmentRef",new Date(), "deliveryStatus",tempIssue,tempSuppliers);
        Assert.assertEquals(tempIssue.getID(),cutOrder.getIssue());
        System.out.print(" : test passed." + "\n");
    }

    @Test
    public void ordersSuppliersRelationshipMapping() {
        System.out.print(": Supplier & Order mapping");
        Issues tempIssue =  new Issues(new Date(), "title","publisher",(short)001,"description");
        Suppliers tempSuppliers = new Suppliers("supplierName", "supplierCity", "supplierReferenceNumber");
        Orders cutOrder = new Orders(new Date(), (short)5, BigDecimal.valueOf(10), "shipmentRef",new Date(), "deliveryStatus",tempIssue,tempSuppliers);
        Assert.assertEquals(tempSuppliers.getID(),cutOrder.getSuppliers());
        System.out.print(" : test passed." + "\n");
    }

}