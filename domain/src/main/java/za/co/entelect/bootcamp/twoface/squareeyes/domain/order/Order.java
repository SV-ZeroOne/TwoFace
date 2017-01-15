package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

import java.util.Date;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
public class Order implements IOrder {
    private int orderID;
    private Date orderDate;
    private byte qtyOrdered;
    private long total;
    private String shipmentRef;
    private Date shipmentDate;
    private String deliveryStatus;
    private Issue issue;
    private Supplier supplier;

    public Order () {
    }
    public Order (int orderID, Date orderDate, byte qtyOrdered, long total, String shipmentRef, Date shipmentDate, String deliveryStatus, Issue issue, Supplier supplier){
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.qtyOrdered = qtyOrdered;
        this.total = total;
        this.shipmentRef = shipmentRef;
        this.shipmentDate = shipmentDate;
        this.deliveryStatus = deliveryStatus;
        this.issue = issue;
        this.supplier = supplier;
    }

    public 

}
