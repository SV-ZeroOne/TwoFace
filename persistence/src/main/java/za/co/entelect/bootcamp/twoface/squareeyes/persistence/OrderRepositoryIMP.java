package za.co.entelect.bootcamp.twoface.squareeyes.persistence;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.issue.Issue;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.supplier.Supplier;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.generic.RelationalRepository;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sean.vienings on 2017/01/16.
 */
public class OrderRepositoryIMP extends RelationalRepository<Order> implements OrderRepository{

}
