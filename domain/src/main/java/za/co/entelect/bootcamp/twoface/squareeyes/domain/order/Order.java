package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

import java.util.Date;
import java.util.List;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
public class Order implements IOrder{
    private int orderID;
    private Date orderDate;
    private byte qtyOrdered;
    private long total;
    private String shipmentRef;
    private Date shipmentDate;
    private String deliveryStatus;
    private List<Issue> issueList;
    private Supplier supplier;

    public Order (){
    }
    public Order (int orderID, Date orderDate, byte qtyOrdered, long total, String shipmentRef, Date shipmentDate, String deliveryStatus, List<Issue> issueList, Supplier supplier){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.qtyOrdered = qtyOrdered;
        this.total = total;
        this.shipmentRef = shipmentRef;
        this.shipmentDate = shipmentDate;
        this.deliveryStatus = deliveryStatus;
        this.issueList = issueList;
        this.supplier = supplier;
    }

    public int getOrderID(){
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

    public byte getQtyOrdered(){
        return this.qtyOrdered;
    }
    public void setQtyOrdered(byte qtyOrdered){
        this.qtyOrdered = qtyOrdered;
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

    public List<Issue> getIssueList(){
        return this.issueList;
    }
    public void setIssueList(List<Issue> issueList){
        this.issueList = issueList;
    }

    public Supplier getSupplier(){
        return this.supplier;
    }
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }
}
