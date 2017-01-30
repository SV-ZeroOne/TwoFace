package za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders;

import org.springframework.stereotype.Repository;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.RelationalRepository;

import javax.persistence.EntityManager;

/**
 * Created by sean.vienings on 2017/01/16.
 */
@Repository
public class OrdersRepositoryIMP extends RelationalRepository<Order> implements OrdersRepository {
}
