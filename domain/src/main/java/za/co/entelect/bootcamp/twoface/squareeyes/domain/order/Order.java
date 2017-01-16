package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.IIssue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.ISupplier;

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
    private List<IIssue> issueList;
    private ISupplier supplier;

    public Order (){
    }
    public Order (int orderID, Date orderDate, byte qtyOrdered, long total, String shipmentRef, Date shipmentDate, String deliveryStatus, List<IIssue> issueList, ISupplier supplier){
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

    public List<IIssue> getIssueList(){
        return this.issueList;
    }
    public void setIssueList(List<IIssue> issueList){
        this.issueList = issueList;
    }

    public ISupplier getSupplier(){
        return this.supplier;
    }
    public void setSupplier(ISupplier supplier){
        this.supplier = supplier;
    }
}
