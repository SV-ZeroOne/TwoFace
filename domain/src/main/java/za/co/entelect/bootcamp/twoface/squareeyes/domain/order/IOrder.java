package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

import java.util.Date;
import java.util.List;

/**
 * Created by quinton.weenink on 2017/01/15.
 */
interface IOrder {
    int getOrderID();
    void setOrderID(int orderID);

    Date getOrderDate();
    void setOrderDate(Date orderDate);

    byte getQtyOrdered();
    void setQtyOrdered(byte qtyOrdered);

    long getTotal();
    //setTotal removed

    String getShipmentRef();
    void setShipmentRef(String shipmentRef);

    Date getShipmentDate();
    void setShipmentDate(Date shipmentDate);

    String getDeliveryStatus();
    void setDeliveryStatus(String deliveryStatus);

    List<Issue> getIssueList();
    void setIssueList(List<Issue> issueList);

    Supplier getSupplier();
    void setSupplier(Supplier supplier);
}
