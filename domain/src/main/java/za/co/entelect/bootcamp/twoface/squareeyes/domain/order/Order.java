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
@Table
public class Order{

    @Id
    @GeneratedValue
    private int orderID;

    @Temporal(value = TemporalType.DATE)
    private Date orderDate;

    @Column
    private BigDecimal total;

    @Column
    private String shipmentRef;

    @Temporal(value = TemporalType.DATE)
    @Column
    private Date shipmentDate;

    @Column
    private String deliveryStatus;

    @ManyToOne
    private Issue issue;

    @Column
    private Integer qty;

    @ManyToOne
    private Supplier supplier;

    public Order (){
    }

    public Order (Date orderDate, Integer qty, BigDecimal total, String shipmentRef, Date shipmentDate, String deliveryStatus, Issue issue, Supplier supplier){
        this.orderDate = orderDate;
        this.qty = qty;
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
