package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
@Entity
@Table(name="Orders")
public class Order {

    private Issue issue;
    private Supplier supplier;

    private int orderID;
    private Date orderDate;
    private BigDecimal total;
    private String shipmentRef;
    private Date shipmentDate;
    private String deliveryStatus;
    private short qty;

    public Order(){}

    public Order(Date orderDate, short qty, BigDecimal total, String shipmentRef, Date shipmentDate, String deliveryStatus){
        this.orderDate = orderDate;
        this.qty = qty;
        this.total = total;
        this.shipmentRef = shipmentRef;
        this.shipmentDate = shipmentDate;
        this.deliveryStatus = deliveryStatus;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "IssueID")
    public Issue getIssue(){
        return this.issue;
    }
    public void setIssue(Issue issue){
        this.issue = issue;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "SupplierID")
    public Supplier getSupplier(){
        return this.supplier;
    }
    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }

    @Id
    @GeneratedValue
    @Column(name="OrderID")
    public int getOrderID(){
        return this.orderID;
    }
    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="OrderDate")
    public Date getOrderDate(){
        return this.orderDate;
    }
    public void setOrderDate(Date orderDate){
        this.orderDate = orderDate;
    }

    @Column(name="Total")
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }

    @Column(name="ShipmentRef", columnDefinition="char(10)")
    public String getShipmentRef(){
        return this.shipmentRef;
    }
    public void setShipmentRef(String shipmentRef){
        this.shipmentRef = shipmentRef;
    }

    @Temporal(value = TemporalType.DATE)
    @Column(name="ShipmentDate")
    public Date getShipmentDate(){
        return this.shipmentDate;
    }
    public void setShipmentDate(Date shipmentDate){
        this.shipmentDate = shipmentDate;
    }

    @Column(name="DeliveryStatus")
    public String getDeliveryStatus(){
        return this.deliveryStatus;
    }
    public void setDeliveryStatus(String deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }

    @Column(name="QtyOrdered")
    public short getQty(){
        return this.qty;
    }
    public void setQty(short qty){
        this.qty = qty;
    }

    //testing
    @Override
    public String toString(){
        return  String.format("orderDate: " + orderDate + "\n" +
                         "qty: " + qty + "\n" +
                         "total: " + total + "\n" +
                         "shipmentRef: " + shipmentRef + "\n" +
                         "shipmentDate: " + shipmentDate + "\n" +
                         "deliveryStatus: " + deliveryStatus + "\n");
    }
}
