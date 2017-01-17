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
    private Issue issue;
    private Integer qty;
    private Supplier supplier;

    public Order (){
    }
    public Order (int orderID, Date orderDate, byte qtyOrdered, long total, String shipmentRef, Date shipmentDate, String deliveryStatus, Issue issue, Supplier supplier){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.shipmentRef = shipmentRef;
        this.shipmentDate = shipmentDate;
        this.deliveryStatus = deliveryStatus;
        this.issue = issue;
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

    public Issue getIssue(){
        return this.issue;
    }
    public void setIssue(Issue issue){
        this.issue = issue;
    }

    public Integer getQty(){
        return this.qty;
    }
    public void setQty(Integer qty){
        this.qty = qty;
    }

    public Supplier getSupplier(){
        return this.supplier;
    }
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }
}
