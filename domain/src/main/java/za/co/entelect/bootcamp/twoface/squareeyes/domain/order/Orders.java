package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issues;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Suppliers;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
@Entity
@Table
public class Orders {

    @Id
    @GeneratedValue
    @Column(name="OrderID")
    private int orderID;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="OrderDate")
    private Date orderDate;

    @Column(name="Total")
    private BigDecimal total;

    @Column(name="ShipmentRef", columnDefinition="char(10)")
    private String shipmentRef;

    @Temporal(value = TemporalType.DATE)
    @Column(name="ShipmentDate")
    private Date shipmentDate;

    @Column(name="DeliveryStatus")
    private String deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "IssueID")
    private Issues issue;

    @Column(name="QtyOrdered")
    private short qty;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Suppliers suppliers;

    public Orders(){
    }

    public Orders(Date orderDate, short qty, BigDecimal total, String shipmentRef, Date shipmentDate, String deliveryStatus, Issues issue, Suppliers suppliers){
        this.orderDate = orderDate;
        this.qty = qty;
        this.total = total;
        this.shipmentRef = shipmentRef;
        this.shipmentDate = shipmentDate;
        this.deliveryStatus = deliveryStatus;
        this.issue = issue;
        this.suppliers = suppliers;
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

    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }

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

    public Issues getIssue(){
        return this.issue;
    }
    public void setIssue(Issues issue){
        this.issue = issue;
    }

    public short getQty(){
        return this.qty;
    }
    public void setQty(short qty){
        this.qty = qty;
    }

    public Suppliers getSuppliers(){
        return this.suppliers;
    }
    public void setSuppliers(Suppliers suppliers){
        this.suppliers = suppliers;
    }
}
