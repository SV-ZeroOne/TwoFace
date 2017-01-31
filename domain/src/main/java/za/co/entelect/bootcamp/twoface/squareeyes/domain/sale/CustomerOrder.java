package za.co.entelect.bootcamp.twoface.squareeyes.domain.sale;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.CustomerAddress;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="CustomerOrders")
public class CustomerOrder {

    private Customer customer;
    private CustomerAddress customerAddress;

    private String deliveryOption;
    private String specialInstructions;
    private BigDecimal paymentAmount;
    private String paymentStatus;
    private Date orderDate;

    CustomerOrder(){}


}
