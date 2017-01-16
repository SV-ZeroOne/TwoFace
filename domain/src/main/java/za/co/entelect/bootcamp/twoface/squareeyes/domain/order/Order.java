package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.Entity;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

import java.util.Date;
import java.util.Map;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
public class Order implements Entity<Integer> {
    private int orderID;
    private Date orderDate;
    private long total;
    private String shipmentRef;
    private Date shipmentDate;
    private String deliveryStatus;
    private Map<Issue, Integer> issueList;
    private Supplier supplier;

    public Order (){
    }
    public Order (int orderID, Date orderDate, byte qtyOrdered, long total, String shipmentRef, Date shipmentDate, String deliveryStatus, Map<Issue, Integer> issueList, Supplier supplier){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.shipmentRef = shipmentRef;
        this.shipmentDate = shipmentDate;
        this.deliveryStatus = deliveryStatus;
        this.issueList = issueList;
        this.supplier = supplier;
    }

    public Integer getID(){
        return this.orderID;
    }
    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public Date getOrderDate(){
        return this.orderDate;
    }
    public void setOrderDate(Date orderDate){
        this.orderDate = orderDate;
    }

    public int getQtyOrdered(Issue issue){
        return issueList.get(issue);
    }
    public void setQtyOrdered(Issue issue, int qtyOrdered){
        this.issueList.put(issue, qtyOrdered);
    }

    public long getTotal(){
        return this.total;
    }
    //setTotal removed

    public String getShipmentRef(){
        return this.shipmentRef;
    }
    public void setShipmentRef(String shipmentRef){
        this.shipmentRef = shipmentRef;
    }

    public Date getShipmentDate(){
        return this.shipmentDate;
    }
    public void setShipmentDate(Date shipmentDate){
        this.shipmentDate = shipmentDate;
    }

    public String getDeliveryStatus(){
        return this.deliveryStatus;
    }
    public void setDeliveryStatus(String deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }

    public Map<Issue, Integer> getIssueList(){
        return this.issueList;
    }
    public void setIssueList(Map<Issue, Integer> issueList){
        this.issueList = issueList;
    }
    public void addIssue(Issue issue, int qtyOrdered){
        issueList.put(issue, qtyOrdered);
    }

    public Supplier getSupplier(){
        return this.supplier;
    }
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }
}
