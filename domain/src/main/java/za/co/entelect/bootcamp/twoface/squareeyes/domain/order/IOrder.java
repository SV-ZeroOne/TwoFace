package za.co.entelect.bootcamp.twoface.squareeyes.domain.order;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.comic.IIssue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.ISupplier;

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

    List<IIssue> getIssueList();
    void setIssueList(List<IIssue> issueList);

    ISupplier getSupplier();
    void setSupplier(ISupplier supplier);
}
