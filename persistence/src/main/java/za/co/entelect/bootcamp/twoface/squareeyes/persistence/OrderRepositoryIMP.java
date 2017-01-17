package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sean.vienings on 2017/01/16.
 */
public class OrderRepositoryIMP extends RepositoryBase implements OrderRepository{
    //public Order (int orderID, Date orderDate, byte qtyOrdered, BigDecimal total, String shipmentRef, Date shipmentDate, String deliveryStatus, Issue issue, Supplier supplier){
    public OrderRepositoryIMP(Issue issue, Supplier supplier) {
        tList.put(1, new Order(1, new Date(), (byte)10, new BigDecimal(100), "DMX", new Date(), "In transit", issue, supplier));

    }
}
